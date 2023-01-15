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
     * Helper Function: Construct the array
     * @param array
     */
    public IntList(int[] array){
        IntList curr = this;
        for(int i = 0; i < array.length - 1; i++){
            curr.first = array[i];
            curr.rest = new IntList(-1, null);
            curr = curr.rest;
        }
        curr.first = array[array.length - 1];
        curr.rest = null;
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
        this.squareList();
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


    /**
     * If 2 numbers in a row are the same, we add them together and make one large node.
     * Destructive
     */
    public void addAdjacent(){
        if(size() == 0 || size() == 1 ){
            return;
        }else{
            IntList prev = this;
            IntList curr = this.rest;
            while(curr != null){
                if(prev.first == curr.first){
                    IntList next = curr.rest;
                    prev.first += curr.first;
                    prev.rest = next;
                    curr = next;
                }else{
                    prev = prev.rest;
                    curr = curr.rest;
                }
            }
        }
    }

    /**
     * Modify the Intlist class so that every time you add a value
     * you “square” the IntList. For example, upon the insertion of 5,
     * the below IntList would transform from:
     *
     * 1 => 2 to 1 => 1 => 2 => 4 => 5
     * and if 7 was added to the latter IntList, it would become
     * 1 => 1 => 1 => 1 => 2 => 4 => 4 => 16 => 5 => 25 => 7
     *
     * Additionally, you are provided the constraint that you can only access
     * the size() function one time during the entire process of adding a node.
     */
    public void squareList(){
        int size = size();
        if(size == 0){
            return;
        }else{
            IntList p = this;
            while(p.rest != null ){
                IntList next = p.rest;
                IntList t = new IntList(p.first * p.first, p.rest);
                p.rest = t;
                p = next;
            }
            p.rest = new IntList(p.first * p.first, null);
        }
    }
}
