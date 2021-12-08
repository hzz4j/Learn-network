package org.hzz.knockknock.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;

public class KnockKnockClient {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket(Inet4Address.getLocalHost(), 8381);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in));
        ) {
            String fromServer, fromUser;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }
                System.out.print("client: ");
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
