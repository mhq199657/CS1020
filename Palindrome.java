/*
 * author		: Ma Hongqiang
 * matric no.	: A0136093H
 */

import java.util.*;

public class Palindrome {
	/* use this method to check whether the string is palindrome word or not
	 * 		PRE-Condition  :
	 * 		POST-Condition :
	 */
	public static boolean isPalindrome(String word) {
		int len = word.length();
		for(int i = 0; i<len/2;i++){
			if(word.charAt(i)!=word.charAt(len-1-i)){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		// declare the necessary variables
		String s1,s2;

		// declare a Scanner object to read input
		Scanner sc = new Scanner(System.in);
		// read input and process them accordingly
		s1 = sc.nextLine();
		s2 = sc.nextLine();

		// simulate the problem
		String s = s1.concat(s2);
		boolean result = isPalindrome(s);
		// output the result
		if(result){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}
}