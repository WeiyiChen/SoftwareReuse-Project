package edu.tongji.reuse.teameleven.processor;

import edu.tongji.reuse.teameleven.processor.ctrl.StubLoader;
import edu.tongji.reuse.teameleven.processor.model.UsersInfo;

import java.util.Scanner;

/**
 * Created by daidongyang on 5/30/16.
 */
public class ProcessorEntry {
//    UsersInfo usersInfo;
    public static void main(String[] args){
        UsersInfo usersInfo = new UsersInfo();
        StubLoader stubLoader = new StubLoader(usersInfo);
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
