import java.util.Iterator;

/**
 * Created by AlexMan
 */
public class OfficeHoursQueue implements Iterable<OHRequest> {

    OHRequest start;

    public OfficeHoursQueue (OHRequest queue) {
        start = queue;
    }

    public static void main(String[] args) {
        OHRequest s1 = new OHRequest("Failing my test for get in arrayDeque, NPE", "Pam", null);
        OHRequest s2 = new OHRequest("conceptual: what is dynamic method selection", "Michael", s1);
        OHRequest s3 = new OHRequest("git: what does checkout do.", "Jim", s2);
        OHRequest s4 = new OHRequest("help", "Dwight", s3);
        OHRequest s5 = new OHRequest("debugging get(i)", "Creed", s4);
        for (OHRequest or: new OfficeHoursQueue(s5)) {
            System.out.println(or.description);
        }
    }

    @Override
    public Iterator<OHRequest> iterator() {
        return new OHIterator(start);
    }
}
