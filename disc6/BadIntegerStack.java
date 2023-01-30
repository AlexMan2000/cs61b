/**
 * Created by AlexMan
 */
public class BadIntegerStack {

    public class Node {
        public Integer value;
        public Node prev;

        public Node(Integer v, Node n) {
            this.value = v;
            this.prev = n;
        }
    }

    // The pointer to the top of the stack
    public Node top;

    public boolean isEmpty() {
        return top == null;
    }


    public void push(Integer num) {
        top = new Node(num, top);
    }

    public Integer pop() {
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }

    public Integer peek() {
        return top.value;
    }

    public static void main(String[] args) {
        BadIntegerStack temp = new BadIntegerStack();
        try {
            Integer ans = temp.pop();
        } catch (NullPointerException e) {
            System.out.println("Success");
        }


        // Due to the public keyword, we can freely modify the structure
        // of the stack outside of the class(from the client side)
        temp.push(1);
        temp.top.prev = temp.top;
        while (!temp.isEmpty()) {
            temp.pop();
        }
        System.out.println("This statement is unreachable");
    }
}
