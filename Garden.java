/**
 * Name 		: 
 * Matric No. 	: 
 * PLab Acct. 	:
 */

import java.util.*;

public class Garden {
    int[][] _mapArray;
    int[][] _prefixArray;
    int count;
    public void run() {
	    // treat this as your "main" method
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        _mapArray = new int[row][col];
        _prefixArray = new int[row][col];
        for(int i = 0; i<row; i++){
            String currRow = sc.next();
            for(int j = 0; j<col ;j++){
                char currChar = currRow.charAt(j);
                if(currChar == 'o'){
                    _mapArray[i][j]=1;
                }else{
                    _mapArray[i][j]=0;
                }
            }
        }
        calculatePrefixArray();
        for(int i = 0; i<row; i++){
            for(int j=0; j<col; j++){
                count(i,j, row, col);
            }
        }
        System.out.println(count);
    }
    private int subcount(int i, int j, int k){
        if(i==0){
            if(j==0){
                return _prefixArray[i+k][j+k];
            }else{
                return _prefixArray[i+k][j+k]-_prefixArray[i+k][j-1];
            }
        }else{
            if(j==0){
                return _prefixArray[i+k][j+k]-_prefixArray[i-1][j+k];
            }else{
                return _prefixArray[i+k][j+k]-_prefixArray[i-1][j+k]-_prefixArray[i+k][j-1]+_prefixArray[i-1][j-1];
        }
    }
    }
    private void count(int i, int j, int row, int col){
        for(int k = 0; i+k<row&&j+k<col; k++){
            int numOfMines = subcount(i,j,k);
            if(numOfMines==0){
                count++;
            }
        }
    }
    private void calculatePrefixArray(){
        for(int i = 0; i<_mapArray.length; i++){
            for(int j = 0; j<_mapArray[0].length; j++){
                if(j==0){
                    if(i==0){
                        _prefixArray[i][j]=_mapArray[i][j];
                    }else{
                        _prefixArray[i][j]=_mapArray[i][j]+_prefixArray[i-1][j];
                    }
                }else{
                    if(i==0){
                        _prefixArray[i][j]=_mapArray[i][j]+_prefixArray[i][j-1];
                    }else{
                        _prefixArray[i][j]=_mapArray[i][j]+_prefixArray[i][j-1]+_prefixArray[i-1][j]-_prefixArray[i-1][j-1];
                    }
                }
            }
            }
    }
    public static void main(String[] args) {
        Garden myBeautifulGarden = new Garden();
        myBeautifulGarden.run();
    }
}
