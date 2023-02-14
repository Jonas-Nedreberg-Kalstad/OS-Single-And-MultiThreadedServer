package no.ntnu.idata2305;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SearchSimulator {
  public static void processClientRequest(Socket socket) throws Exception {
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
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


    }
}
