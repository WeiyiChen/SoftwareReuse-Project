package edu.tongji.reuse.teameleven.file.limit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;

/**
 * 
 * @author daidongyang
 *	May 11, 2016
 *
 */
public class FilesLimitSizeInputStream extends SequenceInputStream {
	
	public static class EnumerationFileInputStream implements Enumeration<FileInputStream>{
		int index = 0;
		FileInputStream[] fileStreams = null;
		
		EnumerationFileInputStream(final String[] relatedFilePathes) throws FileNotFoundException{
			fileStreams = new FileInputStream[relatedFilePathes.length];
			for(int i = 0; i < relatedFilePathes.length; i++){
				fileStreams[i] = new FileInputStream(relatedFilePathes[i]);
			}
		}

		@Override
		public boolean hasMoreElements() {
			// TODO Auto-generated method stub
			return index < fileStreams.length;
		}

		@Override
		public FileInputStream nextElement() {
			// TODO Auto-generated method stub
//			FileInputStream fis = FileStreams
			return fileStreams[index++];
		}		
	}
	
	public FilesLimitSizeInputStream(String originFilePath) throws FileNotFoundException{
		this(FileProcessUtil.getRelatedFiles(originFilePath));
	}
	
	public FilesLimitSizeInputStream(final String[] relatedFilePathes) throws FileNotFoundException{	
		super(new EnumerationFileInputStream(relatedFilePathes));	
	}

	public FilesLimitSizeInputStream(Enumeration<? extends InputStream> e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

}
