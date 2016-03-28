package client.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ClientLogger {
	private static boolean isConnect;
	private static boolean isLogin;
	private static String logFileName;
	private static int sendNum;
	private static int receiveNum;
	private static BufferedWriter logWriter;
	
	
	public static boolean getIsConnect() {
		return isConnect;
	}
	public static void setIsConnect(boolean _isConnect){
		isConnect = _isConnect;
	}
	
	public static boolean getIsLogin(){
		return isLogin;
	}
	
	public static void setIsLogin(boolean _isLogin){
		isLogin = _isLogin;
	}
	
	public static void updateUsr(String usr){
		logFileName = usr+"log.txt";
		try {
			buildLogWriter();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetSendNum(){
		sendNum = 0;
	}
	
	public static void increaseSendNum(){
		sendNum++;
	}
	
	public static void resetReceiveNum(){
		receiveNum = 0;
	}
	
	public static void increaseReceiveNum(){
		receiveNum++;
	}
	
	public static BufferedWriter buildLogWriter() throws FileNotFoundException{
			logWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(logFileName, true)));
		return logWriter;
	}
	
	public static void writeLoginSuccessful() throws IOException{
		
		if(logWriter == null){
			buildLogWriter();
		}
		logWriter.write("login success!\n");
		logWriter.flush();
	}
	
	public static void writeLoginFailed() throws IOException{
		if(logWriter == null){
			buildLogWriter();
		}
		logWriter.write("login fail!\n");
		logWriter.flush();
	}
	
	public static void writePerMinute() throws IOException{
		if(logWriter == null){
			buildLogWriter();
		}
		logWriter.write("send message : " + sendNum + "\n");
		logWriter.write("receive message : " + receiveNum + "\n");
		logWriter.flush();
	}
	
}
