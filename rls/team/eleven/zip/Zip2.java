package team.eleven.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import team.eleven.file.limit.FilesLimitSizeOutputStream;

//这个文件代码有点乱，抽空再改吧
/**
 * 
 * Zip and unzip the file and folder
 *
 */
public class Zip2 {

	/**
	 * Platform-independent File separator
	 */
	public static String separater = File.separator;

	/**
	 * zip the sourceName file or folder to be a zip file named destinationName
	 * 
	 * @param sourceName
	 *            - the file or folder to be compressed under the current
	 *            working directory, if you run a genereated Executable jar
	 *            package, the current working directory is the jar's current
	 *            directory, and if you run the code in an IDE or by command
	 *            line, the current working directory is the project path, such
	 *            as ../Projects/SoftwareResuse.
	 * @param destinationName
	 *            - the target compressed zip file's name, a.zip or a is equal.
	 *            a file a.zip will generated if you set destinationName "a" or
	 *            "a.zip".
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void zip(String sourceName, String destinationName, long fileLimit) throws FileNotFoundException, IOException {
		if (!destinationName.contains(".zip")) {
			destinationName += ".zip";
		}
		if (sourceName.length() <= 0 || destinationName.length() <= 0) {
			throw new IOException("dir name error");
		}
		if ((sourceName.contains(File.separator) && !sourceName.contains(":"))
				|| (destinationName.contains(File.separator) && !destinationName.contains(":"))) {
			try{
				new File(destinationName).getParentFile().mkdirs();
			}catch(Exception ioe){
				throw new IOException("dir name error");
			}
			
		}

		FilesLimitSizeOutputStream fileWriter = null;
		ZipOutputStream zip = null;

		fileWriter = new FilesLimitSizeOutputStream(destinationName, fileLimit);
		zip = new ZipOutputStream(fileWriter);

		zipFile("", sourceName, zip, fileLimit);

		zip.flush();
		zip.close();
		fileWriter.flush();
		fileWriter.close();
	}

	/**
	 * zip the current file or folder named srcFile to be outputZip
	 * 
	 * @param path
	 *            current directory path, a directory path prefix
	 * @param srcFile
	 *            a folder or file to be compressed, the absolute file path of
	 *            source file is "path + separator + srcFile"
	 * @param outputZip
	 *            ZipOutputStream, will generate zip file according to this
	 *            ZipOutputStream.
	 * @throws IOException
	 */
	private static void zipFile(String path, String srcFile, ZipOutputStream outputZip, long fileLimit) throws IOException {

		File folder = new File(srcFile);
		if (!folder.exists()) {
			return;
		}
		if (folder.isDirectory()) {
			zipFolder(path, srcFile, outputZip, fileLimit);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);

			synchronized (in) {
				outputZip.putNextEntry(new ZipEntry(path + separater + folder.getName()));
				while ((len = in.read(buf)) > 0) {
					outputZip.write(buf, 0, len);
				}
			}

			in.close();
		}
	}

	/**
	 * Zip every folder and file under declared folder
	 * 
	 * @param path
	 *            prefix path
	 * @param srcFolder
	 *            the folder under which the files and folders will be
	 *            compressed
	 * @param outputZip
	 *            ZipOutputStream, will generate zip file according to this
	 *            ZipOutputStream.
	 * @throws IOException
	 */
	private static void zipFolder(String path, String srcFolder, ZipOutputStream outputZip, long fileLimit) throws IOException {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				zipFile(folder.getName(), srcFolder + separater + fileName, outputZip, fileLimit);
			} else {
				zipFile(path + separater + folder.getName(), srcFolder + separater + fileName, outputZip, fileLimit);
			}
		}
	}

	/**
	 * Unzip zip file
	 * 
	 * @param zipFile
	 *            input zip file
	 * @param output
	 *            zip file output folder
	 * @throws IOException
	 */
	public static void unZip(String zipFile, String outputFolder) throws IOException {
		if (!zipFile.contains(".zip")) {
			zipFile += ".zip";
		}
		byte[] buffer = new byte[1024];
		ZipInputStream zis = null;
		FileOutputStream fos = null;
		try {
			if(outputFolder.equals("")||outputFolder==null){
				outputFolder = ".";
			}
			File folder = new File(outputFolder);
			if(!folder.exists()){
				folder.mkdir();
			}

			// get the zip file content
			zis = new ZipInputStream(new FileInputStream(zipFile));
			
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);
				
				System.out.println("file unzip : " + newFile.getAbsoluteFile());
				
				// create all non exists folders
				// else you will hit FileNotFoundException for compressed
				// folder
				new File(newFile.getParent()).mkdirs();

				fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
//			System.out.println("Done");

		} finally{
			if(fos != null){
				fos.close();
			}
			if(zis != null){
				zis.close();
			}
		}
	}

}
