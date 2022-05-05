package view;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI ui = new UI();
		do {
			ui.menuPrincipal();
		}
		while(ui.getLoop());
	}

}