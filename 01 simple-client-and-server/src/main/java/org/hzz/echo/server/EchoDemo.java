package org.hzz.echo.server;

import java.io.IOException;

public class EchoDemo {
    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer(8380);
        // 不够优雅
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("钩子函数被执行，可以在这里关闭资源");
            server.stop();
        }));
        server.start();
    }
}


