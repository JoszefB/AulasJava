package application;

import java.text.ParseException;
import java.util.Locale;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Program p = new Program();
		p.setLoop(true);
		while(p.loop() != false) {
				p.menu();
		}
		System.exit(0);
	}

}