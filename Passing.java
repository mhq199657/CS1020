
/**
*	Name		:
*	Matric No.	:
*/

import java.util.*;

public class Passing {
	LinkedList<Person> _personList;
	int _personRemaining;
	int _ballPosition;//0 based
	int _maxNumOfPassing;
	public Passing(){
		_personList = new LinkedList<Person>();
		_ballPosition = 0;
	}
	public void run(){
		Scanner sc = new Scanner(System.in);
		_personRemaining = sc.nextInt();
		int numOfPasses = sc.nextInt();
		_maxNumOfPassing = sc.nextInt();
		for(int i = 0; i<_personRemaining; i++){
			String personName = sc.next();
			_personList.addLast(new Person(personName));
		}
		//_personList.print();
		for(int i = 0; i<numOfPasses; i++){
			int oldPosition = pass(sc.nextInt());
			//_personList.print();
			//System.out.println(_ballPosition+" ");
			if(check()){
				//Exceeds
				remove();
			}else{
				swap(oldPosition,_ballPosition);
			}
		}
	}
	public void swap(int position1, int position2){
		//System.out.print(position1+","+ position2);
		if(position1==position2){
			return;
		}
		if(_ballPosition==position1){
			_ballPosition=position2;
		}else{
			_ballPosition=position1;
		}
		_personList.swap(Math.min(position1,position2),Math.max(position1,position2));
	}
	public void remove(){
		_personRemaining--;
		_personList.remove(_ballPosition);
		if(_ballPosition==_personRemaining){
			_ballPosition=0;
		}
	}
	public boolean check(){
		return _personList.get(_ballPosition).exceedsMaxPass(_maxNumOfPassing);
	}
	public int pass(int numOfPasses){
		int ballPosition = _ballPosition;
		int increment = numOfPasses%_personRemaining;
		_ballPosition = (_ballPosition+increment)%_personRemaining;
		//System.out.println(_ballPosition);
		//System.out.println(ballPosition+","+_ballPosition);
		_personList.get(_ballPosition).pass();
		return ballPosition;
	}
	public static void main(String[] args) {
		Passing ps = new Passing();
		ps.run();
	}
}

class LinkedList<E> {
	protected ListNode<E> head = null;
	private ListNode<E> tail = null;
	public LinkedList(){

	}
	public void addLast(E item){
		if(head == null){
			head = new ListNode<E>(item);
		}else{
			ListNode<E> current = head;
			while(current.getNext()!=null){
				current = current.getNext();
			}
			current.setNext(new ListNode<E>(item));
		}
	}
	public void remove(int index){
		if(index==0){
			head = head.next;
			return;
		}
		ListNode<E> previous = head;
		while(index>1){
			previous = previous.getNext();
			index--;
		}
		previous.setNext(previous.getNext().getNext());
	}
	public void print(){
		if(head == null){
			System.out.println();
			return;
		}
		ListNode<E> current = head;
		while(current!=null){
			System.out.print(current.getElement()+" ");
			current = current.getNext();
		}
	}
	public E get(int index){
		if(index<0){
			return null;
		}
		ListNode<E> current = head;
		while(index>0){
			current = current.getNext();
			if(current==null){
				return null;
			}
			index--;
		}
		return current.getElement();
	}
	public ListNode<E> getPtrAt(int index){
		if(index<0){
			return null;
		}
		ListNode<E> current = head;
		while(index>0){
			current = current.getNext();
			if(current==null){
				return null;
			}
			index--;
		}
		return current;
	}
	public void swap(int index1, int index2){
		ListNode<E> node1 = getPtrAt(index1);
		ListNode<E> beforeNode1 = getPtrAt(index1-1);
		ListNode<E> afterNode1 = getPtrAt(index1+1);
		ListNode<E> beforeNode2 = getPtrAt(index2-1);
		ListNode<E> node2 = getPtrAt(index2);
		ListNode<E> afterNode2 = getPtrAt(index2+1);
		if(beforeNode1==null){
			head = node2;
			if(afterNode1==node2){
				node2.setNext(node1);
				node1.setNext(afterNode2);
				return;
			}
			node2.setNext(afterNode1);
			beforeNode2.setNext(node1);
			node1.setNext(afterNode2);
			return;
		}
		beforeNode1.setNext(node2);
		if(afterNode1==node2){
				node2.setNext(node1);
				node1.setNext(afterNode2);
				return;
			}
			node2.setNext(afterNode1);
			beforeNode2.setNext(node1);
			node1.setNext(afterNode2);
			return;
	}
	public int findIndex(E item) {
		if(head == null){
			return -1;
		}
		ListNode<E> current = head;
		int cursor = 0;
		while(current!=null){
			if(current.getElement()==item){
				return cursor;
			}
			cursor++;
			current = current.getNext();
		}
		return -1;
	}
}

class ListNode<E> {
	protected E element;
	protected ListNode<E> next;

	/* constructors */
	public ListNode(E item) {
		element = item;
		next = null;
	}

	public ListNode(E item, ListNode<E> n) {
		element = item;
		next = n;
	}

	/* get the next ListNode */
	public ListNode<E> getNext() {
		return this.next;
	}

	/* get the element of the ListNode */
	public E getElement() {
		return this.element;
	}

	public void setNext(ListNode<E> item) {
		this.next = item;
	}

	public void setElement(E item) {
		this.element = item;
	}
}
class Person{
	private String _name;
	private int _numOfPasses;
	Person(String name){
		_name = name;
		_numOfPasses = 0;
	}
	public String getName(){
		return _name;
	}
	public int getNumOfPasses(){
		return _numOfPasses;
	}
	public void pass(){
		System.out.println(_name);
		_numOfPasses++;
	}
	public boolean exceedsMaxPass(int k){
		return _numOfPasses==k;
	}
	public String toString(){
		return _name;
	}
}
