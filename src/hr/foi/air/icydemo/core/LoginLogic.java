package hr.foi.air.icydemo.core;

import hr.foi.air.icydemo.factory.LoginFactory;
import hr.foi.air.icydemo.interfaces.ILogin;
import hr.foi.air.icydemo.types.AutentReturnType;

import java.util.ArrayList;

import android.content.Context;

public class LoginLogic {
	private ILogin login;
	
	/** 
	 * ovisno o vrijednosti varijable odabir instancira se objekt željenog plugina za prijavu (implementacija suèelja Ilogin)
	 * @param odabir
	 */
	public LoginLogic (int odabir) {
		LoginFactory loginFactory = new LoginFactory();
		login = loginFactory.odaberi_prijavu(odabir);
	}
	
	/**
	 * provjerava jesu li toèni korisnièki podaci, ako jesu sprema generirani kod i korisnièko ime 
	 * u SharedPreferences
	 * 
	 * @param user
	 * @param pass
	 * @param ctx
	 * @return true ako je autentikacija uspjela, false u suprotnom
	 */
	public boolean provjeraPodaci (String user, String pass, Context ctx) {
		AutentReturnType podaci = new AutentReturnType();
		
		podaci = login.autent(user, pass);
		if (podaci.getStatus() == 1) {
			login.spremiKod(podaci.getUser(), podaci.getKod(), ctx);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Provjerava da li je korisnik logiran
	 * 
	 * @param ctx
	 * @return true ako je logiran, false ako nije
	 */
	
	public boolean provjeraLogiran (Context ctx) {
		try {
			if (login.vratiUser(ctx) == null || login.vratiKod(ctx) == null)
				return false;
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Briše korisnikove podatke spremljene u SharedPreferences
	 * 
	 * @param ctx
	 */
	
	public void obrisiKorisnik (Context ctx){
		login.logout(ctx);
	}
	
	/**
	 * metoda vraæa polje sa stringovima korisnièko ime, korisnièko ime je spremljeno u prvi element polja,
	 * kod je spremljen u drugi element polja
	 * @param ctx
	 * @return
	 */
	
	public ArrayList<String> vratiLokalnePodatke (Context ctx) {
		ArrayList<String> rezultat = new ArrayList<String>();
		
		rezultat.add(login.vratiUser(ctx));
		rezultat.add(login.vratiKod(ctx));
		
		return rezultat;
	}
}
