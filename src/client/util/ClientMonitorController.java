package client.util;

import java.util.concurrent.TimeUnit;

import octoteam.tahiti.performance.PerformanceMonitor;
import octoteam.tahiti.performance.recorder.CountingRecorder;
import octoteam.tahiti.performance.reporter.LogReporter;
import octoteam.tahiti.performance.reporter.RollingFileReporter;

public class ClientMonitorController {
	private static PerformanceMonitor monitor;
	private static CountingRecorder loginTimes;
	private static CountingRecorder logfailTimes;
	private static CountingRecorder sendNum;
	private static CountingRecorder receiveNum;
	private static int saveCycle;
	
	private synchronized static PerformanceMonitor createMonitor(){
		//不知是否能改变目录
		LogReporter reporter = new RollingFileReporter("logclient/bar-%d{yyyy-MM-dd-HH-mm-ss}.log");
		monitor = new PerformanceMonitor(reporter);
		loginTimes = new CountingRecorder("User login times");
		logfailTimes = new CountingRecorder("User login failed times");
		sendNum = new CountingRecorder("User send message times");
		receiveNum = new CountingRecorder("User receive message times");
		monitor.addRecorder(loginTimes);
		monitor.addRecorder(logfailTimes);
		monitor.addRecorder(sendNum);
		monitor.addRecorder(receiveNum);
		return monitor;
	}
	
	public static void increaseLoginTimes(){
		if(loginTimes == null)
			return;
		loginTimes.record();
	}
	
	public static void increaseLoginFailedTimes(){
		if(logfailTimes == null)
			return;
		logfailTimes.record();
	}
	
	public static void increaseSendNum(){
		if(sendNum == null)
			return;
		sendNum.record();
	}
	
	public static void increaseReceiveNum(){
		if(receiveNum == null)
			return;
		receiveNum.record();
	}
	
	public static void setSaveCycle(int _s){
		saveCycle = _s;
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
		loginTimes.reset();
		logfailTimes.reset();
		sendNum.reset();
		receiveNum.reset();
	}

}
