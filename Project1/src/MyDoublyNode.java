/**
 * Base of QueueList. Acts as a node for a doubly linked list which is also
 * a queue. 
 * @author alexc
 */
public class MyDoublyNode{

    private char element;
    private MyDoublyNode next;
    private MyDoublyNode previous;

    /**
     * gets the previous node
     * @return the previous node
     */
    public MyDoublyNode getPrevious(){
        return previous;
    }

    /**
     * sets/updates the previous node
     * 
     * @param previous The node to update the previous node with
     */
    public void setPrevious(MyDoublyNode previous){
        this.previous = previous;
    }
    
    /**
     * sets/updates the next node
     * 
     * @param next The node to update the next node with
     */
    public void setNext(MyDoublyNode next){
        this.next = next;
    }
    
    /**
     * gets the next node
     * 
     * @return The next node
     */
    public MyDoublyNode getNext(){
        return next;
    }

    /**
     * gets the element of the current node
     * 
     * @return element of the current node
     */
    public char getElement(){        
        return element;
    }

    /**
     * set/update the current node's element
     * 
     * @param element new element to assign to the current node
     */
    public void setElement(char element){
        this.element = element;
    }

}