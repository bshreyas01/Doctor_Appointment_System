package bean;

public class Availability {
	String DoctorDate;
	String InTime;
	String OutTime;
	int DoctorId;
//	public Availability(String doctorDate, String inTime, String outTime, int doctorId) {
//		super();
//		DoctorDate = doctorDate;
//		InTime = inTime;
//		OutTime = outTime;
//		DoctorId = doctorId;
//	}
	public String getDoctorDate() {
		return DoctorDate;
	}
	public void setDoctorDate(String doctorDate) {
		DoctorDate = doctorDate;
	}
	public String getInTime() {
		return InTime;
	}
	public void setInTime(String inTime) {
		InTime = inTime;
	}
	public String getOutTime() {
		return OutTime;
	}
	public void setOutTime(String outTime) {
		OutTime = outTime;
	}
	public int getDoctorId() {
		return DoctorId;
	}
	public void setDoctorId(int doctorId) {
		DoctorId = doctorId;
	}

}
