package edu.tongji.reuse.teameleven.record.ctrl;

import edu.tongji.reuse.teameleven.holder.stub.MissedMsgsIntf;
import edu.tongji.reuse.teameleven.record.stub.MonitorControllerIntf;
import octoteam.tahiti.performance.PerformanceMonitor;
import octoteam.tahiti.performance.recorder.CountingRecorder;
import octoteam.tahiti.performance.reporter.LogReporter;
import octoteam.tahiti.performance.reporter.RollingFileReporter;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MonitorController implements MonitorControllerIntf{
    private CountingRecorder receivedNumber;
    private CountingRecorder ignoredNumber;
    private CountingRecorder forwardedNumber;
    private CountingRecorder logsucceedNumber;
    private CountingRecorder logfailedNumber;
    private int saveCycle = 60;
    private PerformanceMonitor monitor;

    public MonitorController(){
        createMonitor();
    }

    private PerformanceMonitor createMonitor() {
        LogReporter reporter = new RollingFileReporter("log/bar-%d{yyyy-MM-dd-HH-mm-ss}.log");
        monitor = new PerformanceMonitor(reporter);
        receivedNumber = new CountingRecorder("Received Number");
        ignoredNumber = new CountingRecorder("Ignored Number");
        forwardedNumber = new CountingRecorder("Forward Number");
        logsucceedNumber = new CountingRecorder("Log Succeed Number");
        logfailedNumber = new CountingRecorder("Login Failed Number");
        monitor.addRecorder(receivedNumber);
        monitor.addRecorder(ignoredNumber);
        monitor.addRecorder(forwardedNumber);
        monitor.addRecorder(logsucceedNumber);
        monitor.addRecorder(logfailedNumber);
        return monitor;
    }

    @Override
    public void increaseReceivedNumber() throws RemoteException{
        if (receivedNumber != null) {
            receivedNumber.record();
        }
    }

    @Override
    public void increaseIgnoredNumber() throws RemoteException{
        if (ignoredNumber != null) {
            ignoredNumber.record();
        }
    }

    @Override
    public void increaseForwardedNumber() throws RemoteException{
        if (forwardedNumber != null) {
            forwardedNumber.record();
        }
    }

    @Override
    public void increaseLogsucceedNumber() throws RemoteException{
        if (logsucceedNumber != null) {
            logsucceedNumber.record();
        }
    }

    @Override
    public void increaseLogfailedNumber() throws RemoteException{
        if (logfailedNumber != null) {
            logfailedNumber.record();
        }
    }

    @Override
    public void setSaveCycle(int _saveCycle) throws RemoteException{
        saveCycle = _saveCycle;
    }

    @Override
    public void startRecord() throws RemoteException{
        if (monitor == null) {
            createMonitor();
        }
        monitor.start(saveCycle, TimeUnit.SECONDS);
    }

    @Override
    public void stopRecord() throws RemoteException{
        if(monitor == null){
            createMonitor();
        }
        monitor.stop();
    }


    public PerformanceMonitor getMonitor() {
        if (monitor == null) {
            createMonitor();
        }
        return monitor;
    }

    @Override
    public void reset() throws RemoteException{
        receivedNumber.reset();
        ignoredNumber.reset();
        forwardedNumber.reset();
        logsucceedNumber.reset();
        logfailedNumber.reset();
    }


}
