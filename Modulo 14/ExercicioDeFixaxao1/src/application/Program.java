package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		
		Scanner kb = new Scanner(System.in);
		List<Product> products = new ArrayList<>();
		Product prod;
		
		System.out.print("Enter the number of products: ");
		int n = kb.nextInt();
		
		for(int i = 1; i <= n; i++){
			
			System.out.println("Product #" + i +" data:");
			
			System.out.print("Common, used or imported (c/u/i)?");
			char option = kb.next().charAt(0);
			
			System.out.print("Name: ");
			kb.nextLine();
			String name = kb.nextLine();
			
			System.out.print("Price: ");
			double price = kb.nextDouble();
			
			if(option == 'i') {
				
				System.out.print("Customs fee: ");
				double customFee = kb.nextDouble();
				
				prod = new ImportedProduct(name, price, customFee);
				
			}
			
			else if(option == 'u') {
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				System.out.print("Manufacture date (DD/MM/YYYY):");
				Date manufactureDate =  sdf.parse(kb.next());
				
				prod = new UsedProduct(name, price, manufactureDate);
			}
			
			else {
				
				prod = new Product(name, price);
				
			}
			
			products.add(prod);
		}
		
		kb.close();
		
		System.out.println("PRICE TAGS:");
		
		for(Product p : products)
			System.out.println(p.priceTag());
	}

}
