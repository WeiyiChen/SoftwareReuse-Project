package edu.tongji.reuse.teameleven.handlers;

import edu.tongji.reuse.teameleven.handlers.ctrl.StubLoader;
import edu.tongji.reuse.teameleven.handlers.transport.HandlersManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by daidongyang on 5/30/16.
 */
public class HandlersEntry {

    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(HandlersEntry.class);
        logger.info("start HandlersEntry");
        HandlersManager handlersManager = new HandlersManager();
        handlersManager.start();
        StubLoader stubLoader = new StubLoader();
        stubLoader.load(handlersManager);
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("close")){
                logger.info("quit HandlersEntry");
                handlersManager.safeQuit();
                break;
            }
        }
    }
}
