package view;

import java.util.Scanner;

import controller.Controller;
import controller.ExceptionCustomized;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	private boolean loop;
	private Controller cont;
	private Scanner kb;
	
	public UI() {
		loop = true;
		cont = new Controller();
		kb = new Scanner(System.in);
	}
	
	public void setLoop() {
		loop = false;
	}
	
	public boolean getLoop() {
		return loop;
	}
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public void menuPrincipal() {
		try {
			clearScreen();
			System.out.print("Digite uma opcao"
					+ "\n1 - Cadastro"
					+ "\n2 - Rotinas"
					+ "\n3 - Relatórios"
					+ "\nEm qualquer tela, digite '-' para voltar ou 0 para sair"
					+ "\n->");
			String resp = kb.next();
			switch (resp) {
			case "1":
				subMenuCadastro();
				break;
			case "2":
				subMenuRotina();
				break;
			case "3":
				subMenuRelatorio();
				break;
			case "0":
				setLoop();
				break;
			default:
				System.out.println("Digite uma opcao valida");
				break;
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void subMenuCadastro() {
		try {
			clearScreen();
			System.out.print("Digite uma opcao"
					+ "\n1 - Cadastro Unidade"
					+ "\n2 - Cadastro Populacao"
					+ "\n3 - Cadastro Lote Vacina"
					+ "\nEm qualquer tela, digite '-' para voltar ou 0 para sair"
					+ "\n->");
			String resp = kb.next();
			switch (resp) {
			case "1":
				subSubMenuCadastro1();
				break;
			case "2":
				subSubMenuCadastro2();
				break;
			case "3":
				subSubMenuCadastro3();
				break;
			case "0":
				setLoop();
				break;
			case "-":
				menuPrincipal();
				break;
			default:
				System.out.println("Digite uma opcao valida");
				break;
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void subSubMenuCadastro1() {
		try {
			System.out.print("Digite uma opcao"
					+ "\n1 - Cadastrar Unidade"
					+ "\n2 - Listar"
					+ "\nEm qualquer tela, digite '-' para voltar ou 0 para sair"
					+ "\n->");
			String resp = kb.next();
			switch (resp) {
			case "1":
				cont.unidadeInsert();
				kb.next();
				break;
			case "2":
				cont.unidadeList();
				kb.next();
				break;
			case "0":
				setLoop();
				break;
			case "-":
				menuPrincipal();
				break;
			default:
				System.out.println("Digite uma opcao valida");
				break;
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void subSubMenuCadastro2() {
		try {
			System.out.print("Digite uma opcao"
					+ "\n1 - Cadastrar Pessoa"
					+ "\n2 - Listar"
					+ "\nEm qualquer tela, digite '-' para voltar ou 0 para sair"
					+ "\n->");
			String resp = kb.next();
			switch (resp) {
			case "1":
				cont.pessoaInsert();
				kb.next();
				break;
			case "2":
				cont.pessoaList();
				kb.next();
				break;
			case "0":
				setLoop();
				break;
			case "-":
				menuPrincipal();
				break;
			default:
				System.out.println("Digite uma opcao valida");
				break;
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void subSubMenuCadastro3() {
		try {
			System.out.print("Digite uma opcao"
					+ "\n1 - Cadastrar Lote Vacina"
					+ "\n2 - Listar"
					+ "\nEm qualquer tela, digite '-' para voltar ou 0 para sair"
					+ "\n->");
			String resp = kb.next();
			switch (resp) {
			case "1":
				cont.loteInsert();
				kb.next();
				break;
			case "2":
				cont.loteList();
				kb.next();
				break;
			case "0":
				setLoop();
				break;
			case "-":
				menuPrincipal();
				break;
			default:
				System.out.println("Digite uma opcao valida");
				break;
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void subMenuRotina() {
		try {
			System.out.print("Digite uma opcao"
					+ "\n1 - Recebimento de Vacinas"
					+ "\n2 - Transferencia de estoque"
					+ "\n3 - Rotina de Aplicacao de vacina"
					+ "\nEm qualquer tela, digite '-' para voltar ou 0 para sair"
					+ "\n->");
			String resp = kb.next();
			switch (resp) {
			case "1":
				cont.criarMovimento("rec");;
				kb.next();
				break;
			case "2":
				cont.criarMovimento("tra");
				kb.next();
				break;
			case "3":
				cont.criarMovimento("apl");
				kb.next();
				break;	
			case "0":
				setLoop();
				break;
			case "-":
				menuPrincipal();
				break;
			default:
				System.out.println("Digite uma opcao valida");
				break;
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void subMenuRelatorio() {
		try {
			System.out.print("Digite uma opcao"
					+ "\n1 - Estoque da vacina"
					+ "\n2 - Aplicacao"
					+ "\n3 - Resumo Aplicacao"
					+ "\n4 - Pessoas com esquema cainal incopleto"
					+ "\nEm qualquer tela, digite '-' para voltar ou 0 para sair"
					+ "\n->");
			String resp = kb.next();
			switch (resp) {
			case "1":
				cont.relatorioEstoque();
				kb.next();
				break;
			case "2":
				cont.relatorioApliacao();
				kb.next();
				break;
			case "3":
				cont.relatorioResumoAplicacao();
				kb.next();
				break;
			case "4":
				cont.relatorioResumoAplicacao();
				kb.next();
				break;	
			case "0":
				setLoop();
				break;
			case "-":
				menuPrincipal();
				break;
			default:
				System.out.println("Digite uma opcao valida");
				break;
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
}