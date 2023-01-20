/**
 * Created by AlexMan
 * An lectures.SLList is a list of integers, which hides the terrible truth
 * of the nakedness within.
 */
public class SLList {
    private IntNode first;

    /**
     * 这里IntNode不需要访问SLList实例化对象中的任何属性和方法，所以推荐使用static
     */
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next){
            this.item = item;
            this.next = next;
        }
    }

    public SLList(){

    }

    public SLList(int x){
        first = new IntNode(x, null);
    }

    /**
     * Append a new node at the front of the lectures.SLList
     * @param i
     */
    public void addFirst(int i){
        IntNode newHead = new IntNode(i, first);
        first = newHead;
    }

    /**
     * Return the string representation of the lectures.SLList
     * @return
     */
    public String printList(){
        IntNode s = first;
        String res = "";
        while(s != null){
            if(s.next !=null){
                res += String.valueOf(s.item) + "->";
            }else{
                res += String.valueOf(s.item);
            }
            s = s.next;
        }
        return res;
    }

//    /**
//     * Insert an item at the given position
//     * @param item
//     * @param position
//     */
//    public void insert(int item, int position){
//        // Empty lectures.SLList
//        if(first == null){
//            addFirst(item);
//        }
//        // At least one item
//        IntNode curr = first;
//        IntNode prev = new IntNode(-1, curr);
//        int pos = 0;
//        while(curr != null){
//            if(pos == position){
//                if(pos == 0){
//                    addFirst(item);
//                }else{
//                    prev.next = new IntNode(item, curr);
//                }
//                return;
//            }
//            curr = curr.next;
//            prev = prev.next;
//            pos ++ ;
//        }
//        // Insert at the end
//        prev.next = new IntNode(item,null);
//    }


    /**
     * Insert an item at the given position
     * @param item
     * @param position
     */
    public void insert(int item, int position){
        // Empty lectures.SLList or insert at the front
        if(first == null || position == 0){
            addFirst(item);
            return;
        }

        IntNode currentNode = first;
        while(position > 1 && currentNode.next != null){
            position --;
            currentNode = currentNode.next;
        }

        IntNode newNode = new IntNode(item, currentNode.next);
        currentNode.next = newNode;
    }

    /**
     * Removes all nodes that contain a certain item, destructive
     * @param x
     */
    public void removeItem(int x){
        removeItemHelper(x, first);
    }


    public IntNode removeItemHelper(int x, IntNode current){
        if(current == null){
            return null;
        }else if(current.item == x){
            return removeItemHelper(x, current.next);
        }else{
            current.next = removeItemHelper(x, current.next);
            return current;
        }
    }


    /**
     * Reverse the list, destructive, should not use new
     */
    public void reverse(){
        if(first == null){
            return;
        }
        IntNode prevNode = first;
        IntNode currNode = first.next;
        first.next = null;
        while(currNode != null){
            IntNode afterNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = afterNode;
        }
        first = prevNode;
    }



    public static void main(String[] args) {
        /* Create a list of one integer, namely 10*/
        SLList L = new SLList(10);
        L.addFirst(20);
        L.addFirst(30);
        L.insert(5, 4);
        L.reverse();
        System.out.println(L.printList());  // 30 -> 20 -> 10
    }
}