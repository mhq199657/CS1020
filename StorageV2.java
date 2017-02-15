import java.util.*;
public class Storage{
    private Vector<Item> _itemList = null;
    private Vector<Item> _bag = null;
    private Vector<Box> _boxList = null;
    private HashMap<String,Item> _itemMap = null;
    private int MAXITEMINBAG;
    Storage(){
        _bag = new Vector<Item>();
        _boxList = new Vector<Box>();
        _itemMap = new HashMap<String, Item>();
    }
    public void run(){
        Scanner sc = new Scanner(System.in);
        int numOfBoxes = sc.nextInt();
        int MAXITEMINBAG = sc.nextInt();
        this.MAXITEMINBAG = MAXITEMINBAG;
        int MAXITEMINBOX = sc.nextInt();
        int numOfQueries = sc.nextInt();
        for(int i = 0; i<numOfBoxes; i++){
            _boxList.add(new Box(MAXITEMINBOX, i+1));
        }
        while(numOfQueries>0){
            numOfQueries--;
            switch(sc.next()){
                case "purchase":{
                    String itemName = sc.next();
                    int itemValue = sc.nextInt();
                    Item newItem = new Item(itemName, itemValue);
                    _itemMap.put(itemName, newItem);
                    purchase(newItem);
                    break;
                }
                case "deposit":{
                    String itemName = sc.next();
                    deposit(itemName);
                    break;
                }
                case "withdraw":{
                    String itemName = sc.next();
                    withdraw(itemName);
                    break;
                }
                case "location":{
                    String itemName = sc.next();
                    location(itemName);
                    break;
                }
                case "valuable":{
                    valuable();
                    break;
                }
            }
        }
    }
    public void purchase(Item item){
        if(_bag.size()<MAXITEMINBAG){
            _bag.add(item);
            System.out.println("item "+item.getName()+" is now being held");
            item.setLocation(null);
        }else{
            //bag is full
            Box currentBox = null;
            int cursor = 1;
            for(Box box: _boxList){
                if(box.isFull()){
                    cursor++;
                    continue;
                }
                currentBox = box;
                break;
            }
            currentBox.add(item);
            System.out.println("item "+item.getName()+" has been deposited to box "+cursor);
        }
    }
    public void deposit(String itemName){
        Item item = _itemMap.get(itemName);
        if(item == null){
            System.out.println("item "+itemName+" does not exist");
            return;
        }
        Box box = item.getLocation();
        if(box!=null){
            System.out.println("item "+itemName+" is already in storage");
            return;
        }
        _bag.remove(item);
        int cursor = 1;
        for(Box b: _boxList){
            if(!b.isFull()){
                b.add(item);
                System.out.println("item "+itemName+" has been deposited to box "+cursor); 
                return;
            }
            cursor++;
        }
    }
    public void withdraw(String itemName){
        Item item = _itemMap.get(itemName);
        if(item == null){
            System.out.println("item "+itemName+" does not exist");
            return;
        }
        Box box = item.getLocation();
        if(box==null){
            System.out.println("item "+itemName+" is already being held");
            return;
        }
        if(_bag.size()==MAXITEMINBAG){
            System.out.println("cannot hold any more items");
            return;    
        }
        item.setLocation(null);
        box.remove(item);
        _bag.add(item);
    }
    public void location(String itemName){
        Item item = _itemMap.get(itemName);
        if(item == null){
            System.out.println("item "+itemName+" does not exist");
            return;
        }
        Box box = item.getLocation();
        if(box==null){
            System.out.println("item "+itemName+" is already being held");
            return;
        }
        System.out.println("item "+itemName+" is in box "+box.getBoxNo());
    }
    public void valuable(){
        Item maxValueItem = null;
        int maxValue = -1;
        for(Item i: _bag){
            if(i.getValue()>maxValue){
                maxValueItem=i;
                maxValue = i.getValue();
            }
            if(i.getValue()==maxValue){
                if(maxValueItem.getName().compareTo(i.getName())>0){
                    maxValueItem = i;
                }
            }
        }
        for(Box b:_boxList){
            if(b.isEmpty()){
                break;
            }
            if(b.mostValuable().getValue()>maxValue){
                maxValueItem = b.mostValuable();
                maxValue = maxValueItem.getValue();
            }
            if(b.mostValuable().getValue()==maxValue){
                if(b.mostValuable().getName().compareTo(maxValueItem.getName())<0){
                    maxValueItem = b.mostValuable();
                }
            }
        }
        System.out.println(maxValueItem.getName());
    }       

    public static void main(String[] args){
        Storage storageManager = new Storage();
        storageManager.run();
    }
}

class Box{
    private int _boxCapacity;
    private int _boxSize;
    private Vector<Item> _itemList;
    private int _boxNo;
    Box(int boxCapacity, int boxNo){
        _boxCapacity = boxCapacity;
        _boxSize = 0;
        _itemList = new Vector<Item>(_boxCapacity);
        _boxNo=boxNo;
    }
    public boolean isEmpty(){
        return _boxSize==0;
    }
    public int getBoxNo(){
        return _boxNo;
    }
    public boolean isFull(){
        if(_boxCapacity==_boxSize){
            return true;
        }
        return false;
    }
    public void add(Item item){
        //Did not check for isFull
        _itemList.add(item);
        item.setLocation(this);
        _boxSize++;
    }
    public void remove(Item item){
        //Did not check for existence, need to be handled by Hashmap
        _itemList.remove(item);
        _boxSize--;
    }
    public Item mostValuable(){
        Item mostValuable = null;
        int mostValue = -1;
        for(Item i : _itemList){
            if(i.getValue()>mostValue){
                mostValuable = i;
                mostValue = i.getValue();
            }
        }
        return mostValuable;
    }
}

class Item{
    private String _itemName;
    private int _value;
    private Box _location;
    Item(String itemName, int value){
        _itemName = itemName;
        _value = value;
    }
    public Box  getLocation(){
        return _location;
    }
    public void setLocation(Box box){
        _location = box;
    }
    public String getName(){
        return _itemName;
    }
    public int getValue(){
        return _value;
    }
}
