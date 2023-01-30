import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by AlexMan
 */
public class IteratorOfIterators implements Iterator<Integer>{
    LinkedList<Integer> ll ;

    public IteratorOfIterators(List<Iterator<Integer>> a) {
        ll = new LinkedList<>();

        // Used for circular index; 0,1,2,0,1,2,0,1,2 for example.
        int i = 0;

        while (!a.isEmpty()) {
            // Get the i-th iterator
            Iterator<Integer> curr = a.get(i);
            if (curr.hasNext()) {
                ll.add(curr.next());
            } else {
                a.remove(curr);
                i--; //Make sure that the index is working just right
            }
            // If a is empty after the removal process above, then break the while loop
            if (a.isEmpty()) {
                break;
            }
            // Circular index
            i = (i + 1) % a.size();
        }
    }

    @Override
    public boolean hasNext() {
        return !ll.isEmpty();
    }

    @Override
    public Integer next(){
        return ll.removeFirst();
    }
}
