package hr.foi.air.icydemo.types;

public class ZaposleniciReturnType {
	private String ime;
	private String prezime;
	private String e_mail;
	private String kontakt;
	private String radno_mjesto;
	
	public ZaposleniciReturnType (String ime, String prezime, String e_mail, String kontakt, String radno_mjesto){
		setIme(ime);
		setPrezime(prezime);
		setE_mail(e_mail);
		setKontakt(kontakt);
		setRadno_mjesto(radno_mjesto);
	}
	
	public ZaposleniciReturnType(){
		
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getKontakt() {
		return kontakt;
	}
	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}
	public String getRadno_mjesto() {
		return radno_mjesto;
	}
	public void setRadno_mjesto(String radno_mjesto) {
		this.radno_mjesto = radno_mjesto;
	}

}
