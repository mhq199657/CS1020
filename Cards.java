/**
 * Name         :
 * Matric No    :
 * Plab Acct.   :
 *
 */

import java.util.*;

public class Cards {
    private TailedLinkedList<Person> _personList;
    Cards(){
        _personList = new TailedLinkedList<Person>();
    }
    public void run() {
        // implement your "main" method here
        Scanner sc = new Scanner(System.in);
        int numOfPersons = sc.nextInt();
        while(numOfPersons>0){
            numOfPersons--;
            String personName = sc.next();
            int personAge = sc.nextInt();
            Person newPerson = new Person(personName,personAge);
            _personList.addLast(newPerson);
        }
        int numOfQueries = sc.nextInt();
        while(numOfQueries>0){
            numOfQueries--;
            switch(sc.next()){
                case "swap":{
                    int firstLower = sc.nextInt();
                    int firstUpper = sc.nextInt();
                    int secondLower = sc.nextInt();
                    int secondUpper = sc.nextInt();
                    swap(firstLower, firstUpper,secondLower,secondUpper);
                    break;
                }
                case "details":{
                    int index = sc.nextInt();
                    details(index);
                    break;
                }
                case "position":{
                    String name = sc.next();
                    position(name);
                    break;
                }
                case "print":{
                    print();
                    break;
                }
                case "shuffle":{
                    shuffle();
                    break;
                }
            }
        }
    }
    private void shuffle(){
        if(_personList.size()==1){
            System.out.println("shuffle has been performed");
            return;
        }
        System.out.println("shuffle has been performed");
        ListNode<Person> middle = _personList.getPtrAt((_personList.size()-1)/2+1);
        _personList.getPtrAt((_personList.size()-1)/2).next=null;
        ListNode<Person> current = _personList.getHead();
        while(middle!=null){
            ListNode<Person> temp = middle;
            middle = middle.next;
            ListNode<Person> next = current.next;

            if(next==null){
                current.next = temp;
                temp.next = null;
                _personList.setTail(temp);
            }else{
                current.next = temp;
                temp.next = next;
            }
            current = next;

            if(middle==null){
                if(current!=null){
                    _personList.setTail(current);
                    current.next=null;
                }
            }
            
        }
    }
    private void position(String name){
        int cursor = 0;
        ListNode<Person> current = _personList.getHead();
        while(current!=null){
            if(current.element.getName().equals(name)){
                System.out.println((cursor+1)+"");
                return;
            }
            cursor++;
            current = current.next;
        }
    }
    private void details(int index){
        Person currentPerson = _personList.getPtrAt(index-1).element;
        System.out.println(currentPerson.getName()+" "+currentPerson.getAge());
    }
    private void swap(int firstLower, int firstUpper, int secondLower, int secondUpper){
        ListNode<Person> firstLowerPerson = _personList.getPtrAt(firstLower-1);
        ListNode<Person> firstUpperPerson = _personList.getPtrAt(firstUpper-1);
        ListNode<Person> secondLowerPerson = _personList.getPtrAt(secondLower-1); 
        ListNode<Person> secondUpperPerson = _personList.getPtrAt(secondUpper-1);
        ListNode<Person> beforeFirstLowerPerson = _personList.getPtrAt(firstLower-2);   
        ListNode<Person> afterFirstUpperPerson = _personList.getPtrAt(firstUpper);
        ListNode<Person> beforeSecondLowerPerson = _personList.getPtrAt(secondLower-2);
        ListNode<Person> afterSecondUpperPerson = _personList.getPtrAt(secondUpper);
        //System.out.print( afterSecondUpperPerson==null);
        if(firstLower==1){
            _personList.setHead(secondLowerPerson);
        }else{
            beforeFirstLowerPerson.next = secondLowerPerson;
            }   
        if(firstUpper==secondLower-1){
            secondUpperPerson.next = firstLowerPerson;
        }else{
            secondUpperPerson.next = afterFirstUpperPerson;
            beforeSecondLowerPerson.next = firstLowerPerson;
        }
        if(afterSecondUpperPerson==null){
            _personList.setTail(firstUpperPerson);
            firstUpperPerson.next = null;
        }else{
            firstUpperPerson.next=afterSecondUpperPerson;
        }
        System.out.println("swap has been performed");
        //print();
    }
    public void print(){
        ListNode<Person> ln = _personList.getHead();
        String out = "";
        while(ln!=null){
            out +=ln.element.getName()+" ";
            ln = ln.next;
        }
        System.out.println(out.trim());
    }
    public static void main(String[] args) {
        Cards myCards = new Cards();
        myCards.run();
    }
}

class TailedLinkedList<E> {

    // Data attributes
    private ListNode<E> head;
    private ListNode<E> tail;
    private int num_nodes;

    public TailedLinkedList() {
        this.head = null;
        this.tail = null;
        this.num_nodes = 0;
    }

    // Return true if list is empty; otherwise return false.
    public boolean isEmpty() {
        return (num_nodes == 0);
    }

    // Return number of nodes in list.
    public int size() {
        return num_nodes;
    }

    // Return value in the first node.
    public E getFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException("can't get from an empty list");
        else
            return head.getElement();
    }

    // Return true if list contains item, otherwise return false.
    public boolean contains(E item) {
        for (ListNode<E> n = head; n != null; n = n.getNext())
            if (n.getElement().equals(item))
                return true;

        return false;
    }



    // Add item to front of list.
    public void addFirst(E item) {
        head = new ListNode<E>(item, head);
        num_nodes++;
        if (num_nodes == 1) tail = head;
    }

    // Return reference to first node.
    public ListNode<E> getHead() {
        return head;
    }

    // Return reference to last node of list.
    public ListNode<E> getTail() {
        return tail;
    }
    public ListNode<E> getPtrAt(int index) {
        if(index<0){
            return null;
        }
        ListNode<E> current = head;
        while(index>0){
            if(current == null){
                return null;
            }else{
                current = current.next;
            }
            index--;
        }
        return current;
    }

    // Add item to end of list.
    public void addLast(E item) {
        if (head != null) {
            tail.setNext(new ListNode<E>(item));
            tail = tail.getNext();
        } else {
            tail = new ListNode<E>(item);
            head = tail;
        }
        num_nodes++;
    }

    // Remove node after node referenced by current
    public E removeAfter(ListNode<E> current) throws NoSuchElementException {
        E temp;
        if (current != null) {
            ListNode<E> nextPtr = current.getNext();
            if (nextPtr != null) {
                temp = nextPtr.getElement();
                current.setNext(nextPtr.getNext());
                num_nodes--;
                if (nextPtr.getNext() == null) // last node is removed
                    tail = current;
                return temp;
            } else
                throw new NoSuchElementException("No next node to remove");
        } else { // if current is null, we want to remove head
            if (head != null) {
                temp = head.getElement();
                head = head.getNext();
                num_nodes--;
                if (head == null)
                    tail = null;
                return temp;
            } else
                throw new NoSuchElementException("No next node to remove");
        }
    }
    public void setHead(ListNode<E> newhead){
        head = newhead;
    }
    public void setTail(ListNode<E> newtail){
        if(newtail==null){
            return;
        }
        tail = newtail;
        tail.next = null;

    }

    // Remove first node of list.
    public E removeFirst() throws NoSuchElementException {
        return removeAfter(null);
    }

    // Remove item from list
    public E remove(E item) throws NoSuchElementException {
        ListNode<E> current = head;
        if (current == null) {
            throw new NoSuchElementException("No node to remove");
        }
        if (current.getElement().equals(item)) {
            return removeAfter(null);
        }
        while (current.getNext().getElement() != null) {
            if (current.getNext().getElement().equals(item)) {
                return removeAfter(current);
            }
            current = current.getNext();
        }
        throw new NoSuchElementException("No node to remove");
    }
}

class ListNode<E> {
    protected E element;
    protected ListNode<E> next;

    /* constructors */
    public ListNode(E item) {
        this.element = item;
        this.next = null;
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
    private int _age;
    Person(String name, int age){
        _name  = name;
        _age = age;
    }
    public String getName(){
        return _name;
    }
    public int getAge(){
        return _age;
    }
}