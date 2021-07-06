package bean;

public class Patient {

	private String pName;
	private String pGender;
	private int pAge;
	private int pid;

	private String pMobile;
	private String pEmail;
	private String pPassword;
	public Patient() {}
	public Patient(String pName, String pGender, int pAge, String pMobile, String pEmail, String pPassword) {
		super();
		this.pName = pName;
		this.pGender = pGender;
		this.pAge = pAge;
		this.pMobile = pMobile;
		this.pEmail = pEmail;
		this.pPassword = pPassword;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpGender() {
		return pGender;
	}
	public void setpGender(String pGender) {
		this.pGender = pGender;
	}
	public int getpAge() {
		return pAge;
	}
	public void setpAge(int pAge) {
		this.pAge = pAge;
	}
	public String getpMobile() {
		return pMobile;
	}
	public void setpMobile(String pMobile) {
		this.pMobile = pMobile;
	}
	public String getpEmail() {
		return pEmail;
	}
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}
	public String getpPassword() {
		return pPassword;
	}
	public void setpPassword(String pPassword) {
		this.pPassword = pPassword;
	}
	@Override
	public String toString() {
		return "Patient [pName=" + pName + ", pGender=" + pGender + ", pAge=" + pAge + ", pMobile=" + pMobile
				+ ", pEmail=" + pEmail + ", pPassword=" + pPassword + "]";
	}
	
}
