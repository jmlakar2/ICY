package hr.foi.air.icydemo.db;

import java.util.Date;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table (name = "VrijemePosao")
public class VrijemePosao extends Model{
	@Column (name = "Korisnici")
	private Korisnici korisnici;
	@Column (name = "pocetak")
	private Date pocetak;
	@Column (name = "kraj")
	private Date kraj;
	
	public VrijemePosao() {
		super();
	}
	
	public VrijemePosao (Date pocetak, Korisnici korisnik) {
		this.pocetak = pocetak;
		this.korisnici = korisnik;
		this.kraj = new Date(0);
	}
	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}
	public Korisnici getKorisnik() {
		return korisnici;
	}
	public Date getKraj() {
		return kraj;
	}
	public Date getPocetak() {
		return pocetak;
	}
}
