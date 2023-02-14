package no.ntnu.idata2305;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedServer implements Runnable {

    private int serverPort;
    private ServerSocket serverSocket;
    private boolean isStopped;
    private Thread singleThread;

    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        //synchronizes singleThread so there is a monitor which
        //guarantees that it can only be accessed by one object at the time.
        synchronized(this){
            this.singleThread = Thread.currentThread();
        }
        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                // Accepts client requests / Waits for client request
                clientSocket = serverSocket.accept();

                // Processes client request
                SearchSimulator.processClientRequest(clientSocket);
            } catch (IOException e) {
                System.out.println("Server Stopped running");
                return;
            } catch (Exception e) {
                throw new RuntimeException("Failed to process request", e);
            }
            // Prints out the current thread being used
            System.out.println(this.singleThread.getName());
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
