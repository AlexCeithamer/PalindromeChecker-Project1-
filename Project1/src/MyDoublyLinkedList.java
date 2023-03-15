/**
 * Creates a doubly linked list from MyDoublyNode class. Designed to be used by 
 * the "QueueList" class. 
 * 
 */

public class MyDoublyLinkedList{

    private MyDoublyNode head;
    private MyDoublyNode tail;

    /**
     * Constructor initializes the head and tail
     * nodes to null
     */
    public MyDoublyLinkedList(){
        head = tail = null;
    }
    
    /**
     * Gets the number of elements within the doubly linked list by using a 
     * MyDoublyNode to traverse from head to tail, counting each node as it goes.
     * 
     * @return number of elements
     */
    public int getNumElements() {
        int numberElements = 0;
        MyDoublyNode current = head;
        while(current != null) {
            numberElements++;
            current = current.getNext();
        }
        return numberElements;
    }
    
    /**
     * Removes the head of our list, which is the oldest element (allowing our
     * queueList class to use it for dequeue) 
     * We throw an excepiton if the list is empty
     * 
     * @return element that was removed (the head)
     */
    public char removeHead(){
        if (head == null) {
            throw new RuntimeException("Empty Queue!");
        }
        char removedChar = head.getElement();
        if (head == tail) {
            head = tail = null;
        }
        else {
            head = head.getNext();
            head.setPrevious(null);
        }
        return removedChar;
    }

    /**
     * adds a new element to the tail of the queue
     * 
     * @param element the char element we want to enter into the list
     */
    public void add(char element){
        MyDoublyNode temp = new MyDoublyNode();
        temp.setElement(element);
        temp.setNext(null);
        temp.setPrevious(tail);

        if(head==null){
            head = temp;
            tail = temp;
        } else{
            tail.setNext(temp);
            tail = temp;
        }
    }

}