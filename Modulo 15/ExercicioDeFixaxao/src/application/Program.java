package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;
import model.exceptions.BusinessException;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Informe os dados da conta");
		try {
			System.out.print("Numero: ");
			int numero = kb.nextInt();
			System.out.print("Titular: ");
			kb.nextLine();
			String holder = kb.nextLine();
			System.out.print("Saldo inicial: ");
			double balance = kb.nextDouble();
			System.out.print("Limite de saque: ");
			double withdrawLimit = kb.nextDouble();
			
			Account account = new Account(numero, holder, balance, withdrawLimit);
			
			System.out.println();
			System.out.print("Informe uma quantia para sacar: ");
			double amount = kb.nextDouble();
			
			try {
				account.withdraw(amount);
				System.out.printf("Novo saldo: %.2f%n", account.getBalance());
			}
			catch (BusinessException e) {
				System.out.println(e.getMessage());
			}
		}
		catch (RuntimeException e) {
			System.out.println("Favor digitar um valor valido");
		}
		
		kb.close();
		
	}

}
