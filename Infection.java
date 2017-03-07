import java.util.*;
class LocationMap { 
	private ArrayList<LinkedList<Integer>> adjList;
	public LocationMap(int numLocations){
		adjList = new ArrayList<LinkedList<Integer>>();
	 	for (int fromIdx = 0; fromIdx < numLocations; fromIdx++)
	 		adjList.add(new LinkedList<Integer>()); 
	}
	public void appendNeighbour(int fromIdx, int toIdx){
		adjList.get(fromIdx).add(toIdx); 
	}
	public void printInfectionSeq() {
		BreathFirstSearch bfs = new BreathFirstSearch(adjList);
		bfs.search(0);
		System.out.println(bfs.getResult());
	}
}
class BreathFirstSearch{
	ArrayList<LinkedList<Integer>> _adjList;
	boolean[] visited;
	Queue<Integer> _result;
	boolean _allFound = false;
	BreathFirstSearch(ArrayList<LinkedList<Integer>> adjList){
		_adjList = adjList;
		visited = new boolean[_adjList.size()];
		_result = new LinkedList<Integer>();
	}
	public void search(int startingIndex){
		Queue<Integer> startLevel = new LinkedList<Integer>();
		startLevel.add(startingIndex);
		search(startLevel);
	}
	public String getResult(){
		return _result.toString();
	}
	private void search(Queue<Integer> thisLevel){
		if(thisLevel.size()==0){
			return;
		}else{
			for(Integer item: thisLevel){
				if(visited[item]){
					continue;
				}
				visited[item]=true;
				_result.offer(item);
			}
			search(generateNextLevel(thisLevel));
		}
	}
	private Queue<Integer> generateNextLevel(Queue<Integer> thisLevel){
		Queue<Integer> result = new LinkedList<Integer>();
		for(Integer item:thisLevel){
			LinkedList<Integer> neighbourList = _adjList.get(item);
			Iterator<Integer> i = neighbourList.listIterator();
			while(i.hasNext()){
			Integer curr = i.next();
			if(visited[curr]){
				continue;
			}
			result.offer(curr);
		}
	}
	//System.out.println(result);
	return result;

}
}
public class Infection {
	public static void main(String[] args){
		LocationMap sampleMap = new LocationMap(9);
		int[][] allNeighbours = {  {1, 3}, {0, 4}, {3, 5}, {0, 2, 4}, {1, 3}, {2, 6, 8},  {5, 7, 8}, {6, 8}, {5, 6, 7}  };
		int fromIdx = 0; 
		for (int[] toLocations : allNeighbours) {
			for (int toIdx : toLocations) {
				sampleMap.appendNeighbour(fromIdx, toIdx);
			}
			fromIdx++;
		}
		sampleMap.printInfectionSeq(); // process and display sequence System.out.println();
	}
}