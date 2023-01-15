package DIY;

/**
 * Created by AlexMan
 */
public class AList {
    public static int MAX_SIZE = 50;
    public int[] array;
    public int size;
    public String resizeOption = "one";

    /** Creates an empty list. */
    public AList() {
        array = new int[MAX_SIZE];
        size = 0;
    }

    /** Creates an empty list. */
    public AList(String option) {
        array = new int[MAX_SIZE];
        size = 0;
        this.resizeOption = option;
    }


    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        array[size] = x;
        size += 1;
        if(size == MAX_SIZE){
            resize(resizeOption);
        }
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        if(size > 0){
            return array[size - 1];
        }else{
            return -1;
        }
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        if(i >= 0 && i < size - 1){
            return array[i];
        }else{
            return -1;
        }

    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        int last =  array[size];
        array[size - 1] = 0; // Clear out
        size -= 1;
        return last;
    }

    /**
     * Resize if the size is too big - one larger
     */
    public void resize(String option){
        switch (option) {
            case "one":
                resizeOne();
                break;
            case "double":
                resizeDouble();
                break;
            default:
                break;
        }
    }


    public void resizeOne(){
        MAX_SIZE += 1;
        int[] newArray = new int[MAX_SIZE];
        System.arraycopy(array, 0, newArray, 0, size);
       array = newArray;
    }

    public void resizeDouble(){
        MAX_SIZE *= 2;
        int[] newArray = new int[MAX_SIZE];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

}
