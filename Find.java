import java.util.*;
class Find {
	Hashtable<String, Integer> _hashTable;
	private int _lengthOfSegment;
    private int _lengthOfDNA;
	Find(){
		_hashTable = new Hashtable<String,Integer>();
	}
	public void run(){
		Scanner sc = new Scanner(System.in);
		 _lengthOfDNA = sc.nextInt();
        _lengthOfSegment = sc.nextInt();
        String dna = sc.next();
        String dna2 = sc.next();
        generate(dna);
        generate2(dna2);
        int numOfQueries = sc.nextInt();
        while(numOfQueries>0){
            numOfQueries--;
            String subDNA = sc.next();
            int currResult = count(subDNA);
            System.out.println(currResult);
        }
	}
	private void generate(String dna){
		for(int i =0; i+_lengthOfSegment<=_lengthOfDNA; i++){
            String currSubDNA = dna.substring(i,i+_lengthOfSegment);
            if(_hashTable.containsKey(currSubDNA)){
            	continue;
            }else{
                _hashTable.put(currSubDNA,1);
            }
        }
	}
	private void generate2(String dna2){
		Hashtable<String, Integer> internal = new Hashtable<String, Integer>();
		for(int i =0; i+_lengthOfSegment<=_lengthOfDNA; i++){
            String currSubDNA = dna2.substring(i,i+_lengthOfSegment);
            if(internal.containsKey(currSubDNA)){
            	continue;
            }
            if(_hashTable.containsKey(currSubDNA)){
            	_hashTable.remove(currSubDNA);
            	_hashTable.put(currSubDNA,3);
            }else{
                _hashTable.put(currSubDNA,2);
            }
            internal.put(currSubDNA,1);
        }
	}
	public int count(String substring) {
        // implementation
        if(_hashTable.containsKey(substring)){
            return _hashTable.get(substring);
        }else{
            return 0;
        }
    }
	public static void main(String[] args) {

		Find fd = new Find();
		fd.run();
	}
}