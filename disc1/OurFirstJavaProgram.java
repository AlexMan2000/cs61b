/**
 * Created by AlexMan
 */
public class OurFirstJavaProgram {
    public static void main(String[] args) {
        int size = 27;
        String name = "Fido";
        Dog myDog = new Dog(name, size);
        int x = size - 5;
        if (x < 15) {
            myDog.bark(8);
        }

        while (x > 3) {
            x -= 1;
            myDog.play();
        }

        int[] numList = {2, 4, 6, 8};
        System.out.print("Hello ");
        System.out.println("Dog: " + name);

        System.out.println(numList[1]);
        if (numList[3] == 8) {
            System.out.println("potato");
        }
    }

}
