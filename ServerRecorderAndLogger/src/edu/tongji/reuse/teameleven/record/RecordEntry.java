package edu.tongji.reuse.teameleven.record;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.record.ctrl.LoggerReceiver;
import edu.tongji.reuse.teameleven.record.ctrl.ReZipLogController;
import edu.tongji.reuse.teameleven.record.ctrl.StubLoader;
import edu.tongji.reuse.teameleven.record.ctrl.ZipLogController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by daidongyang on 6/1/16.
 */
public class RecordEntry {
    public static void main(String[] args) throws RemoteException {
        Logger logger = LoggerFactory.getLogger(RecordEntry.class);
        logger.info("start RecordEntry");
        StubLoader stubLoader = new StubLoader();
        stubLoader.load();
        stubLoader.getMonitorController().setSaveCycle(ConfigCtrl.getConfig().getSaveCycle());
        stubLoader.getMonitorController().startRecord();
        new ZipLogController().setAndStart(ConfigCtrl.getConfig().getZipCycle());
        new ReZipLogController().setAndStart(ConfigCtrl.getConfig().getReZipCycle());
        LoggerReceiver loggerReceiver = new LoggerReceiver();
        loggerReceiver.start();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("quit")){
                stubLoader.getMonitorController().stopRecord();
                loggerReceiver.safeQuit();
                System.out.println("bye!");
                logger.info("exit RecordEntry");
                System.exit(0);
            }
        }
    }
}
