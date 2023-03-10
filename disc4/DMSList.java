/**
 * Created by AlexMan
 */
public class DMSList {
    private IntNode sentinel;
    public DMSList() {
        sentinel = new IntNode(-1000, new LastNode());
    }
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }
    public class LastNode extends IntNode{
        // TODO: Write your code here
        public int item;
        public LastNode() {
            // Since we assume all numbers in DMSList are positive
            // We can replace the float('inf') with 0
            super(0, null);
        }

        @Override
        public int max() {
            // return 0 is ok
            return item;
        }

     }

    public int max() {
        return sentinel.next.max();
    }
}