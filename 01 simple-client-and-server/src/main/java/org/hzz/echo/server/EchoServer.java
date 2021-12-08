package org.hzz.echo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private ServerSocket serverSocket;
    private int port;
    public EchoServer(int port){
        this.port = port;
    }

    public void start(){
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server start up listen port->"+serverSocket.getLocalPort());
            while(true){
                System.out.println("监听客户端连接... ...");
                Socket client = serverSocket.accept();
                System.out.println("客户端连接："+client.getInetAddress()+":"+client.getPort());
                System.out.println("客户端连接："+client.getLocalAddress()+":"+client.getLocalPort());
                new ClientHandler(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server shutdown");
    }
}
