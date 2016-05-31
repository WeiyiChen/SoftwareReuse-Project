package edu.tongji.reuse.teameleven.client.transport;

import edu.tongji.reuse.teameleven.client.intf.IMsgSender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by daidongyang on 5/31/16.
 */
public class StrMsgSender implements IMsgSender{
    @Override
    public boolean send(Object msg) {

        PrintWriter writer;
        try {
            Socket socket = ClientMsgSocket.getSocket();
            writer = new PrintWriter(socket.getOutputStream());
            String jsonString = (String)msg;
            writer.println(jsonString);
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
