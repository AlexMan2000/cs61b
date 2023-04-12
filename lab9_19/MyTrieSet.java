import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by AlexMan
 */
public class MyTrieSet implements TrieSet61B{
    private static final int R = 256;

    private class Node {
        private boolean isKey;
        private Node[] next;
        private Node(boolean b, int R) {
            this.isKey = b;
            this.next = new Node[R];
        }
    }

    private Node root;
    private int n;  // Number of keys

    public MyTrieSet() {
        root = new Node(false, R);
        n = 0;
    }

    @Override
    public void clear() {
        root = new Node(false, R);
        n = 0;
    }

    public int numOfKeys() {
        return n;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException("argument to contains() is null or with length < 1");
        }
        Set<String> allKeys = collectAllKeysSet();
        return allKeys.contains(key);
    }


    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException("argument to add() is null or with length < 1");
        }

//        Node curr = root;
//        for (int i = 0, n = key.length(); i < n; i++) {
//            char c = key.charAt(i);
//            if (curr.next[c] == null) {
//                curr.next[c] = new Node(false, R);
//            }
//            curr = curr.next[c];
//        }
//        curr.isKey = true;

        root = addHelper(root, key, 0);
    }


    /**
     * Add a key node to the tries rooted at node x
     * @param x: the rooted node
     * @param key: the key to be inserted
     * @param i: the current index of the string, also representing the accumulated length
     *         of the string path.from the root node to the current node.
     * @return
     */
    private Node addHelper(Node x, String key, int i) {
        // When we traverse to the end of the tries(fell off the tree), we add the node
        // Careful, don't set node's isKey to true yet
        // Since we don't know whether we have reach the end of the string
        // Very likely we are in the middle of the string when
        // the tries are still very small and x == null often happens.
        if (x == null) {
            x = new Node(false, R);
        }
        // If we have traverse over the string but still not falls off the trie
        if (i == key.length()) {
            x.isKey = true;
            n++;
            return x;
        }
        // Not the end of the string, keep recursing
        char c = key.charAt(i);
        x.next[c] = addHelper(x.next[c], key, i + 1);
        return x;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        Node curr = root;
        for (int  i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            sb.append(c);
            Node next = curr.next[c];
            // Invalid prefix
            if (next == null) {
                return res;
            }
            curr = next;
        }
        collectAllKeysHelper(sb, curr, res);
        return res;
    }

    /**
     * Returns the string in the symbol table that is the longest prefix of {@code key},
     * or {@code null}, if no such string.
     * @param key the query string
     * @return the string in the symbol table that is the longest prefix of {@code key},
     *     or {@code null} if no such string
     * @throws IllegalArgumentException if {@code query} is {@code null}
     */
    @Override
    public String longestPrefixOf(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException("argument to longestPrefixOf() is null or with length < 1");
        }

        return longestPrefixOfHelper(root, key, 0);
    }

    private String longestPrefixOfHelper(Node p, String key, int index) {
        // We fall off the tree
        if (p == null) {
            return key.substring(0, index - 1);
        }

        // We finish traversing the string
        if (index == key.length()) {
            return key;
        }

        // Get the current character
        char c = key.charAt(index);
        // Follow the character in the tree structure.
        return longestPrefixOfHelper(p.next[c], key, index + 1);
    }


    /**
     * Collect all keys in the tries
     * @return
     */
    public Set<String> collectAllKeysSet() {
        List<String> res0 = new ArrayList<>();
        Set<String> res = new HashSet<>();
        StringBuilder prefix = new StringBuilder();
        collectAllKeysHelper(prefix, root, res0);
        res.addAll(res0);
        return res;
    }

    public List<String> collectAllKeysList() {
        List<String> res = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        collectAllKeysHelper(prefix, root, res);
        return res;
    }

    /**
     * Helper method for collecting, using backtracking.
     * @param prefix: The data structure that records the current string
     * @param x: current node, used to traverse the tries
     * @param res: the data structure that stores the final result, containing all keys
     * @return
     */
    private void collectAllKeysHelper(StringBuilder prefix, Node x, List<String> res) {
        if (x == null) {
            return;
        }
        if (x.isKey) {
            res.add(prefix.toString());
        }
        for (char ch = 0; ch < R; ch++) {
            prefix.append(ch);
            collectAllKeysHelper(prefix, x.next[ch], res);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Delete the key from the tries
     * @param key
     */
    public void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is illegal!");
        }

        if (!contains(key)) {
            return;
        }

        root = deleteHelper(root, key, 0);
    }


    private Node deleteHelper(Node p, String key, int index) {
        if (p == null) {
            return null;
        }

        // 遍历到了key string的最后一位，而key string的最后一个
        // char就是我们的key, 我们需要将其设置为false, 从
        // 蓝色的点变为白色的点
        if (index == key.length()) {
            p.isKey = false; // Optional
            // 别忘了缩减size
            n--;
        } else {
            char ch = key.charAt(index);
            p.next[ch] = deleteHelper(p.next[ch], key, index + 1);
        }

        // 到达这里的两种情况
        // 1. index == key.length, 此时p.isKey在上面被设置成了false
        // 于是这里不执行
        // 2. index < key.length, 表示已经将target key删除完毕了，此时遇到其他key就返回
        if (p.isKey) {
            return p;
        }

        // 这里的逻辑用于判断一个白色的节点是否是根节点，如果是，则表明
        // 是我们要删除的target key, 若不是，则就是tries中间的某个白色节点
        // 不需要删除。
        for (char ch = 0; ch < R; ch++) {
            if (p.next[ch] != null) {
                return p;
            }
        }

        // 通过返回null来删除。
        return null;
    }


}
