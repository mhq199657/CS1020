import java.util.*;
class GasStation{
    int[] _cityList;
    int[] _priceList;
    static final int MAX_CAPACITY = 200;
    int currMin = -1;
    GasStation(){
    }
    public void run(){
        Scanner sc = new Scanner(System.in);
        int numOfStations = sc.nextInt();
        _cityList = new int[numOfStations+1];
        _priceList = new int[numOfStations];
        for(int i = 0; i<numOfStations; i++){
            _cityList[i]=sc.nextInt();
            _priceList[i]= sc.nextInt();
        }
        _cityList[numOfStations]=sc.nextInt();
        travel();
        if(currMin==-1){
            System.out.println("can meh?");
        }else{
        System.out.println(currMin);
        }
    }
    private void travel(){
        travel(0,MAX_CAPACITY-_cityList[0], 0);
    }
    private void travel(int origin, int fuelBeforeTravel,int currPrice){
        if(origin==_priceList.length){
            if(fuelBeforeTravel>=0){
                if(currMin==-1||currPrice<currMin){
                    currMin = currPrice;
                }
            }
            return;
        }
        if(fuelBeforeTravel<0){
            return;
        }
        travel(origin+1, fuelBeforeTravel-_cityList[origin+1],currPrice);
        travel(origin+1,MAX_CAPACITY-_cityList[origin+1],currPrice+(MAX_CAPACITY-fuelBeforeTravel)*_priceList[origin]);
}
    public static void main(String[] args){
        GasStation gs = new GasStation();
        gs.run();
    }
}
