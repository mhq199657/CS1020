/**
 * Name			:
 * Matric. No	:
 * PLab Acct.	:
 */

import java.util.*;

public class Subway {
	DoublyLinkedList<Station> _stationList;
	public Subway() {
		//constructor
		_stationList = new DoublyLinkedList<Station>();
	}

	public void run() {
		//implement your "main" method here
		Scanner sc = new Scanner(System.in);
		int numOfStations = sc.nextInt();
		for(int i = 0; i<numOfStations;i++){
			String stationName = sc.next();
			_stationList.addLast(new Station(stationName));
		}
		//System.out.println(_stationList.size());
		int numOfQueries = sc.nextInt();
		while(numOfQueries>0){
			numOfQueries--;
			switch(sc.next()){
				case "TIME":{
					String start = sc.next();
					String end = sc.next();
					time(start, end,1);
					break;
				}
				case "BUILD":{
					String after = sc.next();
					String stationName = sc.next();
					int afterIndex = getStationIndex(after);
					//System.out.print(afterIndex);
					_stationList.addAfter(afterIndex, new Station(stationName));
					System.out.println("station "+stationName+" has been built");
					break;
				}
				case "PATH":{
					String start = sc.next();
					String end = sc.next();
					path(start, end);
					break;
				}
				case "PRINT":{
					String start = sc.next();
					print(start);
					break;
				}
			}
		}
	}
	private void print(String start){
		int startIndex = getStationIndex(start);
		String toBePrint = "";
		for(int i = startIndex; i<_stationList.size();i++){
			toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
		}
		for(int i = 0; i<startIndex; i++){
			toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
		}
		toBePrint=toBePrint.trim();
		System.out.println(toBePrint);
	}
	private void path(String start, String end){
		int startIndex = getStationIndex(start);
		int endIndex = getStationIndex(end);
		//System.out.println(startIndex+" "+ endIndex);
		int dir = time(start, end,0);
		//System.out.println(dir);
		String toBePrint = "";
		if(dir == 1){
			if(startIndex<=endIndex){
				for(int i = startIndex; i<=endIndex;i++){
					toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
				}
			}else{
				for(int i = startIndex; i<_stationList.size(); i++){
					toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
				}
				for(int i = 0; i<=endIndex; i++){
					toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
				}
			}
		}else{
			if(startIndex<=endIndex){
				for(int i = startIndex; i>=0; i--){
					toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
				}
				for(int i = _stationList.size()-1; i>=endIndex;i--){
					toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
				}
			}else{
				for(int i = startIndex; i>=endIndex; i--){
					toBePrint =toBePrint+ _stationList.get(i).getName()+" ";
				}
			}
		}
		toBePrint = toBePrint.trim();
		System.out.println(toBePrint);
	}
	private int time(String start, String end, int isToPrint){
		int station1 = getStationIndex(start);
		int station2 = getStationIndex(end);
		int temp1 = Math.max(station1, station2);
		int temp2 = Math.min(station1, station2);
		station1 = temp2;
		station2 = temp1;
		int size = _stationList.size();
		//System.out.println(station1+","+station2+","+size);
		int time1 = (station2-station1)*2+(station2-station1-1>0?(station2-station1-1):0);
		int time2 = (size-station2+station1)*2+(size-station2+station1-1>0?size-station2+station1-1:0);
		if(getStationIndex(start) == station1){//clockwise return 1; anticlockwise return -1
			if(time1<=time2){
				if(isToPrint==1){
					System.out.println(time1);
				}
				return 1;
			}else{
				if(isToPrint==1){
					System.out.println(time2);
				}
				return -1;
			}
		}else{
			if(time2<=time1){
				if(isToPrint==1){
					System.out.println(time2);
				}
				return 1;
			}else{
				if(isToPrint==1){
					System.out.println(time1);
				}
				return -1;
			}
		}
	}
	private int getStationIndex(String stationName){
		ListNode<Station> current = _stationList.getHead();
		int cursor = 0;
		while(current!=null){
			if(current.getElement().getName().equals(stationName)){
				return cursor;
			}
			cursor++;
			current = current.getNext();
		}
		return -1;
	}
	public static void main(String[] args) {
		Subway newSubwayNetwork = new Subway();
		newSubwayNetwork.run();
	}
}

class DoublyLinkedList<E> {
    
    //Data attributes
	private ListNode<E> head;
	private ListNode<E> tail;
	private int size;

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	// returns the size of the linked list
	public int size() {
		return this.size;
	}

	// returns true if the list is empty, false otherwise
	public boolean isEmpty() {
		return this.size == 0;
	}

	// adds the specified element to the beginning of the list
	public void addFirst(E element) {
		ListNode<E> newNode = new ListNode<E>(element);

		if (size == 0) {
			this.head = newNode;
			this.tail = this.head;
		} else {
			ListNode<E> oldHead = this.head;
			this.head = newNode;
			newNode.setNext(oldHead);
			oldHead.setPrev(newNode);
		}

		this.size++;
	}
	public void addLast(E element){
		ListNode<E> newNode = new ListNode<E>(element);
		if(size==0){
			head = newNode;
			tail = newNode;
			size++;
			return;
		}
		tail.setNext(newNode);
		tail = newNode;
		size++;
	}
	public void addAfter(int index, E element){
		ListNode<E> newNode = new ListNode<E>(element);
		ListNode<E> current = head;
		while(index>0){
			current = current.getNext();
			index--;
		}
		if(current == tail){
			//System.out.println("here"+((Station)element).getName());
			current.setNext(newNode);
			tail = newNode;
			size++;
			return;
		}
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		this.size++;
	}
	// retrieves the first element of the list
	public E getFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("Cannot get from an empty list");
		} else {
			return head.getElement();
		}
	}
	public E get(int index){
		ListNode<E> current = head;
		while(index>0){
			index--;
			current = current.getNext();
		}
		return current.getElement();
	}
	// returns true if the list contains the element, false otherwise
	public boolean contains(E element) {
		for (ListNode<E> current = head; current != null; current = current.getNext()) {
			if (current.getElement().equals(element)) {
				return true;
			}
		}

		return false;
	}

	// removes the first element in the list
	public E removeFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException("Cannot remove from an empty list");
		} else {
			ListNode<E> currentHead = head;
			head = head.getNext();
			if (head == null) {
				tail = null;
			} else {
				head.setPrev(null);
			}
			this.size--;
			return currentHead.getElement();
		}
	}
    
    // Returns reference to first node.
    public ListNode<E> getHead() {
        return this.head;
    }
    
    // Returns reference to last node of list.
    public ListNode<E> getTail() {
        return this.tail;
    }

}

class ListNode<E> {
	private E element;
	private ListNode<E> next;
	private ListNode<E> prev;

	public ListNode(E newElement) {
		this.element = newElement;
		this.next = null;
		this.prev = null;
	}

	public void setElement(E newElement) {
		this.element = newElement;
	}

	public E getElement() {
		return this.element;
	}

	public void setPrev(ListNode<E> previous) {
		this.prev = previous;
	}

	public void setNext(ListNode<E> next) {
		this.next = next;
	}

	public ListNode<E> getNext() {
		return next;
	}

	public ListNode<E> getPrev() {
		return prev;
	}
}
class Station{
	private String _name;
	Station(String name){
		_name = name;
	}
	public String getName(){
		return _name;
	}
}