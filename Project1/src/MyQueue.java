public class MyQueue{

    private MyNode head, tail;

    public MyQueue(){
        head = tail = null;
    }

    //theta(k)
    public int[] dequeueMany(int k){
        int[] array = new int[k];
        MyNode originalHead = head;
        for(int i=0; i<k; i++){
            if(head==null){
                head = originalHead;
                throw new RuntimeException("Not enough elements.");
            }
            array[i] = head.getElement();
            head = head.getNext();
        }
        return array;
    }

    public int dequeue(){
        if(head==null){
            throw new RuntimeException("Empty queue!");
        }
        int temp = head.getElement();        
        head = head.getNext();
        if(head==null){
            tail = null; //not necessary, but doing it anyway for clarity
        }
        return temp;
    }

    public void enqueue(char element){
        MyNode temp = new MyNode();
        temp.setElement(element);
        temp.setNext(null);
        if(head==null){
            head = tail = temp;
        } else{
            tail.setNext(temp);
            tail = temp;
        }

    }

}