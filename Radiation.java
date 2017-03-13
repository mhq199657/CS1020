/**
 * Name			:
 * Matric No.	:
 * PLab Acct.	:
 */

import java.util.*;

public class Radiation {
	Queue<Integer> _descendingQueue;
	int _lastYear;
	int _lastStrength; 
	int _globalMin;
	int _prevStrength;
	Radiation(){
		_descendingQueue = new LinkedList<Integer>();
	}
	public void run() {
		Scanner sc = new Scanner(System.in);
		int numOfEntries = sc.nextInt();
		_globalMin = sc.nextInt();
		_prevStrength = _globalMin;
		numOfEntries--;
		int max = 0;
		while(numOfEntries>0){
			numOfEntries--;
			//System.out.println(numOfEntries	);
			int currStrength = sc.nextInt();
			//check for whether this currStrength is new globalMin
			if(currStrength<=_globalMin){
				//new globalMin, clear previous queue do not push
				int temp = clearQueue();
				if(temp>max){
					max = temp;
				}
				_globalMin = currStrength;
				_lastYear = 0;
				continue;
			}
			//if not global mean, check whether non-increasing
			if(currStrength<=_prevStrength){
				//non increasing, then no need to partition
				_descendingQueue.offer(currStrength);
				_prevStrength = currStrength;
					continue;
			}
			int temp =clearQueue();
			if(temp>max){
				max = temp;
			}
			_descendingQueue.offer(currStrength);
			//System.out.println(_prevStrength);
			_prevStrength = currStrength;
		}
		int temp = clearQueue();
		if(temp >max){
			max = temp;
		}
		System.out.println(max);
	}
	private int clearQueue(){
			if(_descendingQueue.isEmpty()){
				return 0;
			}
			int temp = 0;
			//System.out.print(_descendingQueue);
			int yearsOfDecay = 0;
			boolean check = true;
			boolean increment = true;
			while(!_descendingQueue.isEmpty()){
				if(check){
						if(_descendingQueue.peek()>_lastStrength){
							yearsOfDecay++;
							//System.out.println("Here");
						}else{
							yearsOfDecay = _lastYear+1;
							check= false;
						}
			}else{
				yearsOfDecay++;
			}
			temp = _descendingQueue.poll();
			}
			if(_lastYear>yearsOfDecay){
			}else{
			_lastStrength = temp;
			_lastYear= yearsOfDecay;
				}
			//System.out.println(yearsOfDecay);
			return _lastYear;
	}
	public static void main(String[] args) {
		Radiation myChemicalElements = new Radiation();
		myChemicalElements.run();
	}
}

