/*
 Name:
 Matric No.:
 */

import java.util.*;

public class Island {
	public static void crawl(int[][] map,boolean[][] map_crawled,int x,int y){
        if(x==map.length||y==map[0].length){
            return;
        }
		if(map[x][y]==0){
			return;
		}if(map_crawled[x][y]){
            return;
        }else{
            //System.out.print("Crawled at "+x+" "+y);
			map_crawled[x][y]=true;
		}
		crawl(map,map_crawled,x+1,y);
		crawl(map,map_crawled,x,y+1);
		return;
	}
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num_of_rows = sc.nextInt();
        int num_of_cols = sc.nextInt();
        int[][] map = new int[num_of_rows][num_of_cols];
        boolean[][] map_crawled = new boolean[num_of_rows][num_of_cols];
        for(int i = 0 ; i<num_of_rows;i++){
        	for(int j = 0; j<num_of_cols; j++){
        		map[i][j]= sc.nextInt();
        		map_crawled[i][j]=false;
        	}
        }
        int num_of_islands = 0;
        for(int i = 0; i<num_of_rows; i++){
        	for(int j = 0; j<num_of_cols; j++){
        		if(map_crawled[i][j]){
        			continue;
        		}else if(map[i][j]==0){
        			map_crawled[i][j]=true;
        		}else{//have plot
        			num_of_islands++;
        			crawl(map,map_crawled,i,j);
        		}
        	}
        }
        System.out.println(num_of_islands);
    }
}
