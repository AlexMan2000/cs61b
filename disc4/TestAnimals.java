/**
 * Created by AlexMan
 */
public class TestAnimals {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);

        // Easy ones, just call instance methods
        a.greet();  // Animal Pluto says: Huh?
        c.greet();  // Cat Garfield says: Meow!
        d.greet();  // Dog Fido says: WOOF!

        // Dynamic method selection
        a = c;  // a has static type Animal, so is able to hold Cat
        ((Cat) a).greet(); // Cat Garfield says: Meow!

        a.greet(); // Cat Garfield says: Meow!

        a = new Dog("Spot", 10);
//        d = a;
        // Compilation Error, a has static type Animal, but d has static type Dog
    }
}
