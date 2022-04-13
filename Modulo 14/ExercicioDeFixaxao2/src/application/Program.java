package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Company;
import entities.Individual;
import entities.TaxPayer;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);

		Scanner kb = new Scanner(System.in);
		List<TaxPayer> tp = new ArrayList<>();
		
		System.out.print("Enter the number of tax payers: ");
		int n = kb.nextInt();
		
		for(int i = 1; i <= n; i++) {
			
			System.out.println("Tax payer #"+i+" data:");
			System.out.print("Individual or company (i/c)?" );
			char op = kb.next().charAt(0);
			System.out.print("Name: ");
			kb.nextLine();
			String name = kb.nextLine();
			System.out.print("Anual income: ");
			double anualIncome = kb.nextDouble();
			
			if(op == 'i') {
				
				System.out.print("Health expenditures: ");
				double healthExpenditures = kb.nextDouble();
				
				tp.add(new Individual(name, anualIncome, healthExpenditures));
				
			}
			
			else {
				
				System.out.print("Number of employees: ");
				int numberOfEmployee = kb.nextInt();
				
				tp.add(new Company(name, anualIncome, numberOfEmployee));
			}
			
		}
		
		kb.close();
		
		double sum = 0.0;

		System.out.println("TAXES PAID:");
		for(TaxPayer taxPayer : tp) {
			
			sum+= taxPayer.tax();
			
			System.out.println(taxPayer);
		}
		
		System.out.println("TOTAL TAXES: $ "+sum);
	}

}
