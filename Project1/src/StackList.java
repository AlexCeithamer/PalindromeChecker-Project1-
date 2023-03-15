
/**
 * Creates a stack list using a singly linked list. Follows the last in first out
 * methodology. Can push, pop, and check size or if it's empty
 */
public class StackList {
    private MyLinkedList myList;
    
    /**
     * Constructor initializes myList which creates a head and tail pointing at
     * null
     */
    public StackList() {
        myList = new MyLinkedList();
    }
    
    /**
     * Pushes a character onto the stack by calling the add method on myList
     * 
     * @param x character to add to the stack
     */
    public void push(char x) {
        myList.add(x);
    }
    
    /**
     * Tries to pop the element last put on the stack. If it's empty we catch the
     * exception and print a message, otherwise we return the character popped.
     * 
     * @return character that was popped
     */
    public char pop() {
        try {
            return myList.removeTail();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    /**
     * Check if the stack list is empty by calling getNumElements() on myList
     * If it returns 0, we return true from this method.
     * @return True if number of elements = 0, False if number of elements > 0
     */
    public boolean isEmpty() {
        return myList.getNumElements() <= 0;
    }
    
    /**
     * Gets the number of elements by calling getNumElements on myList
     * 
     * @return number of elements
     */
    public int size() {
        return myList.getNumElements();
    }
    
}
