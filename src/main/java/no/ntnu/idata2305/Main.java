package no.ntnu.idata2305;

import java.util.Scanner;

/**
 * Runs application
 */
class Main {
    public static void main(String[] args) throws Exception {
        boolean quit = false;

        while (!quit) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nPlease enter the letter [M] for Multi-Threaded Server. " +
                    "\nFor Single-Thread Server, press [S] " +
                    "\nIf you wish to quit the application press anything else:");

            String userInput = scanner.nextLine();
            // If you press m for multiThreadedServer this will run
            if (userInput.equalsIgnoreCase("M")) {
                System.out.println("Starting Multi-Threaded Server:");
                // Start multiThreadedServer here
                MultiThreadedServer multiThreadedServer = new MultiThreadedServer(8080);
                Thread multiThread = new Thread(multiThreadedServer);
                multiThread.start();
                // Server runs for 9 seconds
                Thread.sleep(9000);
                multiThreadedServer.stop();
            } else if (userInput.equalsIgnoreCase("S")){
                System.out.println("Starting Single-Threaded Server:");
                // Start singleThreadedServer here
                SingleThreadedServer singleThreadedServer = new SingleThreadedServer(8080);
                Thread singleThread = new Thread(singleThreadedServer);
                singleThread.start();
                // Server runs for 9 seconds
                Thread.sleep(9000);
                singleThreadedServer.stop();
            } else{
                quit = true;
            }

        }
    }
}