import java.util.*;

public class PSI {
    long[] _numArray;
    long[] _sumArray;
    long[] _temp;
    long _count = 0;
    public void run(){
        Scanner sc = new Scanner(System.in);
        int numOfElements = sc.nextInt();
        _numArray = new long[numOfElements];
        _sumArray = new long[numOfElements+1];
        _temp = new long[numOfElements+1];
        for(int i = 0; i<numOfElements; i++){
            _numArray[i]=sc.nextInt();
        }
        _sumArray[0]=0;
        for(int i = 0; i<numOfElements; i++){
            _sumArray[i+1]=_sumArray[i]+_numArray[i];
            _temp[i+1]=_sumArray[i+1];
        }
        mergeSort();
        System.out.println(_count);
    }
    private void mergeSort(){
        mergeSort(0, _sumArray.length-1);
    }
    private void mergeSort(int low, int high){
        if(low<high){
            int mid = (high+low)/2;
            mergeSort(low,mid);
            mergeSort(mid+1, high);
            merge(low, mid, high);
        }
    }
    private void merge(int low, int mid, int high){
        int i = low;
        int j = mid+1;
        int k = low;
        while(i<mid+1&&j<high+1){
            if(_sumArray[i]<_sumArray[j]){
                _temp[k]=_sumArray[i];
                i++;
                k++;
            }else{
                _temp[k]=_sumArray[j];
                _count = _count+(i-low);
                j++;
                k++;
            }
        }
        while(i<mid+1){
            _temp[k]=_sumArray[i];
            i++;
            k++;
        }
        while(j<high+1){
            _temp[k]=_sumArray[j];
            _count = _count+(i-low);
            k++;
            j++;
        }
        for(int x = low; x<high+1; x++){
            _sumArray[x]=_temp[x];
        }
    }
	public static void main(String [] args) {
	    PSI psi = new PSI();
        psi.run();
	}
    
}

