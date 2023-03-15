/**
 * Base of StackList. Acts as a node for a singly linked list that is also a 
 * stack.
 * @author alexc
 */
public class MyNode{

    private char element;
    private MyNode next;    
    
    /**
     * sets/updates the next node
     * 
     * @param next The new node to update the next node as
     */
    public void setNext(MyNode next){
        this.next = next;
    }

    /**
     * gets the next node
     * 
     * @return The next node
     */
    public MyNode getNext(){
        return next;
    }

    /**
     * gets the element of the current node
     * 
     * @return the current node's element
     */
    public char getElement(){        
        return element;
    }

    /**
     * sets/updates the current node's element
     * 
     * @param element The new element to update the current node's element with
     */
    public void setElement(char element){
        this.element = element;
    }

}