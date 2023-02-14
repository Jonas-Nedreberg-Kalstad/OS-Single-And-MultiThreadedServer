package no.ntnu.idata2305;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

  private int serverPort;
  private ServerSocket serverSocket;
  private boolean isStopped;

  public MultiThreadedServer(int port) {
    this.serverPort = port;

  }

  public void run() {

    openServerSocket();

    while (!isStopped()) {
      Socket clientSocket = null;
      try {
        // Accepts client requests / Waits for client request
        clientSocket = this.serverSocket.accept();
      } catch (IOException e) {
        System.out.println("Server Stopped running");
        return;
      }
      Thread thread = new Thread(new AsyncSearchSimulator(clientSocket));
      thread.start();
      // Prints out the current thread being used
      try {
        // Needs to be 1050 since the sleep in AsyncSearchSimulator is 1000
        Thread.sleep(1050L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println(thread.getName());
      // Terminates thread and printing out status if thread is Alive or not
      thread.interrupt();
      System.out.println("Thread is still alive: " + thread.isAlive());
    }
  }

  private synchronized boolean isStopped() {
    return this.isStopped;
  }

  public synchronized void stop() {
    this.isStopped = true;
    try {
      //Sets isStopped to true, then closes the server connection.
      this.serverSocket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void openServerSocket() {
    try {
      // Opens serverSocket with given serverPort
      this.serverSocket = new ServerSocket(this.serverPort);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
