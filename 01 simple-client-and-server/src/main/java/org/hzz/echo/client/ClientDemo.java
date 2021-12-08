package org.hzz.echo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        try (
                Socket echoSocket = new Socket(Inet4Address.getLocalHost(),8380);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in));

        ) {
            System.out.println("客户端: " + echoSocket.getLocalAddress() + ":" + echoSocket.getLocalPort());
            System.out.println("服务端: " + echoSocket.getInetAddress() + ":" + echoSocket.getPort());
            String userInput;
            while((userInput = stdIn.readLine()) != null){
                out.println(userInput);
                System.out.println("echo "+in.readLine());
            }
        }catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
    }
}
