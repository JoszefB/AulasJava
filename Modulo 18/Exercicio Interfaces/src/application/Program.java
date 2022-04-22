package application;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		Scanner kb = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			System.out.println("Enter contract data");
			System.out.print("Number: ");
			int number = kb.nextInt();
			System.out.print("Date (dd/MM/yyyy): ");
			Date date = sdf.parse(kb.next());
			System.out.println("Contract value: ");
			double totalValue = kb.nextDouble();
			
			Contract contract = new Contract(number, date, totalValue);
			
			System.out.println("Enter number of installments: ");
			int n = kb.nextInt();
			
			ContractService contractService = new ContractService(new PaypalService());
			
			contractService.processContract(contract, n);
			
			System.out.println("Installments:");
			for(Installment x: contract.getInstallments()) {
				System.out.println(x);
			}
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		kb.close();
		
	}

}
