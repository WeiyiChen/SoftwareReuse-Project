package edu.tongji.reuse.teameleven.record;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.record.ctrl.ReZipLogController;
import edu.tongji.reuse.teameleven.record.ctrl.StubLoader;
import edu.tongji.reuse.teameleven.record.ctrl.ZipLogController;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by daidongyang on 6/1/16.
 */
public class RecordEntry {
    public static void main(String[] args) throws RemoteException {
        StubLoader stubLoader = new StubLoader();
        stubLoader.load();
        stubLoader.getMonitorController().setSaveCycle(ConfigCtrl.getConfig().getSaveCycle());
        stubLoader.getMonitorController().startRecord();
        new ZipLogController().setAndStart(ConfigCtrl.getConfig().getZipCycle());
        new ReZipLogController().setAndStart(ConfigCtrl.getConfig().getReZipCycle());
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("quit")){
                stubLoader.getMonitorController().stopRecord();
                System.out.println("bye!");
                System.exit(0);
            }
        }
    }
}
