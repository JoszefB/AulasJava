package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;
import entities.OutsourcedEmployee;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		
		Scanner kb = new Scanner(System.in);
		List<Employee> emp = new ArrayList<>();
		Employee employee;
		
		System.out.print("Enter the number of employees:");
		int n = kb.nextInt();
		
		for(int i = 1; i <= n; i++) {
			System.out.println("Employee #"+i+" data:");
			System.out.print("Outsourced (y/n)? ");
			char resp = kb.next().charAt(0);
			System.out.print("Name: ");
			kb.nextLine();
			String name = kb.nextLine();
			System.out.print("Hours: ");
			int hours = kb.nextInt();
			System.out.print("Value per hour: ");
			double value = kb.nextDouble();
			if(resp == 'y') {
				System.out.print("Additional charge: ");
				double additional = kb.nextDouble();
				employee = new OutsourcedEmployee(name, hours, value, additional);
			}
			else {
				employee = new Employee(name, hours, value);
			}
			emp.add(employee);
		}
		
		kb.close();
		
		System.out.println("PAYMENTS:");
		for(Employee e: emp) {
			System.out.println(e);
		}
	}

}
