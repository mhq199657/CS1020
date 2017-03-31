/**
 * Name 		:
 * Matric No. 	:
 * PLab Acct. 	:
 */
import java.util.*;
public class Office {
	long[] _towerList;
	int  _numOfTowers;
	long _count;
    Stack<Tower> _towerStack = new Stack();
    private void run() {
	// treat this as your "main" method
    	Scanner sc = new Scanner(System.in);
    	_numOfTowers = sc.nextInt();
    	_towerList = new long[_numOfTowers];
    	for(int i = 0; i<_numOfTowers; i++){
    		_towerList[i] = sc.nextInt();
        }
        _towerStack.push(new Tower(0,0,0));
        long stackSum=0;
        for(int i = 0; i<_numOfTowers; i++){
           long lengthOfMin = 1;
           long height = _towerList[i];
           while(height<=_towerStack.peek().getHeight()){
               Tower t = _towerStack.pop();
                stackSum -=t.getNumOfRooms();
                lengthOfMin+=t.getLengthOfMin();
           }
           long numOfRooms = lengthOfMin*height*(height+1)/2;
           stackSum +=numOfRooms;
           _count+=stackSum;
          _towerStack.push(new Tower(height, lengthOfMin, numOfRooms));
        }
    	System.out.println(_count);
    }

    public static void main(String[] args) {
        Office myBeautifulOffice = new Office();
        myBeautifulOffice.run();
    }
}
class Tower{
    long _height;
    long _lengthOfMin;
    long _numOfRooms;
    Tower(long height, long lengthOfMin, long numOfRooms){
        _height = height;
        _lengthOfMin = lengthOfMin;
        _numOfRooms = numOfRooms;
    }
    public long getHeight(){
        return _height;
    }
    public long getLengthOfMin(){
        return _lengthOfMin;
    }
    public long getNumOfRooms(){
        return _numOfRooms;
    }
}
