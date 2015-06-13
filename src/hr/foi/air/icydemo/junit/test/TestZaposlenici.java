package hr.foi.air.icydemo.junit.test;

import static org.junit.Assert.*;
import hr.foi.air.icydemo.plugins.Zaposlenici;

import org.junit.Test;

public class TestZaposlenici {

	@Test

		public void testPorez_na_dohodak()
		{
			Zaposlenici test = new Zaposlenici();
			
//			assertEquals((double) 964, test.dohvatiJson("kor1", "0"));
//			assertEquals((double) 964, test.dohvatiJson("kor1", "1"));
//			assertEquals((double) 964, test.dohvatiJson("kor1", "2"));
			assertNull(test.dohvatiJson("kor1", "0"));
		}
	}

