package edu.tongji.reuse.teameleven.holder;

import edu.tongji.reuse.teameleven.holder.ctrl.StubLoader;

import java.util.Scanner;

/**
 * Created by daidongyang on 5/31/16.
 */
public class HolderEntry {
    public static void main(String[] args){
        StubLoader stubLoader = new StubLoader();
        stubLoader.load();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("close")){
                System.out.println("Holder module is exiting! Bye!");
                System.exit(0);
            }
        }
    }
}
