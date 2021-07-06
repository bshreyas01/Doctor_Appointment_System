package bean;

public class Doctor {

	private int did;
	private String dName;
	private String dGender;
	private int dAge;
	private String dMobile;
	private String dEmail;
	private String dPassword;
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	public Doctor(int did, String dName, String dGender, int dAge, String dMobile, String dEmail, String dPassword) {
		super();
		this.did = did;
		this.dName = dName;
		this.dGender = dGender;
		this.dAge = dAge;
		this.dMobile = dMobile;
		this.dEmail = dEmail;
		this.dPassword = dPassword;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdGender() {
		return dGender;
	}
	public void setdGender(String dGender) {
		this.dGender = dGender;
	}
	public int getdAge() {
		return dAge;
	}
	public void setdAge(int dAge) {
		this.dAge = dAge;
	}
	public String getdMobile() {
		return dMobile;
	}
	public void setdMobile(String dMobile) {
		this.dMobile = dMobile;
	}
	@Override
	public String toString() {
		return "Doctor [did=" + did + ", dName=" + dName + ", dGender=" + dGender + ", dAge=" + dAge + ", dMobile="
				+ dMobile + ", dEmail=" + dEmail + ", dPassword=" + dPassword + "]";
	}
	public String getdEmail() {
		return dEmail;
	}
	public void setdEmail(String dEmail) {
		this.dEmail = dEmail;
	}
	public String getdPassword() {
		return dPassword;
	}
	public void setdPassword(String dPassword) {
		this.dPassword = dPassword;
	}
	
}
