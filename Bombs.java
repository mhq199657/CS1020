/*
 * Name		:		Ma Hongqiang
 * Matric No.	:	A0136093H
 */

import java.util.*;
public class Bombs {
	public static int[] query(int[][] map, int range, int type){
		int num_of_rows	= map.length;
		int num_of_cols = map[0].length;
		int highest_so_far = -1-num_of_rows*num_of_cols;
		int highest_x=-1;
		int highest_y=-1;
		int score_now= -1-num_of_rows*num_of_cols;
		for(int i = 0; i<num_of_rows; i++){
			for(int j = 0; j<num_of_cols; j++){
				score_now = evaluate_score(map,(range-1)/2,i,j,num_of_rows,num_of_cols, type);
				if(score_now>highest_so_far){
					highest_so_far=score_now;
					highest_x=i;
					highest_y=j;
				}
			}
		}
		int[] result = {highest_x,highest_y,highest_so_far};
		return result;
	}
	private static int evaluate_score(int[][] map, int half_length, int x, int y,int max_x,int max_y, int type){
		int score = 0;
		//Evaluate score
		for(int i = Math.max(0, x-half_length); i<=Math.min(max_x-1,x+half_length); i++){
			for(int j = Math.max(0,y-half_length); j<=Math.min(max_y-1,y+half_length); j++){
				if(type==2){
					if(map[i][j]==0){
						score--;
					}else{
						score=score+3;
					}
				}
				if(type==1){
					if(map[i][j]==1){
						score++;
					}
				}
			}
		}
		//For debugging
		//
		return score;
	}
    public static void main(String[] args) {
    	//Input map
    	Scanner sc = new Scanner(System.in);
    	int num_of_rows = sc.nextInt();
    	int num_of_cols = sc.nextInt();
    	int[][] map = new int[num_of_rows][num_of_cols];
    	for(int i = 0; i<num_of_rows;i++){
    		for(int j = 0; j<num_of_cols;j++){
    			map[i][j]=sc.nextInt();
    		}
    	}
    	//Input number of queries type 1
    	int num_of_type_one = sc.nextInt();
    	int bomb_range;
    	//Start queries
    	int[] result;
    	for(int i = 0; i<num_of_type_one; i++){
    		bomb_range = sc.nextInt();
    		result = query(map,bomb_range,1);
    		System.out.println(result[0]+" "+result[1]);
    	}
    	int[] optimal ={-1,-1,-1-map.length*map[0].length,-1};
    	int max_length;
    	if(Math.max(map.length,map[0].length)%2==0){
    		max_length = Math.max(map.length,map[0].length)+1;
    	}else{
    		max_length = Math.max(map.length,map[0].length);
    	}
    	for(int r = 1; r<=max_length; r=r+2){
    		result = query(map,r,2);
    		if(result[2]>optimal[2]){
    			//System.out.println("Previous score:"+optimal[2]+"; new:"+result[2]);
    			optimal[0]=result[0];
    			optimal[1]=result[1];
    			optimal[2]=result[2];
    			optimal[3]=r;
    		}
    	}
    	System.out.println(optimal[0]+" "+optimal[1]+" "+optimal[3]);
    	return;
    }
    
}
