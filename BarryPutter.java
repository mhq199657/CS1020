/**
 * Name 		:
 * Matric No. 	:
 * PLab Acct. 	:
 */
import java.util.*;
public class BarryPutter {
    ArrayList<Integer>[] _adjacencyList;
    int[] _goldList;
    int _maxJumps;
    int currMax = -1;
    private void run() {
        // treat this as your "main" method
        Scanner sc = new Scanner(System.in);
        int dimension = sc.nextInt();
        _goldList = new int[dimension];
        _maxJumps = sc.nextInt();
        generateAdjacencyList(sc, dimension);
        travel();
        System.out.println(currMax);
    }
    private void travel(){
        travel(0,_maxJumps, 0);
    }
    private void travel(int currPosition, int jumpsLeft, int currGold){
        if(currPosition<0||currPosition>=_adjacencyList.length){
            if(currGold>currMax){
                currMax = currGold;
            }
            return;
        }
        if(jumpsLeft==-1){
            if(currGold>currMax){
                currMax = currGold;
            }
            return;
        }
        ArrayList<Integer>  currList = _adjacencyList[currPosition];
        if(currList.size()==0){
            if(currGold+_goldList[currPosition]>currMax){
                currMax = currGold+_goldList[currPosition];
            }
            return;
        }
        for(int i = 0; i<currList.size(); i++){
            travel(currList.get(i), jumpsLeft-1,currGold+_goldList[currPosition]);
        }
    }
    private void generateAdjacencyList(Scanner sc, int dimension){
        _adjacencyList = (ArrayList<Integer>[])  new ArrayList[dimension];
        while(dimension>0){
            dimension--;
            int position = sc.nextInt();
            _goldList[position] = sc.nextInt();
            ArrayList<Integer> currList = new ArrayList<Integer>();
            int numOfPassages = sc.nextInt();
            while(numOfPassages>0){
                numOfPassages--;
                currList.add(sc.nextInt()+position);
            }
            _adjacencyList[position] = currList;
        }
    }
    public static void main(String[] args) {
        BarryPutter barry = new BarryPutter();
        barry.run();
    }
}
