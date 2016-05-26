package test.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by daidongyang on 5/25/16.
 */
public class TestSocket {
    public static void main(String[] args){
        try {
            Socket sockt = new Socket("localhost", 23450);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
