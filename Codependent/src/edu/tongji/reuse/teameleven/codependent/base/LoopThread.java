package edu.tongji.reuse.teameleven.codependent.base;

/**
 * Created by daidongyang on 5/22/16.
 */
public abstract class LoopThread extends Thread {

    @Override
    public void run(){
        beforeLoop();
        while(!Thread.currentThread().isInterrupted()){
            loop();
        }
        afterLoop();
    }


    /**
     * you can init some variables before loop
     */
    public void beforeLoop(){

    }

    /**
     * you can add loop behavior here in a subclass, such as listening to
     * a client socket
     */
    public void loop(){

    }

    /**
     * you can do something after the loop
     */
    public void afterLoop(){

    }

    /**
     * quit this thread safely
     */
    public void safeQuit(){
        interrupt();
    }
}
