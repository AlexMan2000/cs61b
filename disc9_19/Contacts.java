import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexMan
 */
public class Contacts {

    private class Node {
        public int prefixCount; // Used to count Partial
        public Map<Character, Node> children;

        public Node() {
            prefixCount = 0; // Initialize the prefix count tp zero
            children = new HashMap<Character, Node>();
        }
    }

    Node root;

    public Contacts() {root = new Node();}

    public void addName(String name) {
        Node current = root;
        for (int index = 0; index < name.length(); index++) {
            if (!current.children.containsKey(name.charAt(index))) {
                Node n = new Node();
                current.children.put(name.charAt(index), n);
                }
            current = current.children.get(name.charAt(index));
            current.prefixCount++;
            }
    }

    public int countPartial(String partial) {
        // First traversal to the end of the prefix,
        // then return the number stored on it
        Node current = root;
        for(int index = 0; index < partial.length(); index++) {
            if(current.children.containsKey(partial.charAt(index))) {
                current = current.children.get(partial.charAt(index));
            }
            else {
                return 0;
            }
        }
        return current.prefixCount;
    }
}
