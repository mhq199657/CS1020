/**
 * Name          :
 * Matric number :
 */

import java.util.*;

public class Cake {
	private Queue<Integer> _lengthQueue;
	private Queue<Boolean> _rasinQueue;
	private int _tolerance;
	Cake(){
		_lengthQueue = new LinkedList<Integer>();
		_rasinQueue = new LinkedList<Boolean>();
		_tolerance = 0;
	}
	public void init(Scanner sc){
		int numOfBlocks = sc.nextInt();
		_tolerance = sc.nextInt();
		while(numOfBlocks>0){
			numOfBlocks--;
			switch(sc.next()){
				case "R":{
					_rasinQueue.offer(true);
					_lengthQueue.offer(sc.nextInt());
					break;
				}
				case "C":{
					_rasinQueue.offer(false);
					_lengthQueue.offer(sc.nextInt());
					break;
				}
			}
		}
	}
	public void cut(){
		int max = -1;
		int result = 0;
		int temp = 0;
		Queue<Integer> _lengthWindow = new LinkedList<Integer>();
		Queue<Boolean> _rasinWindow = new LinkedList<Boolean>();
		while(!_rasinQueue.isEmpty()){
			//System.out.println(_lengthWindow);
			if(_tolerance>0){
				temp =_lengthQueue.poll();
				result +=temp;
				_lengthWindow.offer(temp);
				if(_rasinQueue.peek()){
					_tolerance--;
				}
				_rasinWindow.offer(_rasinQueue.poll());
			continue;
			}
			if(_tolerance==0){
				if(_rasinQueue.peek()){
					if(max<result){
						max = result;
					}
					while(!_rasinWindow.peek()){
						_rasinWindow.poll();
						result -=_lengthWindow.poll();
					}
					_rasinWindow.poll();
					result -=_lengthWindow.poll();
					_tolerance++;
					continue;
				}
				_rasinWindow.offer(_rasinQueue.poll());
				temp = _lengthQueue.poll();
				_lengthWindow.offer(temp);
				result +=temp;
			}
		}
		if(max<result){
			max = result;
		}
		System.out.println(max);

	}
	public static void main(String[] args) {
		//implement your main method here
		Cake cake = new Cake();
		Scanner sc = new Scanner(System.in);
		cake.init(sc);
		cake.cut();
	}
}

