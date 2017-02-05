/**
 * Name         :
 * Matric No.   :
 * Plab Acct.   :
 */
import java.util.*;
public class Storage {
    private Vector<Box> _boxList=null;
    private Vector<Item> _itemList=null;
    private Item _latestPurchase;
    private int _maxInventory = 0;
    private int _maxItemPerBox = 0;
    Storage(){
    }
    private void init(Scanner sc){
        int noOfBoxes= sc.nextInt();
        _boxList = new Vector<Box>(noOfBoxes);
        _maxInventory = sc.nextInt();
        _itemList = new Vector<Item>(_maxInventory);
        _maxItemPerBox = sc.nextInt();
        Box._maxSize = _maxItemPerBox;
        for(int i = 0; i<noOfBoxes; i++){
            _boxList.add(new Box());
        }
    }
    private void purchase(String itemName, int value){
        _latestPurchase = new Item(itemName, value);
        if(_itemList.size()<_maxInventory){
            _itemList.add(_latestPurchase);
            System.out.println("item "+itemName+" is now being held");
        }else{
            deposit(_latestPurchase);
        }
    }
    private boolean isInBox(String itemName){
        for(int i = 0; i<_boxList.size(); i++){
            Box currentBox = _boxList.get(i);
            if(currentBox.isInBox(itemName)){
                return true;
            }
        }
        return false;
    }
    private int isInInventory(String itemName){
        for(int i = 0; i<_itemList.size(); i++){
            Item currentItem = _itemList.get(i);
            if(currentItem.getName().equals(itemName)){
                return i;
            }
        }
        return -1;
    }
    private void deposit(Item item){
        for(int i = 0; i<_boxList.size(); i++){
            if(_boxList.get(i).isFull()){
                continue;
            }else{
                _boxList.get(i).addItem(item);
                System.out.println("item "+item.getName()+" has been deposited to box "+(i+1));
                _latestPurchase = null;
                break;
            }
        }
    }
    private void deposit(String itemName){
        if(isInBox(itemName)){
            System.out.println("item "+itemName+" is already in storage");
        }else{
            if(isInInventory(itemName)!=-1){//found
                int itemIndex = isInInventory(itemName);
                Item currentItem = _itemList.get(itemIndex);
                for(int i = 0; i<_boxList.size(); i++){
                    if(_boxList.get(i).isFull()){
                        continue;
                    }else{
                        _boxList.get(i).addItem(currentItem);
                        System.out.println("item "+itemName+" has been deposited to box "+(i+1));
                        _itemList.remove(currentItem);
                        break;
                    }
                }
            }else{
                System.out.println("item "+itemName+" does not exist");
            }
        }
    }
    private void withdraw(String itemName){
        if(isInInventory(itemName)!=-1){//found
            System.out.println("item "+itemName+" is already being held");
        }else{
            if(!isInBox(itemName)){
                System.out.println("item "+itemName+" does not exist");
            }else{
                if(_itemList.size()>=_maxInventory){
                    System.out.println("cannot hold any more items");
                }else{
                    for(int i = 0; i<_boxList.size(); i++){
                        Box currentBox = _boxList.get(i);
                        if(currentBox.isInBox(itemName)){
                            for(int j = 0; j<currentBox.size(); j++){
                                Item currentItem = currentBox.get(j);
                                if(currentItem.getName().equals(itemName)){
                                    _itemList.add(currentItem);
                                    currentBox.remove(currentItem);
                                    System.out.println("item "+itemName+" has been withdrawn");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private void location(String itemName){
        if(isInInventory(itemName)!=-1){
            System.out.println("item "+itemName +" is being held");
            return;
        }else{
            if(!isInBox(itemName)){
                System.out.println("item "+itemName+" does not exist");
                return;
            }else{
                for(int i = 0; i<_boxList.size(); i++){
                        Box currentBox = _boxList.get(i);
                        if(currentBox.isInBox(itemName)){
                            System.out.println("item "+itemName+" is in box "+(i+1));
                            return;
                        }
                    }
            }
        }
    }
    private void valuable(){
        int maxValue = -1;
        String maxValueItemName = "";
        for(int i = 0; i<_itemList.size(); i++){
            Item currentItem = _itemList.get(i);
            if(currentItem.getValue()>maxValue){
                maxValue = currentItem.getValue();
                maxValueItemName = currentItem.getName();
            }else if(currentItem.getValue()==maxValue){
                if(currentItem.getName().compareTo(maxValueItemName)<0){
                    maxValueItemName=currentItem.getName();
                }
            }else{

            }
        }
        for(int i = 0; i<_boxList.size(); i++){
            Box currentBox = _boxList.get(i);
            for(int j = 0; j<currentBox.size(); j++){
                Item currentItem = currentBox.get(j);
                //System.out.print(currentItem.getName());
                if(currentItem.getValue()>maxValue){
                    maxValue = currentItem.getValue();
                    maxValueItemName = currentItem.getName();
                }else if(currentItem.getValue()==maxValue){
                    if(currentItem.getName().compareTo(maxValueItemName)<0){
                        maxValueItemName=currentItem.getName();
                    }
                }else{

                }
            }
        }
        System.out.println(maxValueItemName);
    }
    public void run() {
    // treat this as your "main" method
        Scanner sc = new Scanner(System.in);
        init(sc);
        int num_of_queries = sc.nextInt();
        while(num_of_queries>0){
            switch(sc.next()){
                case "purchase":{
                    String itemName = sc.next();
                    int value = sc.nextInt();
                    purchase(itemName, value);
                    num_of_queries--;
                    break;
                }
                case "deposit":{
                    String itemName = sc.next();
                    deposit(itemName);
                    num_of_queries--;
                    break;
                }
                case "withdraw":{
                    String itemName = sc.next();
                    withdraw(itemName);
                    num_of_queries--;
                    break;
                }
                case "location":{
                    String itemName = sc.next();
                    location(itemName);
                    num_of_queries--;
                    break;
                }
                case "valuable":{
                    valuable();
                    num_of_queries--;
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
    Storage myStorageSystem = new Storage();
        myStorageSystem.run();
    }
}
class Box {
    public static int _maxSize;
    private int _currentSize;
    private Vector<Item> _itemList;
    Box(){
        _currentSize = 0;
        _itemList = new Vector<Item>(_maxSize);
    }
    public int size(){
        return _currentSize;
    }
    public Item get(int index){
        return _itemList.get(index);
    }
    public void remove(Item item){
        _itemList.remove(item);
        _currentSize--;
    }
    public boolean isInBox(String itemName){
        for(int i = 0; i<_itemList.size(); i++){
            Item currentItem = _itemList.get(i);
            if(currentItem.getName().equals(itemName)){
                return true;
            }
        }
        return false;
    }
    public void addItem(Item item){
        _itemList.add(item);
        _currentSize++;
    }
    public boolean isFull(){
        return _itemList.size()>=_maxSize;
    }

}
class Item {
    private String _itemName;
    private int _value;
    Item(String itemName, int value){
        _itemName = itemName;
        _value = value;
    }
    public String getName(){
        return _itemName;
    }
    public int getValue(){
        return _value;
    }

}