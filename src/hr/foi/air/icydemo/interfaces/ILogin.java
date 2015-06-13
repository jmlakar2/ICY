package hr.foi.air.icydemo.interfaces;

import hr.foi.air.icydemo.types.AutentReturnType;
import android.content.Context;

public interface ILogin {
	/**
	 * �alje autentifikacijske podatke na servis i vra�a objekt klase
	 * AutentReturnType (atribut kod treba biti 1 ako je autentikacija uspjela, 0 ako nije)
	 * 
	 * @param user Korisni�ko ime korisnika
	 * @param pass Lozinka korisnika
	 * @return boolean tip podatka koji govori da li je autentifikacija uspjela
	 */
	public AutentReturnType autent (String user, String pass);
	
	/**
	 * Metoda sprema kod generiran na web servisu i korisni�ko ime povezano sa njim
	 * @param user korisni�ko ime korisnika
	 * @param kod generirani kod
	 * @param ctx
	 */
	public void spremiKod (String user, String kod, Context ctx);
	
	/**
	 * Metoda vra�a kod spremljeni lokalno
	 * @param ctx
	 * @return kod ili null ako nema spremljenog koda
	 */
	public String vratiKod (Context ctx);
	
	/**
	 * Metoda vra�a korisni�ko ime spremljeni lokalno
	 * @param ctx
	 * @return kod ili null ako nema spremljenog korisni�kog imena
	 */
	public String vratiUser (Context ctx);
	
	/**
	 * Metoda postavlja vrijednosti kod i korsin�ko ime na null u lokalnoj memoriji, a status postavlja na 0
	 * @param ctx
	 */
	public void logout (Context ctx);
}
