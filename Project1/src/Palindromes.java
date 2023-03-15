
import java.io.*;
import java.util.Scanner;



/**
 * This program uses a queue and a stack to compare the letters of a word from
 * a dictionary file to see if it's a palindrome. The QueueList uses a 
 * doubly linked list, while StackArray uses an array, and StackList uses a 
 * singly linked list. We output the palindromes to a file called output.txt
 * 
 * @author alex ceithamer
 */
public class Palindromes {
    private static QueueList queue = new QueueList();
    //private static StackArray stack = new StackArray();
    private static StackList stack = new StackList();
    
    private static PrintWriter out;
    private static Scanner in;
    
    /**
     * Constructor initializes in and out to null.
     */
    public Palindromes() {
        in = null;
        out = null;
        
    }
    
    /**
     * Main driver of the program. Creates a PrintWriter and Scanner object for
     * reading from the dictionary doc and outputting a new document. For each
     * word, we save it to a String, put the chars of the string into both the 
     * stack and queue, then call checkPalindrome. If that method returns true
     * we enter it into the new text document
     * 
     * @param args 
     */
    public static void main(String args[]) {
        try {
            out = new PrintWriter("./output.txt");
            in = new Scanner(new File("./dictionary.txt"));
            while(in.hasNext()) {
                String word = in.next();
                for (int i = 0; i < word.length(); ++i) {
                    queue.enqueue(word.charAt(i));
                    stack.push(word.charAt(i));
                }
                if (checkPalindrome()) {
                    out.println(word);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found error: " + ex);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
    
    /**
     * Checks if the current word in the queue and stack is a palindrome by 
     * dequeueing (the first letter) and popping (the last letter), then comparing
     * those to see if they're the same. If any time they are false, we end the program.
     * 
     * we let the while loop continue until empty even if we find out it's not a 
     * palindrome so we can reset the queue and stack to be empty.
     * 
     * @return Boolean indicating whether the word is a palindrome
     * 
     */
    private static boolean checkPalindrome() {
        boolean isPalindrome = true;
        while (queue.isEmpty() == false &&
                stack.isEmpty() == false) {
            if (queue.dequeue() != stack.pop()) {
                isPalindrome = false;
            }
        }//while
        
        return isPalindrome;

        
        
    }
    
    
}
