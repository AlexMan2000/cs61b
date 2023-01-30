import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by AlexMan
 */
public class KthIntList implements Iterator<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    /**
     * Returns true iff there is a next Kth element. Do not modify.
     * @return
     */
    @Override
    public boolean hasNext() {
        return this.hasNext;
    }


    /**
     * Returns the next Kth element of the IntList given in the constructor.
     * Returns the 0th element first. Throws a NoSuchElementException if there
     * are no Integers available to return
     * @return
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer res = this.curList.first;
        int i = 0;
        while (i < k && this.curList != null) {
            this.curList = this.curList.rest;
            i++;
        }
        if (i < k){
            this.hasNext = false;
        }
        return res;
    }

}
