package application;

import java.util.Scanner;

import entities.Student;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		Student student = new Student();
		Student [] stu = new Student [10];
		boolean loop = true;
		boolean subLoop = true;
		boolean subSubLoop = true;
		int option;
		int manyRooms;
		do {
			System.out.print("MENU:"
					+ "\n1 - RENT"
					+ "\n2 - LIST OF ALL ROOMS"
					+ "\n3 - LIST OF OCCUPIED ROOMS"
					+ "\n4 - VACANCY A ROOM"
					+ "\n5 - LEAVE"
					+ "\n->");
			option = kb.nextInt();
			switch (option) {
				case 1:
					subLoop = true;
					do {
						System.out.print("How many rooms wil be rented? ");
						manyRooms = kb.nextInt();
						if(manyRooms > 0 && manyRooms <= 10) {
							int count = 0; 
							for(int i = 0; i < stu.length; i++) {
								if(stu[i] == null) {
									count++;
								}
							}
							if (manyRooms <= count) {
								for(int i = 0; i < stu.length; i++) {
									if(stu[i] == null) {
										System.out.println("Room: "+i+" - vacant");
									}
								}
								for(int i = 0; i < manyRooms; i++) {
									System.out.println("Rent "+i+"#:");
									student = new Student();
									System.out.print("Name: ");
									kb.nextLine();
									student.setName(kb.nextLine());
									System.out.print("E-mail: ");
									student.setEmail(kb.next());
									subSubLoop = true;
									while (subSubLoop == true) {
										System.out.print("Room: ");
										int pos = kb.nextInt();
										if(stu[pos] == null) {
											stu[pos] = student;
											subLoop = false;
											subSubLoop = false;
										}
										else {
											System.out.println("Enter a valid Room");
										}
									}
								}
							}
							else {
								System.out.println("Enter a valid amount!");
							}
						}
						else {
							System.out.println("Enter a valid amount!");
						}
					}
					while (subLoop == true);
					break;
				case 2:
					for(int i = 0; i < stu.length; i++) {
						System.out.println(i+": "+stu[i]);
					}
					break;	
				case 3:
					for(int i = 0; i < stu.length; i++) {
						if(stu[i] != null)
							System.out.println(i+": "+stu[i]);
					}
					break;
				case 4:
					subLoop = true;
					do {
						for(int i = 0; i < stu.length; i++) {
							System.out.println(i+": "+stu[i]);
						}
						System.out.print("Enter the room to be vacated: ");
						int room = kb.nextInt();
						if(room > -1 && room < 10) {
							if(stu[room] != null) {
								stu[room] = null;
								subLoop = false;
							}
							else {
								System.out.println("Enter a valid amount!");
							}
						}
						else {
							System.out.println("Enter a valid amount!");
						}
					}
					while(subLoop != false);
					break;
				case 5:
					System.out.println("Finishing");
					loop = false;
					break;				
				default:
					System.out.println("Option invalid!");
					break;
			}
		}
		while(loop == true);
		System.exit(0);
		kb.close();
	}

}