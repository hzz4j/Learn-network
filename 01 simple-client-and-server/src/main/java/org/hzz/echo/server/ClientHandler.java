package org.hzz.echo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final String hostname;
    private final int port;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.hostname = socket.getInetAddress().toString();
        this.port = socket.getPort();
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(),true)
        ) {
            String line;
            // 判断socket停止的方法
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("客户端" + getClientInfo() + "异常");
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("客户端退出");
    }

    private String getClientInfo() {
        return "客户端 " + this.hostname + ":" + this.port;
    }
}
