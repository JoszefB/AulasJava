package application;

import java.util.ArrayList;
import java.util.Scanner;
import entities.Employee;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		Employee emp;
		ArrayList<Employee> employee = new ArrayList<Employee>();
		boolean loop = true;
		int option;
		int manyEmployee;
		int id;
		do {
			System.out.print("MENU:"
					+ "\n1 - REGISTER EMPLOYEE"
					+ "\n2 - INCREASE SALARY"
					+ "\n3 - LIST EMPLOYEES"
					+ "\n4 - LEAVE"
					+ "\n->");
			option = kb.nextInt();
			switch (option) {
				case 1:
					System.out.print("How many employees will be refistred?");
					manyEmployee = kb.nextInt();
					for(int i = 1; i <= manyEmployee; i++) {
						System.out.println("Employee #"+i+": ");
						System.out.print("Id: ");
						id = kb.nextInt();
						if(employee.size() > 0) {
							for(int j = 0; j < employee.size(); j++) {
								if(employee.get(j).getId() != id) {
									emp = new Employee();
									emp.setId(id);
									System.out.print("Name: ");
									kb.nextLine();
									emp.setName(kb.nextLine());
									System.out.print("Salary: ");
									emp.setSalary(kb.nextDouble());
									employee.add(emp);
								}
							}
						}
						else if (employee.size() == 0) {
							emp = new Employee();
							emp.setId(id);
							System.out.print("Name: ");
							kb.nextLine();
							emp.setName(kb.nextLine());
							System.out.print("Salary: ");
							emp.setSalary(kb.nextDouble());
							employee.add(emp);
						}
						else {
							System.out.println("Please provide a valid id");
						}
					}
					break;
				case 2:
					boolean flag = false;
					System.out.print("Enter the employee id that will have salary increase: ");
					id = kb.nextInt();
					int pos = 0;
					while (flag == false && pos < employee.size()) {
						if(employee.get(pos).getId() == id) {
							System.out.print("Enter the percentage:");
							employee.get(pos).increaseSalary(kb.nextDouble());
							flag = true;
						}
						pos++;
					}
					if(flag == false) {
						System.out.println("This id does not exist!");
					}
					break;
				case 3:
					for(int i = 0; i < employee.size(); i++) {
						System.out.println(""+employee.get(i));
					}
					break;
				case 4:
					System.out.println("Finishing");
					loop = false;
					break;				
				default:
					System.out.println("Option invalid!");
					break;
			}
		}
		while(loop == true);
		kb.close();
		System.exit(0);
	}

}
