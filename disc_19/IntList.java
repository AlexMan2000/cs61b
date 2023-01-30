/**
 * Created by AlexMan
 */
public class IntList {
    public int first;
    public IntList rest;

    /**
     * Constructor
     * @param f element
     * @param r IntList
     */
    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    /**
     * Recursive implementation of linked list size
     * @return the size of the list
     */
    public int size(){
        if(this.rest == null){
            return 1;
        }else{
            return 1 + this.rest.size();
        }
    }

    /**
     * Iterative implementation of linked list size
     * @return the size of the list
     */
    public int iterativeSize(){
        IntList p = this;
        int size = 0;
        while (p != null){
            size += 1;
            p = p.rest;
        }
        return size;
    }

    /**
     * Recursive Implementation
     * @param i
     * @return size of linked list
     */
    public int get(int i){
        if(this.rest == null){
            return -1;
        }else if(i == 0){
            return this.first;
        }else{
            return this.rest.get(i-1);
        }
    }

    /**
     * Iterative Implementation
     * @param i
     * @return size of linked list
     */
    public int getIterative(int i){
        IntList p = this;
        while(i > 0){
            if(p == null){
                break;
            }else{
                p = p.rest;
                i --;
            }
        }
        if(p == null){
            return -1;
        }
        return p.first;
    }

    /**
     * Add first
     * @param i
     */
    public void addFirst(int i){
        rest = new IntList(first, rest);
        first = i;
    }

    /**
     * Returns an IntList identical to L, but with all values incremented by x.(Non Mutated)
     * @param L
     * @param x
     * @return An IntList identical to L, but with all values incremented by x.
     */
    public static IntList incrList(IntList L, int x){
        if(L == null){
            return L;
        }else{
            return new IntList(L.first + x, incrList(L.rest, x));
        }
    }


    /**
     * Returns an IntList identical to L, but with all values incremented by x.(Mutated)
     * @param L
     * @param x
     * @return
     */
    public static IntList dincriList(IntList L, int x){
        if(L == null){
            return L;
        }
        L.first -= x;
        dincriList(L.rest, x);
        return L;
    }


    /**
     * Helper Function to print the linked list.
     * @return
     */
    public String strLinkedList(){
        if(this.rest == null){
            return String.valueOf(this.first);
        }else{
            return String.valueOf(this.first) + "->" + this.rest.strLinkedList();
        }
    }
}