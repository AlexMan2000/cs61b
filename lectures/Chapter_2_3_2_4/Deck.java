import java.util.Arrays;

/**
 * Created by AlexMan
 */
public class Deck {
    public static int[] cards;
    Deck(){
        cards = new int[]{1,3,4,10};
    }

    public static void main(String[] args) {
        Deck dingle= new Deck();
        dingle.cards[3] = 3;  // {1, 3, 4, 3}
        System.out.println(Arrays.toString(dingle.cards));
        Deck pilates = new Deck();
        pilates.cards[1] = 2; // {1, 2, 4, 10}
        System.out.println(Arrays.toString(pilates.cards));

        int[] newArrWhoDis = {2, 3, 4, 1, 3};
        dingle.cards = pilates.cards;  // {1， 2， 4， 10}
        System.out.println(Arrays.toString(dingle.cards));
        pilates.cards = newArrWhoDis; // {2, 3, 4, 1, 3}
        System.out.println(Arrays.toString(pilates.cards));
        newArrWhoDis = null;
        System.out.println(Arrays.toString(pilates.cards)); // {2, 3, 4, 1, 3}
    }
}
