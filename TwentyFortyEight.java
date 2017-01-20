/*
 * Name		:			Ma Hongqiang
 * Matric No.		:	A0136093H
 */

import java.util.*;

public class TwentyFortyEight {
	static int[][] board = new int[4][4];
	static final int LENGTH = 4;
	public static void mergeleft(){
	killzero();
	for(int i = 0;i<LENGTH; i++){
			for(int j = 0; j<LENGTH; j++){
				//System.out.print(board[i][j]+((j==LENGTH-1)?"\n":" "));
			}
	}
	for(int i=0; i<LENGTH; i++){
			merge(i,0);
		}
	}
	private static void merge(int x,int y){
		if(y==LENGTH-1){
			return;
		}
		if(board[x][y]==0){
			return;
		}
		if(board[x][y]==board[x][y+1]){
			int cursor;
			//System.out.println("Merged at ("+x+","+y+")");
			board[x][y]=2*board[x][y];
			for(cursor = y+1; cursor+1<LENGTH; cursor++){
				//System.out.println("Board set at "+x+","+cursor);
				board[x][cursor]=board[x][1+cursor];
			}
			for(int i = cursor; i<LENGTH; i++ ){
				board[x][i]=0;
				//System.out.println("Board set at "+x+","+i);
			}
			merge(x,y+1);
			return;
		}
		merge(x,y+1);
		return;
	}
	private static void killzero(){
		for(int i = 0; i<LENGTH;i++){
			killzero(i);
		}
	}
	private static void killzero(int x){
		int[] temp = new int[]{0,0,0,0};
		int cursor = 0;
		for(int i = 0; i<LENGTH;i++){
			if(board[x][i]==0){
				continue;
			}else{
				temp[cursor]=board[x][i];
				cursor++;
			}
		} 
		for(int i = 0; i<LENGTH;i++){
			board[x][i]=temp[i];
		}
	}

	public static void rotateleft(){
		int[][] temp = new int[4][4];
		for(int i = 0; i<LENGTH; i++){
			for(int j = 0; j<LENGTH; j++){
				temp[i][j]=board[j][3-i];
			}
		}
		board = temp;
		return;
	}
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i = 0;i<LENGTH; i++){
			for(int j = 0; j<LENGTH; j++){
				board[i][j] = sc.nextInt();
			}
		}
		int dir = sc.nextInt();
		switch(dir){
			case 0:{
				mergeleft();
				break;
			}
			case 1:{
				rotateleft();
				mergeleft();
				rotateleft();
				rotateleft();
				rotateleft();
				break;
			}
			case 2:{
				rotateleft();
				rotateleft();
				mergeleft();
				rotateleft();
				rotateleft();
				break;
			}case 3:{
				rotateleft();
				rotateleft();
				rotateleft();
				mergeleft();
				rotateleft();
				break;
			}
		}
		for(int i = 0;i<LENGTH; i++){
			for(int j = 0; j<LENGTH; j++){
				System.out.print(board[i][j]+((j==LENGTH-1)?"\n":" "));
			}
		}
		return;
    }
    
}
