package edu.tongji.reuse.teameleven.record.ctrl;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import edu.tongji.reuse.teameleven.codependent.base.LoopThread;
import org.slf4j.LoggerFactory;

/**
 * Created by Dai on 6/11/16.
 */
public class LoggerReceiver extends LoopThread {

    @Override
    public void beforeLoop(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);
    }
}
