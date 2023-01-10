package Chapter_1_2.Static_Non_Static;

import Chapter_1_2.Static_Non_Static.Dog;

/**
 * Created by AlexMan
 */
public class DogLauncher {
    public static void main(String[] args) {
        Dog d1;
        Dog d2;
        d1 = new Dog(51);
        d2 = new Dog(52);
        System.out.println(d1.maxDog(d2));
        System.out.println(Dog.maxDog(d1,d2));
        System.out.println(d1.binomen);
        System.out.println(Dog.binomen);
    }
}


