import java.util.Iterator;

/**
 * Created by AlexMan
 */
public class OHIterator implements Iterator<OHRequest> {
    OHRequest curr;

    public OHIterator(OHRequest queue) {
        curr = queue;
    }

    public boolean isGood(String description) {
        return description != null && description.length() > 5;
    }

    @Override
    public boolean hasNext() {
        while (curr != null && !isGood(curr.description)) {
            curr = curr.next;
        }
        return curr != null;
    }

    @Override
    public OHRequest next() {
        if (hasNext()) {
            OHRequest or = curr;
            curr = curr.next;
            return or;
        } else {
            throw new RuntimeException("StopIteration");
        }
    }
}
