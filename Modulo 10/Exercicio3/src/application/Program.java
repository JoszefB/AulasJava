package application;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		int m = kb.nextInt();
		int n = kb.nextInt();
		int [][] matrix = new int [m][n];
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				matrix [i][j] = kb.nextInt();
			}
		}
		int val = kb.nextInt();
		kb.close();
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(val == matrix[0][j] && i == 0) {
					System.out.println("Position 0,"+j);
					if(j > 0) {
						System.out.println("Left: "+matrix[0][j-1]);
						if(j < matrix[i].length-1) {
							System.out.println("Right: "+matrix[0][j+1]);
							System.out.println("Down: "+matrix[1][j]);
						}
						else {
							System.out.println("Down: "+matrix[1][j]);
						}
					}
					else {
						System.out.println("Right: "+matrix[0][j+1]);
						System.out.println("Down: "+matrix[1][j]);
					}
				}
				else if(val == matrix[i][j] && i > 0) {
					System.out.println("Position "+i+","+j);
					if(j > 0) {
						System.out.println("Left: "+matrix[i][j-1]);
						if(j < matrix[i].length-1) {
							System.out.println("Right: "+matrix[i][j+1]);
							System.out.println("UP: "+matrix[i-1][j]);
							System.out.println("Down: "+matrix[i+1][j]);
						}
						else {
							System.out.println("UP: "+matrix[i-1][j]);
							System.out.println("Down: "+matrix[i+1][j]);
						}
					}
					else {
						System.out.println("Right: "+matrix[i][j+1]);
						System.out.println("UP: "+matrix[i-1][j]);
						System.out.println("Down: "+matrix[i+1][j]);
					}
				}
				else if(val == matrix[matrix.length-1][j] && i == matrix[i].length-1) {
					System.out.println("Position "+i+","+j);
					if(j > 0) {
						System.out.println("Left: "+matrix[i][j-1]);
						if(j < matrix[i].length-1) {
							System.out.println("Right: "+matrix[i][j+1]);
							System.out.println("UP: "+matrix[i-1][j]);
							System.out.println("Down: "+matrix[i+1][j]);
						}
						else {
							System.out.println("UP: "+matrix[i-1][j]);
							System.out.println("Down: "+matrix[i+1][j]);
						}
					}
					else {
						System.out.println("Right: "+matrix[i][j+1]);
						System.out.println("UP: "+matrix[i-1][j]);
						System.out.println("Down: "+matrix[i+1][j]);
					}
				}
			}
		}
	}

}
