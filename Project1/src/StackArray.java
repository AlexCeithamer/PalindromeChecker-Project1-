

/**
 * Creates an array list of type MyArrayList. Acts as a stack, and so it uses
 * the last in first out methodology. Allows poping, pushing, and checking the
 * size or if it's empty.
 */
public class StackArray {
    private MyArrayList myArray;
    
    /**
     * Constructor initializes myArray, which is of size 2 from inside the 
     * MyArrayList class
     */
    public StackArray() {
        myArray = new MyArrayList();
    }
    
    /**
     * Pushes a new char element onto the stack using myArray's add method.
     * Pushing onto the stack puts the element at the end of the array
     * 
     * @param x element to be pushed onto the stack
     */
    public void push(char x) {
        myArray.add(x);
    }
    
    /**
     * Pops the 'youngest' element on the stack (back of the array). If the 
     * array is empty it throws an exception from the MyArrayList class back
     * here where we catch it and print out a message.
     * 
     * The return 0 at the bottom of the method is there to avoid complier
     * warnings
     * 
     * @return char that was popped
     */
    public char pop() {
        try {
            return myArray.remove();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    /**
     * checks if the array is empty by calling the getNumElements method of
     * myArray. If 0 elements is returned, we return true from this method.
     * 
     * @return True if array is empty. False if array is not empty
     */
    public boolean isEmpty() {
        return myArray.getNumElements() == 0;
    }
    
    
    /**
     * Checks the size of the array, expressed as an integer.
     * @return integer size of the array 
     */
    public int size() {
        return myArray.getNumElements();
    }
}
