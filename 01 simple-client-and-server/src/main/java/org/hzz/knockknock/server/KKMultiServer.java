package org.hzz.knockknock.server;

import java.io.IOException;
import java.net.ServerSocket;

public class KKMultiServer {
    public static void main(String[] args) {
        boolean listening = true;
        int portNumber = 8381;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new KKMultiServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
