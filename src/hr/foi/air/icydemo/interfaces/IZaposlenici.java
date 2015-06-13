package hr.foi.air.icydemo.interfaces;

import hr.foi.air.icydemo.types.ZaposleniciReturnType;

import java.util.List;

public interface IZaposlenici {
	/**
	 * Metoda vra�a listu sa podacima o zaposlenicima
	 * 
	 * @param user korisni�ko ime trenutno prijavljenog korisnika
	 * @param kod korisnikov kod za pristup podacima o zaposlenicima
	 * @return lista sa podacima zaposlenika
	 */
	public List<ZaposleniciReturnType> vratiZaposlenike(String user, String kod);
}
