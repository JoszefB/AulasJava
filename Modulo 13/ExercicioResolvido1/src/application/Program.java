package application;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	private Scanner kb;
	private SimpleDateFormat sdf;
	private ArrayList<Worker> worker;
	private boolean flag;
	
	public Program() {
		kb = new Scanner(System.in);
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		worker = new ArrayList<Worker>();
	}
	
	public void setLoop(boolean flag) {
		this.flag = flag;
	}
	
	public boolean loop() {
		return flag;
	}
	
	public void menu() throws ParseException {
		System.out.print("MENU:"
				+ "\n1 - Worker Registration"
				+ "\n2 - Update Contract"
				+ "\n3 - List worker"
				+ "\n4 - List Contract by Employee"
				+ "\n5 - Calculate income per worker"
				+ "\n6 - Exit"
				+ "\n->");
		int menu = kb.nextInt();
		switch (menu) {
			case 1:
				createWorker();
				break;
			case 2:
				newContract();
				break;
			case 3:
				listWorker();
				break;
			case 4:
				listContractByWorker();
				break;
			case 5:
				calculateIncomePerWorker();
				break;
			case 6:
				System.out.println("Finishing");
				setLoop(false);
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	public void createWorker() throws ParseException {
		
		System.out.print("Enter department's name: ");
		String departmentName = kb.next();
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		kb.nextLine();
		String workerName = kb.nextLine();
		System.out.print("Level: ");
		String workerLevel = kb.nextLine();
		System.out.print("Base salary: ");
		double baseSalary = kb.nextDouble();
		Worker work = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
		
		System.out.print("How many contracts to this worker? ");
		int n = kb.nextInt();
		
		worker.add(contract(n, work));
		
	}
	
	public Worker contract(int n, Worker work) throws ParseException {
		for(int i = 1; i <= n; i++) {
			System.out.println("Enter contract #" + i + " data:");
			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(kb.next());
			System.out.print("Value per hour: ");
			double valuePerHour = kb.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = kb.nextInt();
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			work.addContract(contract);
		}
		return work;
	}
	
	public void newContract() throws ParseException {
		System.out.print("Enter the name of the worker:");
		kb.nextLine();
		String name = kb.nextLine();
		for(int i = 0; i < worker.size(); i++) {
			if(name.equalsIgnoreCase(worker.get(i).getName())) {
				System.out.print("How many contracts to this worker? ");
				int n = kb.nextInt();
				for(int j = 1; j <= n; j++) {
					System.out.println("Enter contract #" + j + " data:");
					System.out.print("Date (DD/MM/YYYY): ");
					Date contractDate = sdf.parse(kb.next());
					System.out.print("Value per hour: ");
					double valuePerHour = kb.nextDouble();
					System.out.print("Duration (hours): ");
					int hours = kb.nextInt();
					HourContract contract = new HourContract(contractDate, valuePerHour, hours);
					worker.get(i).addContract(contract);
				}
			}
		}
	}
	
	public void listWorker() {
		for(int i = 0; i < worker.size(); i++) {
			System.out.println(i+": "+worker.get(i).getName());
		}
	}
	
	public void listContractByWorker() {
		System.out.print("Enter the name of the worker:");
		kb.nextLine();
		String name = kb.nextLine();
		for(int i = 0; i < worker.size(); i++) {
			if(name.equalsIgnoreCase(worker.get(i).getName())) {
				for(int j = 0; j < worker.get(i).contract().size(); j++) {
					System.out.println("Date (DD/MM/YYYY): "+worker.get(i).contract().get(j).getDate()+
							"\nValue per hour: "+worker.get(i).contract().get(j).getValuePerHour()+
							"\nDuration (hours): "+worker.get(i).contract().get(j).getHours());
				}
			}
		}
	}
	
	public void calculateIncomePerWorker() {
		System.out.print("Enter the name of the worker:");
		kb.nextLine();
		String name = kb.nextLine();
		for(int i = 0; i < worker.size(); i++) {
			if(name.equalsIgnoreCase(worker.get(i).getName())) {
				System.out.print("Enter month and year to calculate income (MM/YYYY): ");
				String monthAndYear = kb.next();
				int month = Integer.parseInt(monthAndYear.substring(0, 2));
				int year = Integer.parseInt(monthAndYear.substring(3));
				System.out.println("Name: " + worker.get(i).getName()+
						"\nDepartment: " + worker.get(i).getDepartment().getName()+
						"\nIncome for " + monthAndYear + ": " + String.format("%.2f", worker.get(i).income(year, month)));
			}
		}
	}
	
}