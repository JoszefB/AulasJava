package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		Scanner kb = new Scanner(System.in);
		List<Product> list = new ArrayList<>();
		 
		System.out.println("Enter file path: ");
		String sourceFileStr = kb.nextLine();
		
		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
			
			String itemCSV = br.readLine();
			while(itemCSV != null) {
				
				String [] field = itemCSV.split(",");
				String name = field [0];
				double price = Double.parseDouble(field[1]);
				int quantity = Integer.parseInt(field[2]);
				
				list.add(new Product(name, price, quantity));
				
				itemCSV = br.readLine();
				
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
				
				for(Product prod: list) {
					bw.write(prod.getName() + "," + String.format("%.2f", prod.total()));
					bw.newLine();
				}
				
				System.out.println(targetFileStr + " CREATED!");
				
			}
			catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}
			
		}
		catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		kb.close();
		
	}

}
