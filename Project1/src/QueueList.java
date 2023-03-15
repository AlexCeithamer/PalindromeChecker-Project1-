
/*
A queue  class (first in first out) which uses a doubly linked list as the 
structure for the queue. 
*/

public class QueueList {
   
    private MyDoublyLinkedList myDoublyList = new MyDoublyLinkedList();
    
    /**
     * Calls on the add method of myDoublyList to add a char to the back of the
     * queue
     * 
     * @param x The char we want to add to the queue
     */
    public void enqueue(char x) {
        myDoublyList.add(x);
    }
    
    /**
     * Tries to remove the head by calling removeHead method from myDoublyList.
     * If the list is empty, we catch the exception and return a whitespace, 
     * otherwise we return the char that was removed.
     * 
     * @return the char that was removed (head of the list)
     */
    public char dequeue() {
        try {
            return myDoublyList.removeHead();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ' ';
        }
        
    }
    
    
    /**
     * Calls on the getNumElements method on myDoublyList which returns 
     * the number of elements. If it's 0, then we return true.
     * 
     * @return boolean representing whether the list is empty or not.
     */
    public boolean isEmpty() {
        return (myDoublyList.getNumElements() == 0);
    }

    /**
     * Calls the getNumElements method on myDoublyList which returns the number
     * of elements in the list. 
     * @return size of the list
     */
    public int size() {
        return myDoublyList.getNumElements();
    }
}
