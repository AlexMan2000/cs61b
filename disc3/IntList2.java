import java.util.Formatter;

/**
 * Created by AlexMan
 */
public class IntList2 {
    public int first;
    public IntList2 rest;

    public IntList2(int f, IntList2 r){
        this.first = f;
        this.rest = r;
    }

    public static void evenOdd(IntList2 lst){
        if(lst == null || lst.rest == null){
            return;
        }
        IntList2 second = lst.rest;
        int index = 0;
        while (!(index % 2 == 0 && (lst.rest == null || lst.rest.rest == null))) {
            IntList2 temp = lst.rest;
            lst.rest = lst.rest.rest;
            lst = temp;
            index ++ ;
        }
        lst.rest = second;
    }


    @Override
    public int hashCode() {
        return first;
    }


    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList2 L = (IntList2) x;
        IntList2 p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }


    private int detectCycles(IntList2 A) {
        IntList2 tortoise = A;
        IntList2 hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList2 p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }

}
