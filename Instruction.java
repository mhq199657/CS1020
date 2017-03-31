import java.util.*;

class Instruction {
    int[] numArray;
    char[] operatorArray;
    int target;
    int bestSoFar;
    int diff=-1;
    boolean sgn;
    Instruction(){
    }
    public void run(){
        Scanner sc = new Scanner(System.in);
        int numOfElements = sc.nextInt();
        target = sc.nextInt();
        numArray = new int[numOfElements];
        operatorArray = new char[numOfElements];
        for(int i = 0; i<numOfElements; i++){
            operatorArray[i] = sc.next().charAt(0);
            numArray[i]=sc.nextInt();
        }
        operate();
        System.out.println(bestSoFar);
    }
    private void operate(){
        operate(0,0);
    }
    private void operate(int curr, int index){
        if(Math.abs(curr-target)<diff||diff==-1){
            bestSoFar = curr;
            diff = Math.abs(target-curr);
            sgn = bestSoFar>target;
        }else if(Math.abs(curr-target)==diff){
            if(sgn){
                if(curr-target<0){
                    bestSoFar = curr;
                    sgn = false;
                }
            }
        }else{
        }
        if(index>=numArray.length){
            return;
        }
        operate(calculate(curr, index), index+1);
        operate(curr,index+1);
    }
    private int calculate(int curr, int index){
        switch(operatorArray[index]){
            case '+':{
                return curr+numArray[index];
            }
            case '-':{
                return curr-numArray[index];
            }
            case '*':{
                return curr*numArray[index];
            }
            case '/':{
                return curr/numArray[index];
            }
        }
        return -1;
    }
    public static void main(String[] args) {
		Instruction ins = new Instruction();
	    ins.run();
    }
}
