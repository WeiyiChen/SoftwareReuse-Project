package edu.tongji.reuse.teameleven.client.transport;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by daidongyang on 5/26/16.
 */
public class ClientMsgSocket {
    private static int port = 0;
    private static String ip = "";

    public static void setIPAndPort(String _ip, int _port){
        ip = _ip;
        port = _port;
    }

    private static Socket clientMsgSocket = null;

    private synchronized static Socket createSocket(String _ip, int _port) throws IOException {
        clientMsgSocket = new Socket(_ip, _port);
        return clientMsgSocket;
    }

    public static Socket getSocket() throws IOException{
        if(port == 0){
            System.out.println("plase init ip and port");
            return null;
        }
        if(null == clientMsgSocket || clientMsgSocket.isClosed()){
            return createSocket(ip, port);
        }
        return clientMsgSocket;
    }
}
