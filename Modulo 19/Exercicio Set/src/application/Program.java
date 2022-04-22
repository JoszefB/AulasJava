package application;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner kb = new Scanner(System.in);
		
		Set<Integer> a = new HashSet<>();
		Set<Integer> b = new HashSet<>();
		Set<Integer> c = new HashSet<>();
		
		System.out.print("How many students for course A? ");
		int n = kb.nextInt();
		for (int i = 0; i <= n; i++) {
			int number = kb.nextInt();
			a.add(number);
		}
		
		System.out.print("How many students for course B? ");
		n = kb.nextInt();
		for (int i = 0; i <= n; i++) {
			int number = kb.nextInt();
			b.add(number);
		}
		
		System.out.print("How many students for course C? ");
		n = kb.nextInt();
		for (int i = 0; i <= n; i++) {
			int number = kb.nextInt();
			c.add(number);
		}
		
		Set<Integer> total = new HashSet<Integer>();
		total.addAll(b);
		total.addAll(c);
		
		System.out.println("Total students: " + total.size());
		
		kb.close();
	}

}
