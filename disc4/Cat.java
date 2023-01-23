/**
 * Created by AlexMan
 */
public class Cat extends Animal {
    // Must write the constructor signature
    // In order to compile properly
    public Cat(String name, int age) {
        super(name, age);
        // Inherited from super class
        this.noise = "Meow!";
    }

    @Override
    public void greet() {
        System.out.println("Cat " + name + " says: " + makeNoise());
    }

    public static void main(String[] args) {
        Cat c1 = new Cat("Nuomi", 23);
        c1.greet();
    }
}