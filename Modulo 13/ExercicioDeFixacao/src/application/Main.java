package application;

import java.text.ParseException;
import java.util.Locale;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Program program = new Program();
		while (program.getLoop() == true) {
			program.menu();
		}
		System.exit(0);
	}

}
