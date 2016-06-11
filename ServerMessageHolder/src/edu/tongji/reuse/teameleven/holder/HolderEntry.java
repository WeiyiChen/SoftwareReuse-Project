package edu.tongji.reuse.teameleven.holder;

import edu.tongji.reuse.teameleven.holder.ctrl.StubLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by daidongyang on 5/31/16.
 */
public class HolderEntry {
    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(HolderEntry.class);
        logger.info("start HolderEntry");
        StubLoader stubLoader = new StubLoader();
        stubLoader.load();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("close")){
                logger.info("Holder module is exiting! Bye!");
                System.exit(0);
            }
        }
    }
}
