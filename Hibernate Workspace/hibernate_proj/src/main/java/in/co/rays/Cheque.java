package in.co.rays;

public class Cheque extends Payment {
	private long chqNumber;
	private String bankName;

	public long getChqNumber() {
		return chqNumber;
	}

	public void setChqNumber(long chqNumber) {
		this.chqNumber = chqNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
