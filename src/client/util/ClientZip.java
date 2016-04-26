package client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ClientZip {

	public static String separater = File.separator;

	public static void zip(String sourceName, String destinationName) throws FileNotFoundException, IOException {
		if (!destinationName.contains(".zip")) {
			destinationName += ".zip";
		}
		if (sourceName.length() <= 0 || destinationName.length() <= 0) {
			throw new IOException("dir name error");
		}
		if ((sourceName.contains(File.separator) && !sourceName.contains(":"))
				|| (destinationName.contains(File.separator) && !destinationName.contains(":"))) {
			throw new IOException("dir name error");
		}

		FileOutputStream fileWriter = null;
		ZipOutputStream zip = null;

		fileWriter = new FileOutputStream(destinationName);
		zip = new ZipOutputStream(fileWriter);
		
		
		zipFile("", sourceName, zip);

		zip.flush();
		zip.close();
		fileWriter.flush();
		fileWriter.close();
	}

	private static void zipFile(String path, String srcFile, ZipOutputStream ouputZip) throws IOException {

		File folder = new File(srcFile);
		if(!folder.exists()){
			return;
		}
		if (folder.isDirectory()) {
			zipFolder(path, srcFile, ouputZip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			
			synchronized(in){
				ouputZip.putNextEntry(new ZipEntry(path + separater + folder.getName()));
				while ((len = in.read(buf)) > 0) {
					ouputZip.write(buf, 0, len);
				}
			}
			
			in.close();
		}
	}

	private static void zipFolder(String path, String srcFolder, ZipOutputStream outputZip) throws IOException {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				zipFile(folder.getName(), srcFolder + separater + fileName, outputZip);
			} else {
				zipFile(path + separater + folder.getName(), srcFolder + separater + fileName, outputZip);
			}
		}
	}

}
