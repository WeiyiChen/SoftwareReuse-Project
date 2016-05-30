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
        Scanner scanner = new Scanner(System.in);
        while(true){
            String s = scanner.nextLine();
            if("quit".equals(s)){
                // todo add code for end other thread
                System.exit(0);
            }
        }

    }
}
