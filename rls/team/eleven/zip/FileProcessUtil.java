package team.eleven.zip;

class FileProcessUtil {
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

}
