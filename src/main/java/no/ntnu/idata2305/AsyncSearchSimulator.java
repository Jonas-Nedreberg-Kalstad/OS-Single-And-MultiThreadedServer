package no.ntnu.idata2305;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class AsyncSearchSimulator implements Runnable {

  private Socket clientSocket;

  public AsyncSearchSimulator(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public void run() {
    try {
      InputStream inputStream = clientSocket.getInputStream();
      OutputStream outputStream = clientSocket.getOutputStream();
      long time1 = System.currentTimeMillis();
      Thread.sleep(1000L);
      long time2 = System.currentTimeMillis();
      byte[] message = ResponseGenerator.generatorResponseHTML(time1, time2).getBytes(StandardCharsets.UTF_8);
      byte[] header = ResponseGenerator.generatorResponseHeader(1000).getBytes(StandardCharsets.UTF_8);
      outputStream.write(header);
      outputStream.write(message);
      outputStream.close();
      inputStream.close();

      System.out.println("Server request time: " + (time2 - time1));
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
