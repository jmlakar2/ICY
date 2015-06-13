package hr.foi.air.icydemo.junit.test;

import static org.junit.Assert.*;
import hr.foi.air.icydemo.core.Placa;

import org.junit.Test;


public class TestPlaca {
	@Test
	public void testPorez_na_dohodak()
	{
		Placa test = new Placa();
		double result = test.porez_na_dohodak(5000);
		assertEquals((double) 964, result, 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetPlaca()
	{
		Placa test = new Placa();
		double[] result = test.getPlaca(1, null);
	}
}
