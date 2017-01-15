/**
 *
 * author	: Ma Hongqiang
 * matric no: A0136093H
 * 
 */

import java.util.*;

public class StringComparison {
	
	public static void main(String[] args) {

		// declare the necessary variables
		String s1,s2;

		// declare a Scanner object to read input
		Scanner sc = new Scanner(System.in);

		// read input and process them accordingly
		s1 = sc.nextLine().toLowerCase();
		s2 = sc.nextLine().toLowerCase();
		if(s1.length()<s2.length()){
			System.out.println("1");
			return;
		}else if(s1.length()>s2.length()){
			System.out.println("2");
			return;
		}else{//Of equal length
			//Comparing each digit
			for(int i = 0; i<s1.length(); i++){
				if(s1.codePointAt(i)<s2.codePointAt(i)){
					System.out.println("1");
					return;
				}else if(s1.codePointAt(i)>s2.codePointAt(i)){
					System.out.println("2");
					return;
				}
			}
			System.out.println("0");
			return;
		}
	}
}
