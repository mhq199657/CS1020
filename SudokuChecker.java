import java.util.*;
public class SudokuChecker{
	private static final int SIZE = 9;
	private static int[][] board = new int[9][9];
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		board_init(sc);
		boolean status = board_check();
		System.out.println(status);
	}
	private static void board_init(Scanner sc){
		for(int r = 0; r<SIZE; r++){
			for(int c = 0; c<SIZE;c++){
				board[r][c]=sc.nextInt();
			}
		}
	}
	private static boolean board_check(){
		return check_rows()&&check_cols()&&check_squares();
	}
	private static boolean check_rows(){
		boolean status = true;
		for(int i = 0; i<SIZE; i++){
			status = status&check_rows(i);
		}
		return status;
	}
	private static boolean check_rows(int row_num){
		int[] num_to_be_checked = retrieve(row_num, "ROW");
		return check(num_to_be_checked);
	}
	private static boolean check_cols(){
		boolean status = true;
		for(int i = 0; i<SIZE; i++){
			status = status&check_cols(i);
		}
		return status;
	}
	private static boolean check_cols(int col_num){
		int[] num_to_be_checked = retrieve(col_num, "COL");
		return check(num_to_be_checked);
	}
	private static boolean check_squares(){
		boolean status = true;
		for(int i = 0; i<SIZE; i++){
			status = status&check_squares(i);
		}
		return status;
	}
	private static boolean check_squares(int sqr_num){
		int[] num_to_be_checked = retrieve(sqr_num+1, "SQR");
		return check(num_to_be_checked);
	}
	private static int[] retrieve(int num, String mode){
		int[] num_array = new int[9];
		switch(mode){
			case "ROW":{
				for(int i = 0; i<SIZE; i++){
					num_array[i]= board[num][i];
				}
				return num_array;
			}
			case "COL":{
				for(int i = 0; i<SIZE; i++){
					num_array[i]=board[i][num];
				}
				return num_array;
			}
			case "SQR":{
				int x_min = ((num-1)%3)*3;
				int x_max = x_min+2;
				int y_min = ((num-1)/3)*3;
				int y_max = y_min+2;
				int cursor = 0;
				for(int i = x_min; i<=x_max; i++){
					for(int j = y_min; j<=y_max;j++){
						num_array[cursor] = board[i][j];
						cursor++;
					}
				}
				cursor = 0;
				return num_array;
			}
		}
		return num_array;
	}
	private static boolean check(int[] num_array){
		Arrays.sort(num_array);
		for(int i = 0; i < SIZE; i++){
			if(num_array[i]!=i+1){
				return false;
			}
		}
		return true;
	}



}