package ie.gmit.queues;

public class Request {
	private String cypherText;
	private int maxKeyLength;
	private long jobNumber;
	
	
	public Request(String cypherText, int maxKeyLength, long jobNumber) {
		this.cypherText = cypherText;
		this.maxKeyLength = maxKeyLength;
		this.jobNumber = jobNumber;
	}
	
	public String getCypherText() {
		return cypherText;
	}
	public void setCypherText(String cypherText) {
		this.cypherText = cypherText;
	}
	public int getMaxKeyLength() {
		return maxKeyLength;
	}
	public void setMaxKeyLength(int maxKeyLength) {
		this.maxKeyLength = maxKeyLength;
	}
	public long getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(long jobNumber) {
		this.jobNumber = jobNumber;
	}
	
	

}
