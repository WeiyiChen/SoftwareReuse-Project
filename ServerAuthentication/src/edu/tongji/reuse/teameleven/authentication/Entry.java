package edu.tongji.reuse.teameleven.authentication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * authentication module's0 entry
 * Created by daidongyang on 5/27/16.
 */
public class Entry {
    public static void main(String[] args){

        try {
            // todo port should be read from config file
            ServerSocket serverSocket = new ServerSocket(15501);
            Socket socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
