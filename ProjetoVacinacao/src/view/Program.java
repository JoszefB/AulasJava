package view;

import java.util.Scanner;

import controller.ExceptionCustomized;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI ui = new UI();
		Scanner kb = new Scanner(System.in);
		do {
			try {
				ui.menuPrincipal();
			}
			catch (ExceptionCustomized e) {
				System.out.println(e.getMessage());
				kb.nextLine();
			}
		}
		while(ui.getLoop());
		kb.close();
	}

}