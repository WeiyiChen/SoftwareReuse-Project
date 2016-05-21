package edu.tongji.reuse.teameleven.server.ctrl;

import java.util.concurrent.TimeUnit;

import octoteam.tahiti.performance.PerformanceMonitor;
import octoteam.tahiti.performance.recorder.CountingRecorder;
import octoteam.tahiti.performance.reporter.LogReporter;
import octoteam.tahiti.performance.reporter.RollingFileReporter;

public class ServerMonitorController {
	private static CountingRecorder receivedNumber;
	private static CountingRecorder ignoredNumber;
	private static CountingRecorder forwardedNumber;
	private static CountingRecorder logsucceedNumber;
	private static CountingRecorder logfailedNumber;
	private static int saveCycle = 60;
	private static PerformanceMonitor monitor;
	
	private synchronized static PerformanceMonitor createMonitor(){
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
	
	public static void increaseReceivedNumber(){
		if(receivedNumber != null){
			receivedNumber.record();
		}
	}
	
	public static void increaseIgnoredNumber(){
		if(ignoredNumber != null){
			ignoredNumber.record();
		}
	}
	
	public static void increaseForwardedNumber(){
		if(forwardedNumber != null){
			forwardedNumber.record();
		}
	}
	
	public static void increaseLogsucceedNumber(){
		if(logsucceedNumber != null){
			logsucceedNumber.record();
		}
	}
	
	public static void increaseLogfailedNumber(){
		if(logfailedNumber != null){
			logfailedNumber.record();
		}
	}
	
	public static void setSaveCycle(int _saveCycle){
		saveCycle = _saveCycle;
	}
	
	public static void startRecord(){
		if(monitor == null){
			createMonitor();
		}
		monitor.start(saveCycle, TimeUnit.SECONDS);
	}
	
	public static PerformanceMonitor getMonitor(){
		if(monitor == null){
			createMonitor();
		}
		return monitor;
	}
	
	public static void reset(){
		receivedNumber.reset();
		ignoredNumber.reset();
		forwardedNumber.reset();
		logsucceedNumber.reset();
		logfailedNumber.reset();
	}

}
