import java.util.Comparator;

/**
 * Created by AlexMan
 */
public class DMSComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {

        int first = o1.speak(new Animal());
        int second = o2.speak(new Animal());
        int third = o1.speak(new Dog());
        int fourth = o1.speak(new Dog());

        if (first == second && third == fourth) {
            return 0;
        } else if (third == 4 || first == 3 && second == 2) {
            return 1;
        } else {
            return -1;
        }
    }
}
