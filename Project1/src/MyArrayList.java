/**
 * This class is used by StackArray to create a stack data structure using
 * an array. It will resize and downsize if needed. Because it's a stack, it
 * pushes and pops elements last in first out
 * 
 */
public class MyArrayList {

	private char[] theElements;
	private int numberElements; 
	
        /**
         * Constructor that initializes the array to size of 2, and number
         * of elements to 0.
         */
	public MyArrayList() {
		theElements = new char[2];
		numberElements = 0;
	}
        
        /**
         * adds an element to the top of the stack (back of the array)
         * 
         * @param element element to put into the stack
         */
	public void add(char element) {
		if(numberElements==theElements.length) {
			resize(true);
		}
		theElements[numberElements] = element;
		numberElements++;
	}
        
	
	/**
         * resizes the array depending on the parameter "increase". If true, the
         * method will double the array size. If false, it means the number of
         * elements is less than 1/3 the size of the array, and so the array
         * will downsize by 1/2 it's original size
         * 
         * @param increase whether to increase or decrease the size
         */
	private void resize(boolean increase) {
            char[] tempArray = null;
            if (increase){
                System.out.println("Array is being resized larger!");
                tempArray = new char[2 * theElements.length];
                for(int i = 0; i < theElements.length; i++) {
                        tempArray[i] = theElements[i];
                }
                
            } else {
                if (theElements.length > 2) {
                    System.out.println("Array is being resized smaller!");
                    tempArray = new char[theElements.length / 2];
                    for (int i = 0; i < numberElements; i++) {
                        tempArray[i] = theElements[i];
                    }
                }
            }//if increase = true
            theElements = tempArray;
	} //resize method
        
        /**
         * 'pops' the top of the stack, aka the last element of the array.
         * 
         * @return the removed character
         */
        public char remove() {
            char removedChar;
            if (numberElements <= 0) {
                throw new RuntimeException("Empty Stack!");
            } else {
                
                removedChar = theElements[numberElements - 1];
                --numberElements;
                if (numberElements < Math.floor(theElements.length / 3) ) {
                    resize(false);
                }
            }
            return removedChar;
        }
        
        /**
         * gets the number of elements in the array
         * @return number of elements in the array
         */
        public int getNumElements() {
            return numberElements;
        }
	
	
	
}
