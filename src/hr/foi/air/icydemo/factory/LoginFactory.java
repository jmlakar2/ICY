package hr.foi.air.icydemo.factory;

import hr.com.air.icylib1.implement.TestLogin;
import hr.foi.air.icydemo.interfaces.ILogin;
import hr.foi.air.icydemo.plugins.Login;

public class LoginFactory {
	public ILogin odaberi_prijavu (int odabir) {
		switch (odabir) {
		case 1:
			return new Login();
		case 2:
			return new TestLogin();
		default:
			return null;
		}
	}
}
