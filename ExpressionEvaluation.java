import java.util.*;
class ExpressionEvaluation{
	Stack<String> _mainStack;
	Stack<String> _tempStack;
	ExpressionEvaluation(){
		_mainStack = new Stack<String>();
		_tempStack = new Stack<String>();
	}
	public double evaluate(String input){
		Scanner sc = new Scanner(input);
		while(sc.hasNext()){
			String currOp = sc.next();
			if(currOp.equals(")")){
				subEvaluate();
			}else{
				_mainStack.push(currOp);
			}
			//System.out.print(_mainStack);
		}
		return Double.parseDouble(_mainStack.peek());
	}
	public void subEvaluate(){
		while(!_mainStack.peek().equals("(")){
			_tempStack.push(_mainStack.pop());
		}
		_mainStack.pop();
		String operator = _tempStack.pop();
		switch(operator){
			case "+":{
				double sum = 0.0;
				while(!_tempStack.empty()){
					sum+=Double.parseDouble(_tempStack.pop());
				}
				_mainStack.push(sum+"");
				break;
			}
			case "-":{
				double difference = Double.parseDouble(_tempStack.pop());
				if(_tempStack.empty()){
					_mainStack.push(0-difference+"");
					break;
				}
				while(!_tempStack.empty()){
					difference =difference-Double.parseDouble(_tempStack.pop());
				}
				_mainStack.push(difference+"");
				break;
			}
			case "*":{
				double product = 1.0;
				while(!_tempStack.empty()){
					product*=Double.parseDouble(_tempStack.pop());
				}
				_mainStack.push(product+"");
				break;
			}
			case "/":{
				double quotient = Double.parseDouble(_tempStack.pop());
				if(_tempStack.empty()){
					_mainStack.push(1.0/quotient+"");
					break;
				}
				while(!_tempStack.empty()){
					quotient /=Double.parseDouble(_tempStack.pop());
				}
				_mainStack.push(quotient+"");
				break;
			}
		}
	}
	public static void main(String[] args){
		String expr = "( + ( - 6 ) ( * 2 3 4 ) )";
		ExpressionEvaluation ee = new ExpressionEvaluation();
		System.out.print(ee.evaluate(expr));
	}
}
	