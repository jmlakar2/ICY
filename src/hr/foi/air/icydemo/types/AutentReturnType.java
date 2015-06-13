package hr.foi.air.icydemo.types;

public class AutentReturnType {
	private int status;
	private String user;
	private String kod;
	
	public AutentReturnType (int status, String user, String kod) {
		setStatus(status);
		setUser(user);
		setKod(kod);;
	}
	
	public AutentReturnType () {
		
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
}
