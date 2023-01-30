import java.util.Iterator;
import java.util.List;

/**
 * Created by AlexMan
 */
public class FilteredList<T> implements Iterable<T> {
    List<T> FL;
    Predicate<T> pred;

    public FilteredList (List<T> L, Predicate<T> filter) {
        FL = L;
        pred = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new NumberIterator(FL, pred);
    }


    public class NumberIterator implements Iterator<T> {
//        List<T> FL;
//        Predicate<T> pred;
        int index;

        public NumberIterator(List<T> L, Predicate<T> filter) {
//            this.FL = L;
//            this.pred = filter;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            if (index < FL.size() && !pred.test(FL.get(index))) {
                index++;
            }
            return index < FL.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("StopIteration");
            }
            T res = FL.get(index);
            index++;
            return res;
        }
    }
}
