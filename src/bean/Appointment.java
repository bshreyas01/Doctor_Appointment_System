package bean;

public class Appointment {
	
	private int aID,DoctorId,PatientID;
	private String aDate,aTime;
	public Appointment() {}
	public Appointment(int aID, int doctorId, int patientID, String aDate, String aTime) {
		super();
		this.aID = aID;
		DoctorId = doctorId;
		PatientID = patientID;
		this.aDate = aDate;
		this.aTime = aTime;
	}
	public int getaID() {
		return aID;
	}
	public void setaID(int aID) {
		this.aID = aID;
	}
	public int getDoctorId() {
		return DoctorId;
	}
	public void setDoctorId(int doctorId) {
		DoctorId = doctorId;
	}
	public int getPatientID() {
		return PatientID;
	}
	public void setPatientID(int patientID) {
		PatientID = patientID;
	}
	public String getaDate() {
		return aDate;
	}
	public void setaDate(String aDate) {
		this.aDate = aDate;
	}
	public String getaTime() {
		return aTime;
	}
	public void setaTime(String aTime) {
		this.aTime = aTime;
	}
	
	
}
