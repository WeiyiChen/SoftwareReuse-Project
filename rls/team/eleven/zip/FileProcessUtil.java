package team.eleven.zip;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * 
 * @author daidongyang
 *	May 10, 2016
 *
 */
class FileProcessUtil {
	
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
			throw new RuntimeException("please makesure parameters in method getRelatedFies valid!");
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
}
