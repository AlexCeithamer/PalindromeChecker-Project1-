/**
 * Creates a singly linked list using MyNode. Is used by StackList, and so it 
 * follows the last in first out methodology
 */
public class MyLinkedList{

    private MyNode head;
    private MyNode tail;
    

    /**
     * Constructor initializes the head and tail
     * nodes to null
     */
    public MyLinkedList(){
        head = tail = null;
    }
    
    /**
     * Gets the number of elements within the linked list
     * @return number of elements
     */
    public int getNumElements() {
        int numberElements = 0;
        MyNode current = head;
        while(current != null) {
            numberElements++;
            current = current.getNext();
        }
        return numberElements;
    }
    
    /**
     * adds a new element to the tail of the stack
     * 
     * @param element the char element we want to enter into the list
     */
    public void add(char element){
        MyNode temp = new MyNode();
        temp.setElement(element);
        temp.setNext(null);

        if(head==null){
            head = temp;
            tail = temp;
        } else{
            tail.setNext(temp);
            tail = temp;
        }
    }
    
    /**
     * removes the tail which is the 'youngest' element since this is a stack
     * Used by the 'pop' method in StackList
     * 
     * @return character that was removed
     */
    public char removeTail() {
        if (head == null) {
            throw new RuntimeException("Empty Stack!");
        }
        char removedChar = tail.getElement();
        if(head == tail) {
            head = tail = null;
        } else {
            MyNode current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(tail.getNext());
            tail = current;
        }
        return removedChar;
    }


    

}