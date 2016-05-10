package team.eleven.zip;

public class Zip2 {
	private final static int BUFF_SIZE = 1024;
	private int partCount = 0;
	
	public int getPartCount() {
		return partCount;
	}
	public void setPartCount(int partCount) {
		this.partCount = partCount;
	}
	public static int getBuffSize() {
		return BUFF_SIZE;
	}	
	

}
