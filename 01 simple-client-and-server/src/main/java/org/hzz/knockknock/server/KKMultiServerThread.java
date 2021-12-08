package org.hzz.knockknock.server;

import org.hzz.knockknock.protocol.KnockKnockProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KKMultiServerThread extends Thread {
    private final Socket socket;
    private final KnockKnockProtocol protocol;

    public KKMultiServerThread(Socket socket) {
        this.socket = socket;
        this.protocol = new KnockKnockProtocol();
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
        ) {
            String inMsg, outMsg;
            outMsg = protocol.processInput(null);
            out.println(outMsg);

            while ((inMsg = in.readLine()) != null) {
                outMsg = protocol.processInput(inMsg);
                out.println(outMsg);
                if (outMsg.equals("Byte.")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
