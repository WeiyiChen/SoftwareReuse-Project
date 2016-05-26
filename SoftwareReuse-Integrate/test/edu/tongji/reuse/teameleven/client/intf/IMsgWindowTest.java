package edu.tongji.reuse.teameleven.client.intf;

import edu.tongji.reuse.teameleven.client.ui.MsgWindow;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by daidongyang on 5/20/16.
 */
public class IMsgWindowTest {
    IMsgWindow imw;
    @Before
    public void before(){
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                System.out.print("in thread");
//
//                imw = new MsgWindow();
//                imw.toShowWindow();
//                imw.addContact("hello");
//            }
//        }).start();

        imw = new MsgWindow();
        imw.toShowWindow();

    }

    @Test
    public void test(){
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("world");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("world");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("world");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("world");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("world");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("world");
        imw.addContact("hello");
        imw.addContact("hello");
        imw.addContact("world");
        imw.addContact("world");
        StringBuilder sb = new StringBuilder("hello");
        StringBuilder sb2 = new StringBuilder("hello");
        imw.removeContact(sb.toString());
        imw.removeContact(sb2.toString());
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }



}