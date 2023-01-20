import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


/**
 * Created by AlexMan
 */
public class ArrayDequeTest {

    @Test
    public void testAddFirst(){
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        for(int i=0; i< 100; i++){
            aDeque.addFirst(i);
        }
        System.out.println(aDeque);
    }


    @Test
    public void testAddLast(){
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        for(int i=0; i< 100; i++){
            aDeque.addLast(i);
        }
        System.out.println(aDeque);
    }


    @Test
    public void testRemoveFirst(){
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        for(int i=0; i< 100; i++){
            aDeque.addLast(i);
        }
        System.out.println(aDeque);
        for(int i=0; i < 99; i++){
            aDeque.removeFirst();
        }
        System.out.println(aDeque.get(0));
        System.out.println(aDeque.size());
        System.out.println(aDeque.isEmpty());
    }

    @Test
    public void testRemoveLast(){
        System.out.println(Math.round(3.2));
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        for(int i=0; i< 100; i++){
            aDeque.addLast(i);
        }
        System.out.println(aDeque);
        for(int i=0; i < 99; i++){
            aDeque.removeLast();
        }
        System.out.println(aDeque.get(0));
        System.out.println(aDeque.size());
        System.out.println(aDeque.isEmpty());
    }

    @Test
    public void randomizedTest(){
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        int N = 5000;
        int operationNumber = StdRandom.uniform(0,4);
        for(int i = 0; i < N; i++){
            int randomNumber = StdRandom.uniform(1,11);
            if(operationNumber == 0){
                aDeque.addFirst(randomNumber);
            } else if (operationNumber == 1){
                aDeque.removeFirst();
            } else if (operationNumber == 2){
                aDeque.addLast(randomNumber);
            } else {
                aDeque.removeLast();
            }
        }
        System.out.println(aDeque);
    }

}
