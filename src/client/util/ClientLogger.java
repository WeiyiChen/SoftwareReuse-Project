package client.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class ClientLogger {
	private static boolean isConnect;
	private static boolean isLogin;
	private static String logFileName = "log.txt";
	private static int sendNum;
	private static int receiveNum;
	private static BufferedWriter logWriter;
	private static int loginSucceedCount = 0;
	private static int loginFailedCount = 0;
	
	
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
	
	public static void writeLoginSuccessful(String usr) throws IOException{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("log.txt", true)));
		out.write(new Date().toString() + "\n");
		out.write(usr + " login successfully!\n");
		out.flush();
		out.close();
		updateUsr(usr);
		logWriter.write(new Date().toString() + "\n");
		logWriter.write("login successfully!\n\n");
		logWriter.flush();
	}
	
	public static void writeLoginFailed(String usr) throws IOException{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("log.txt", true)));
		out.write(new Date().toString() + "\n");
		out.write(usr + " login failed!\n\n");
		out.flush();
		out.close();
	}
	
	public static void writePerMinute() throws IOException{
		if(!isLogin){
			return;
		}
		if(logWriter == null){
			buildLogWriter();
		}
		logWriter.write(new Date().toString() + "\n");
		logWriter.write("send message : " + sendNum + "\n");
		logWriter.write("receive message : " + receiveNum + "\n");
		logWriter.write("\n");
		logWriter.flush();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("logincountlog.txt", true)));
		out.write(new Date().toString() + "\n");
		out.write("login succeed count : " + loginSucceedCount + "\n");
		out.write("login fail cout : " + loginFailedCount + "\n");
		out.write("\n");
		out.flush();
		out.close();
	}
	public static void resetLoginSucceedCount(){
		loginSucceedCount = 0;
	}
	public static void increaseLoginSucceedCount(){
		loginSucceedCount++;
	}
	public static void resetLoginFailedCount(){
		loginFailedCount = 0;
	}
	
	public static void increaseLoginFailedCount(){
		loginFailedCount++;
	}
	public static void closeLogWriter() throws IOException{
		if(logWriter != null){
			logWriter.close();
			logWriter = null;
		}
	}
	
	public static String outputAttribute(){
		String ic = isConnect?"true":"false";
		String il = isLogin?"true":"false";
		return "isConnect" + ic + "\n" 
				+ "isLogin" + il + "\n"
				+ "logFileName" + logFileName + "\n"
				+ "sendNum" + sendNum + "\n"
				+ "receiveNum" + receiveNum + "\n"
				+ "loginSucceedCount" + loginSucceedCount + "\n"
				+ "loginFailedCount" + loginFailedCount + "\n";
				
	}
	
	public String toString(){
		return outputAttribute();
	}
	
	
}
