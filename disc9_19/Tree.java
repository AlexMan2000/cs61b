import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexMan
 */
public class Tree<T> {
    private Node root;

    private class Node {
        public T item;
        public ArrayList<Node> children;
    }

    public Node getAncestor(int k, Node Target) {
        List<Node> list = new ArrayList<>();
        ancestorHelper(root, Target, list);
        return list.get(list.size() - 1 - k);
    }

    private boolean ancestorHelper(Node t, Node target, List<Node> list) {
        list.add(t);
        if (t == target) {
            return true;
        }
        for (Node node: t.children) {
            // 可以保证如果当前路径含有target，则一路返回true一直到最外层函数
            if (ancestorHelper(node, target, list)) {
                return true;
            }
        }
        // 如果ancestorHelper()全是false, 说明以当前节点t为根节点的树中不包含target
        // 于是我们返回false, 且将t从path list中移除，从下一个根节点开始遍历
        list.remove(t);
        return false;
    }
}
