package edu.tongji.reuse.teameleven.processor;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.processor.ctrl.LicenseCtrl;
import edu.tongji.reuse.teameleven.processor.ctrl.StubLoader;
import edu.tongji.reuse.teameleven.processor.ctrl.UsersInfoCtrl;

import java.util.Scanner;

/**
 * Created by daidongyang on 5/30/16.
 */
public class ProcessorEntry {
//    UsersInfoCtrl usersInfo;
    public static void main(String[] args){
        UsersInfoCtrl usersInfoCtrl = new UsersInfoCtrl();
        LicenseCtrl licenseCtrl = new LicenseCtrl();
        licenseCtrl.setMaxMessagePerLogin(ConfigCtrl.getConfig().getMaxMessagesPerLogin());
        licenseCtrl.setMaxMessagePerSecond(ConfigCtrl.getConfig().getMaxMessagesPerSecond());
        StubLoader stubLoader = new StubLoader();
        stubLoader.setUsersInfoCtrl(usersInfoCtrl);
        stubLoader.setLicenseCtrl(licenseCtrl);
        stubLoader.load();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("close")){
                System.out.println("bye!");
                System.exit(0);
            }

        }
    }
}
