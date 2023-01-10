package Chapter_1_2.Static_vs_Non_Static_Methods;

public class Dog {

    public static void makeNoise(){
        System.out.println("bark");
    }

    public static void main(String[] args) {
        Dog.makeNoise();
        makeNoise();
    }
}
