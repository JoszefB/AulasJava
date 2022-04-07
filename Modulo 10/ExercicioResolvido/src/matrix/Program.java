package matrix;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		int matrix [][];
		int n = kb.nextInt();
		matrix = new int [n][n];
		for(int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = kb.nextInt();
			}
		}
		kb.close();
		System.out.println("Main diagonal:");
		for(int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(i == j) {
					System.out.print(matrix[i][j]+" ");
				}
			}
		}
		int count = 0;
		for(int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] < 0) {
					count++;
				}
			}
		}
		System.out.println("");
		System.out.println("Negative numbers = "+count);
		
	}

}
