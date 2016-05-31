package edu.tongji.reuse.teameleven.handlers;

import edu.tongji.reuse.teameleven.handlers.ctrl.StubLoader;
import edu.tongji.reuse.teameleven.handlers.transport.HandlersManager;

import java.util.Scanner;

/**
 * Created by daidongyang on 5/30/16.
 */
public class HandlersEntry {

    public static void main(String[] args){
        HandlersManager handlersManager = new HandlersManager();
        handlersManager.start();
        StubLoader stubLoader = new StubLoader();
        stubLoader.load(handlersManager);
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("close")){
                handlersManager.safeQuit();
                break;
            }
        }
    }
}
