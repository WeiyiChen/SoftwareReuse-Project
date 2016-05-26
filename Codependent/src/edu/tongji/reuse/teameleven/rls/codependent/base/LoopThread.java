package edu.tongji.reuse.teameleven.rls.codependent.base;

/**
 * Created by daidongyang on 5/22/16.
 */
public abstract class LoopThread extends Thread {

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            loop();
        }
    }

    /**
     * you can add loop behavior here in a subclass, such as listening to
     * a client socket
     */
    public void loop(){

    }

    /**
     * quit this thread safely
     */
    public void safeQuit(){
        interrupt();
    }
}
