/**
 * Created by AlexMan
 */
public class IntListMutation {


    /**
     * Recursive implementation of non-mutative square operation
     * @param L
     * @return
     */
    public static IntList square(IntList L){
        if(L == null){
            return L;
        }else if(L.rest == null){
            return new IntList(L.first * L.first, null);
        }else{
            return new IntList(L.first * L.first,square(L.rest));
        }
    }


    /**
     * Extra: Iterative implementation of non-mutative square operation
     * @param L
     * @return
     */
    public static IntList squareIterative(IntList L){
        if(L == null){
            return L;
        }
        IntList p = L;
        IntList prev = new IntList(-1, null);
        IntList res = prev;
        while(p != null){
            prev.rest = new IntList(p.first * p.first, null);
            prev = prev.rest;
            p = p.rest;
        }
        return res.rest;
    }



    /**
     * Iterative implementation of mutative square operation
     * @param L
     * @return
     */
    public static IntList squareMutative(IntList L){
        IntList p = L;
        while(p != null){
            p.first = p.first * p.first;
            p = p.rest;
        }
        return L;
    }



    /**
     * Extra: Recursive implementation of mutative square operation
     * @param L
     * @return
     */
    public static IntList squareMutativeRecursive(IntList L){
        if(L == null){
            return L;
        }else{
            L.first *= L.first;
            return squareMutativeRecursive(L.rest);
        }
    }


    public static void main(String[] args) {
        IntList L4 = new IntList(20, null);
        IntList L3 = new IntList(15, L4);
        IntList L2 = new IntList(10, L3);
        IntList L1 = new IntList(5, L2);
        System.out.println(L1.strLinkedList());


        // Test the normal ones
        // 1. square
        IntList newL1 = square(L1);
        System.out.println(newL1.strLinkedList());


        // 2. squareMutative
        IntList newMutativeL1 = L1;
        squareMutative(newMutativeL1);
        System.out.println(newMutativeL1.strLinkedList());


        // Test the extra
        IntList LM4 = new IntList(20, null);
        IntList LM3 = new IntList(15, LM4);
        IntList LM2 = new IntList(10, LM3);
        IntList LM1 = new IntList(5, LM2);
        System.out.println(LM1.strLinkedList());
        // 1. squareIterative
        IntList newL2 = squareIterative(LM1);
        System.out.println(newL2.strLinkedList());


        // 2. squareMutativeRecursive
        IntList newMutativeL2 = LM1;
        squareMutativeRecursive(newMutativeL2);
        System.out.println(newMutativeL2.strLinkedList());

    }


}
