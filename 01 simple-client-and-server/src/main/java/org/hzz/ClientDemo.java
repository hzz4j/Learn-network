package org.hzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 8380), 3000);
        System.out.println("客户端: " + socket.getLocalAddress() + ":" + socket.getLocalPort());
        System.out.println("服务端: " + socket.getInetAddress() + ":" + socket.getPort());

        // 得到Socket输出流，并转换为打印流
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.println("Hello Server");
        printStream.flush();
        String msg = socketReader.readLine();
        System.out.println(msg);

        printStream.close();
        socketReader.close();
        socket.close();
    }
}
