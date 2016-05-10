package team.eleven.record;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileAccess {

	private PrintWriter pwa;
	private FileOutputStream fosa;

	public static String readFile(String path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	public static boolean fileOverWrite(String path, String content) {
		boolean succeed = true;
		try {
			FileOutputStream fos = new FileOutputStream(path, false);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(content);
			pw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			succeed = false;
			e.printStackTrace();
		} catch (IOException e) {
			succeed = false;
			e.printStackTrace();
		}
		return succeed;

	}

	public boolean createAppendFile(String path) {
		boolean succeed = true;
		try {
			fosa = new FileOutputStream(path, true);
			pwa = new PrintWriter(fosa);
		} catch (FileNotFoundException e) {
			succeed = false;
			e.printStackTrace();
		} 
		return succeed;
	}

	public boolean append(String content) {
		pwa.println(content);
		pwa.flush();
		return !pwa.checkError();
	}
	
	public void closeFile(){
		pwa.close();
		try {
			fosa.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
