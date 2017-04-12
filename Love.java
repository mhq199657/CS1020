/**
 * Name			:
 * Matric No.	:
 */
import java.util.*;

public class Love {
	Hashtable<String,Integer> _wordTable;
	Queue<String> _minimalQueue;
	int currMin = Integer.MAX_VALUE;
	Love(){
		_wordTable = new Hashtable<String, Integer>();
		_minimalQueue = new LinkedList<String>();
	}
	public void run() {
		// treat this as your "main" method
		Scanner sc = new Scanner(System.in);
		int numOfWords = sc.nextInt();
		int currCount = 0;
		while(numOfWords>0){
			//System.out.println(_minimalQueue);
			numOfWords--;
			String currWord = sc.next();
			if(_wordTable.containsKey(currWord)){
				if(currWord.equals(_minimalQueue.peek())){
					currCount = processQueue(currCount);
				}else{
					_wordTable.put(currWord, _wordTable.remove(currWord)+1);
					_minimalQueue.offer(currWord);
					currCount +=currWord.length();
				}
			}else{
				_wordTable.put(currWord,1);
				_minimalQueue.offer(currWord);
				currCount +=currWord.length();
				currCount = processQueue2(currCount);
			}
		}
		System.out.println(currMin);
	}
	private int processQueue(int currCount){
		String toBeOffered = _minimalQueue.poll();
		while(!_minimalQueue.isEmpty()){
			String currFront = _minimalQueue.peek();
			if(currFront.equals(toBeOffered)||_wordTable.get(currFront)>1){
				currCount -=_minimalQueue.poll().length();
				_wordTable.put(currFront, _wordTable.remove(currFront)-1);
			}else{
				break;
			}
		}
		_minimalQueue.offer(toBeOffered);
		if(currCount<currMin){
			currMin = currCount;
		}
		return currCount;
	}
	private int processQueue2(int currCount){
		while(!_minimalQueue.isEmpty()){
			String currFront = _minimalQueue.peek();
			if(_wordTable.get(currFront)>1){
				currCount -=_minimalQueue.poll().length();
				_wordTable.put(currFront, _wordTable.remove(currFront)-1);
			}else{
				break;
			}
		}
		currMin = currCount;
		return currCount;
	}
	public static void main(String[] args) {
		Love myLove = new Love();
		myLove.run();
	}

}
