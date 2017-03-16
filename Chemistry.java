/**
 * Name          :
 * Matric number :
 */

import java.util.*;

public class Chemistry {
	Stack<Character> _mainStack;
	Stack<Character> _helpStack;
	HashMap<Character, Integer> _elementTable;
	String _formula;
	Chemistry(){
		_mainStack = new Stack<Character>();
		_helpStack = new Stack<Character>();
		_elementTable = new HashMap<Character, Integer>();
		_formula = null;

	}
	public void init(Scanner sc){
		int numOfElements = sc.nextInt();
		while(numOfElements>0){
			numOfElements--;
			Character element = sc.next().charAt(0);
			int mass = sc.nextInt();
			_elementTable.put(element, mass);
		}
		sc.nextLine();
		_formula = sc.nextLine();
	}
	public void calculate(){
		for(int i = 0; i<_formula.length();i++){
			Character curr = _formula.charAt(i);
			if(Character.isDigit(curr)){
				int multiplier = Character.getNumericValue(curr);
				if(_mainStack.peek().equals(')')){
					_mainStack.pop();
					int counter = 0;
					while(!_mainStack.peek().equals('(')||counter>0){
						
						if(_mainStack.peek().equals(')')){
							counter++;
						}
						if(_mainStack.peek().equals('(')){
							counter--;
						}
						_helpStack.push(_mainStack.pop());
					}
					_mainStack.pop();
					while(!_helpStack.empty()){
						Character temp = _helpStack.peek();
						for(int j = 0; j<multiplier;j++){
							_mainStack.push(temp);
						}
						_helpStack.pop();
					}
				}else{
					_helpStack.push(_mainStack.pop());
					while(!_helpStack.empty()){
						Character temp = _helpStack.peek();
						for(int k = 0; k<multiplier;k++){
							_mainStack.push(temp);
						}
						_helpStack.pop();
					}
				}
			}else{
				_mainStack.push(curr);
			}
		}
		int totalMass = 0;
		while(!_mainStack.empty()){
			char here = _mainStack.pop();
			switch(here){
				case '(':{
					break;
				}
				case ')':{
					break;
				}
				default:{
					totalMass +=_elementTable.get(here);
				}
			}
		}
		System.out.println(totalMass);
	}
	public static void main(String[] args) {
        //implement your main method here
        Chemistry cm = new Chemistry();
		Scanner sc = new Scanner(System.in);
        cm.init(sc);
        cm.calculate();
    }
}
