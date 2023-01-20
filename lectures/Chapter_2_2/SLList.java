/**
 * Created by AlexMan
 * An lectures.SLList is a list of integers, which hides the terrible truth
 * of the nakedness within.
 */
public class SLList {
    private IntNode sentinel;
    private int size;

    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }


    /**
     *
     */
    public SLList(){
        // Any initialized value will do, just pick -5
        sentinel = new IntNode(-5, null);
        size = 0;
    }


    public SLList(int x){
        // Create arbitrary sentinel node
        sentinel = new IntNode(-5, null);
        // Link the first element
        sentinel.next =  new IntNode(x, null);
        size = 1;
    }


    public SLList(int[] array){
        sentinel = new IntNode(-5, null);
        IntNode p = sentinel;
        for(int x: array){
            p.next = new IntNode(x, null);
            p = p.next;
            size += 1;
        }
    }

    /**
     * Append a new node at the front of the lectures.SLList
     * @param i
     */
    public void addFirst(int i){
        IntNode newHead = new IntNode(i, sentinel.next);
        sentinel.next = newHead;
        size += 1;
    }

    /**
     * Return the first item.
     * @return
     */
    public int getFirst(){
        if(size == 0){
            return -1;
        }
        return sentinel.next.item;
    }


    /**
     * Return the string representation of the lectures.SLList
     * @return
     */
    public String printList(){
        IntNode s = sentinel.next;
        String res = "";
        while(s != null){
            if(s.next !=null){
                res += String.valueOf(s.item) + "->";
            }else{
                res += String.valueOf(s.item);
            }
            s = s.next;
        }
        return res;
    }

    /**
     * Append an item at the end of the lectures.SLList, with sentinel, no modifications!
     * @param i
     */
    public void addLast(int i){
        IntNode p = sentinel;
        while(p.next != null){
            p = p.next;
        }
        p.next = new IntNode(i, null);
        size += 1;
    }


    /**
     * Delete the first element in your lectures.SLList.
     */
    public void deleteFirst(){
        if(sentinel.next == null){
            return;
        }else if(sentinel.next.next == null){
            sentinel.next = null;
        }else{
            sentinel.next = sentinel.next.next;
        }
    }



    public int size() {
        return size;
    }


    public static void main(String[] args) {
        /* Create a list of one integer, namely 10*/
        SLList L = new SLList(10);
        L.addFirst(20);
        L.addFirst(30);
        System.out.println(L.printList());  // 30 -> 20 -> 10
        System.out.println(L.getFirst());  // 30
        System.out.println(L.size());
    }
}
