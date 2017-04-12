/**
 *  Name      :
 *  Matric no.:
 */

import java.util.*;

public class DNA {
    private Hashtable<String,Integer> _hashTable;
    private int _lengthOfSegment;
    private int _lengthOfDNA;
    public DNA(){
        _hashTable = new Hashtable<String,Integer>();
    }
    public void run() {
        Scanner sc = new Scanner(System.in);
        _lengthOfDNA = sc.nextInt();
        _lengthOfSegment = sc.nextInt();
        String dna = sc.next();
        generate(dna);
        int numOfQueries = sc.nextInt();
        while(numOfQueries>0){
            numOfQueries--;
            String subDNA = sc.next();
            int currResult = count(subDNA);
            System.out.println(currResult);
        }
    }
    
    /**
     *  generate        : use this method to generate/pre-process the substrings of length K from DNA
     *  Pre-condition   :
     *  Post-condition  :
     */
    public void generate(String dna) {
        // implementation
        for(int i =0; i+_lengthOfSegment<=_lengthOfDNA; i++){
            String currSubDNA = dna.substring(i,i+_lengthOfSegment);
            if(_hashTable.containsKey(currSubDNA)){
                _hashTable.put(currSubDNA,_hashTable.remove(currSubDNA)+1);
            }else{
                _hashTable.put(currSubDNA,1);
            }
        }
    }
    
    /**
     *  count           : use this method to answer one query.
     *  Pre-condition   :
     *  Post-condition  :
     */
    public int count(String substring) {
        // implementation
        if(_hashTable.containsKey(substring)){
            return _hashTable.get(substring);
        }else{
            return 0;
        }
    }

    public static void main(String[] args) {
        DNA dna = new DNA();
        dna.run();
    }
}
