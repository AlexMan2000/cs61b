/**
* Created by AlexMan
*/
public class Cat {

    public String name;
    public static String noise;

    public Cat(String name, String noise) {
        this.name = name;
        this.noise = noise;
    }

    public void play() {
        System.out.println(noise + " I'm " + name + " the cat!");
    }

    public static void anger() {
        noise = noise.toUpperCase();
    }

    public static void calm() {
        noise = noise.toLowerCase();
    }

    public static void main(String[] args) {
        Cat a = new Cat("Cream", "Meow!");
        System.out.println(Cat.noise);  // Meow!
        Cat b = new Cat("Tubbs", "Nyan!");
        System.out.println(Cat.noise);  // Nyan!
        a.play(); // Nyan! I'm Cream the cat!
        b.play(); // Nyan! I'm Tubbs the cat!
        Cat.anger();  // Set noise to NYAN!
        System.out.println(Cat.noise); // NYAN!
        a.calm();  // Set noise to nyan!
        System.out.println(Cat.noise); // nyan!
        a.play();  // nyan! I'm Cream the cat!
        b.play();  // nyan! I'm Tubbs the cat!
     }
}
