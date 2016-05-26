package edu.tongji.reuse.teameleven.file.limit;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * 
 * @author daidongyang
 *	May 10, 2016
 *
 */
public class FileProcessUtil {
	
	/**
	 * split the filename into prefileName(fileName without extension) and extension fileName
	 * if you input "test.out", returns {"test",".out"}
	 * @param fileNameWithEx - a file name with extension, such as "/usr/local/test.out", "a.b" etc.
	 * @return - String[] results. results[0] is the prefileName, results[1] is the extension name
	 */
	public static String[] getFilePreNameAndEx(String fileNameWithEx){
		String[] result = new String[2];
		if((fileNameWithEx != null) && (fileNameWithEx.length() > 0)){
			int dotIndex = fileNameWithEx.lastIndexOf('.');
			if(dotIndex > 0 && dotIndex < fileNameWithEx.length()){
				result[0] = fileNameWithEx.substring(0,dotIndex);
				result[1] = fileNameWithEx.substring(dotIndex, fileNameWithEx.length());
			}else{
				// the file has no extension name
				result[0] = fileNameWithEx;
				result[1] = "";
			}
		}else{
			// if the fileNameWithEx == null or fileNemWithEx == "", throw RuntimeException
			throw new RuntimeException("please make sure fileName valid");
		}
		return result;
	}
	
	/**
	 * if you input originFilePath equals /User/bar.txt
	 * then the method will list all the file matched /User/bar*.txt
	 * for example, Input "/Users/d/Desktop/tmp/testdir/bar.zip"
	 * return a String[] contains {"/Users/d/Desktop/tmp/testdir/bar.zip",
	 * "/Users/d/Desktop/tmp/testdir/bar-app1.zip",
	 * "/Users/d/Desktop/tmp/testdir/bar-app2.zip"}
	 * @param originFilePath - absolute file path.
	 * @return - an array of absolute file matched the originFile
	 */
	public static String[] getRelatedFiles(String originFilePath){
		String[] results = null;
		int fileSeperatorIndex = originFilePath.lastIndexOf(File.separatorChar);
		if(fileSeperatorIndex < 0 || fileSeperatorIndex >= originFilePath.length()){
			throw new RuntimeException("illegal input: " + originFilePath);
		}
		
		String dirPath = originFilePath.substring(0, fileSeperatorIndex);
		String originFileName = originFilePath.substring(fileSeperatorIndex + 1,originFilePath.length());
		File dir = new File(dirPath);
		results = dir.list(relatedFilter(originFileName));
		
		String[] originPreAndExNames = getFilePreNameAndEx(originFileName);
		String exName = originPreAndExNames[1];
		int exNameLen = exName.length();
	
		for(int i = 0; i < results.length; i++){
			results[i] = results[i].substring(0,results[i].length()-exNameLen);
		}
		
		Arrays.sort(results, String.CASE_INSENSITIVE_ORDER);
		
		for(int i = 0; i < results.length; i++){
			results[i] = dirPath + File.separator + results[i] + exName;
		}
		
		return results;
		
	}
	
	
	/**
	 * list origin files
	 * @param dirOrFilePath - directory under with the origin files will be list, 
	 * or file under whose directory the origin files will be list
	 * @return
	 */
	public static String[] getOriginFiles(String dirOrFilePath, String appendIdentifier){
		String[] results = null;
		File dir = null;
		File testF = new File(dirOrFilePath);
		String dirPath = null;
		if(testF.isDirectory()){
			dir = testF;
			if(dirOrFilePath.charAt(dirOrFilePath.length()-1) == File.separatorChar){
				dirPath = dirOrFilePath.substring(0, dirOrFilePath.length() - 1);
			}else{
				dirPath = dirOrFilePath;
			}
		}else{
			int fileSeperatorIndex = dirOrFilePath.lastIndexOf(File.separatorChar);
			dirPath = dirOrFilePath.substring(0, fileSeperatorIndex);
			dir = new File(dirPath);
			if(!dir.exists()||!dir.isDirectory()){
				throw new RuntimeException("illegal input: " + dirOrFilePath);
			}
		}
		results = dir.list(originFilter(appendIdentifier));
		for(int i = 0; i < results.length; i++){
			results[i] = dirPath + File.separator + results[i];
		}
		return results;
	}
	
	/**
	 * please make sure originFileName is the name in directory instead of abusolute path.
	 * @param originFileName
	 * @return
	 */
	public static FilenameFilter relatedFilter(final String originFileName){
		return new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				boolean result = false;
				File f = new File(dir.getAbsolutePath() + name);
				if(f.isDirectory()){
					return false;
				}
				String[] checkNames = getFilePreNameAndEx(originFileName);
				String[] names = getFilePreNameAndEx(name);
				if(names[0].startsWith(checkNames[0]) && names[1].equals(checkNames[1])){					
					result = true;
				}
				return result;
			}};
	
	
	}
	
	/**
	 * filter the origin file (doesn't contains the append identifier)
	 * @param appendIdentifier - the identifier to demonstrate append file
	 * @return
	 */
	public static FilenameFilter originFilter(final String appendIdentifier){
		return new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.contains(appendIdentifier)){
					return false;
				}
				File f = new File(dir.getAbsolutePath() + File.separator + name);
				if(f.isDirectory()){
					return false;
				}
				return true;
			}
			
		};
	}
	
	/**
	 * get a total size of a directory or a size of a file
	 * @param f - file or dirctory to get size
	 * @return - file size in unit of byte
	 */
	public static long getLength(File f){
		
		if(f.isFile()){
			return f.length();
		}
		if(f.isDirectory()){
			//The return value is unspecified if this pathname denotes a directory.
//			long length = f.length();
			long length = 0;
			for(File subf : f.listFiles()){
				length += getLength(subf);
			}
			return length;
		}
		else{
			return 0;
		}
	}
	
	/**
	 * get a total size of a directory or a size of a file
	 * @param dirOrFilePath - the file or dirctory's path you want to measure size
	 * @return - file size in unit of byte
	 */
	public static long getLength(String dirOrFilePath){
		File f = new File(dirOrFilePath);
		return getLength(f);
	}
}
