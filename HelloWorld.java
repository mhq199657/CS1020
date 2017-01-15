/**
 *
 * author	: Ma Hongqiang
 * matric no: A0136093H
 * 
 */

import java.util.*;

public class HelloWorld {
	private static int operate(String operator,int p1,int p2){
		if(operator.equals("AND")){
			return p1*p2;
		}else{
			return p1+p2-Math.min(p1,p2);
		}
	}
	public static void main(String[] args) {

		// declare the necessary variables
		int option;
		String operator = null;
		int p1,p2;
		// declare a Scanner object to read input
		Scanner sc = new Scanner(System.in);
		option = sc.nextInt();
		switch(option){
			case 1:{
				int num_of_entries = sc.nextInt();
				for(int i=0;i<num_of_entries;i++){
					operator= sc.next();
					p1 = sc.nextInt();
					p2 = sc.nextInt();
					//System.out.print(operator +" "+ p1.toString()+" "+p2.toString());
					System.out.println(operate(operator, p1, p2));
				}
				return;
			}
			case 2:{
				while(true){
					operator = sc.next();
					if(operator.equals("0")||operator.equals("-1")){
						break;
					}
					p1 = sc.nextInt();
					p2 = sc.nextInt();
					System.out.println(operate(operator, p1, p2));
				}
				return;
			}
			case 3:{
				while(sc.hasNext()){
					operator= sc.next();
					p1 = sc.nextInt();
					p2 = sc.nextInt();
					System.out.println(operate(operator, p1, p2));
				}
				return;
			}
		}

		// read input and process them accordingly

	}
}
