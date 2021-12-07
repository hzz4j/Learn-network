package org.hzz.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final String hostname;
    private final int port;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.hostname = socket.getInetAddress().toString();
        this.port = socket.getPort();
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            String line;
            // 判断socket停止的方法
            while ((line = reader.readLine()) != null) {
                System.out.println(getClientInfo() + ": " + line);
                writer.println("echo: "+line);
                writer.flush();
            }
        } catch (IOException e) {
            try {
                reader.close();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("客户端异常");
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
