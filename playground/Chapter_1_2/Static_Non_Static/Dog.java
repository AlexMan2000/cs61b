package Chapter_1_2.Static_Non_Static;

/**
 * Created by AlexMan
 */
public class Dog {
    public int weightInPounds;
    public static String binomen = "Canis familiaris";

    public Dog(int w) {
        weightInPounds = w;
    }

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }
    }


    public static Dog maxDog(Dog d1, Dog d2){
        /*
         Static Method: Compare between the pounds of dogs.
         */
        if(d1.weightInPounds > d2.weightInPounds){
            return d1;
        }
        return d2;
    }

    public Dog maxDog(Dog d2){
        /*
         Non-Static Method: Compare between the pounds of dogs.
         */
        if(this.weightInPounds > d2.weightInPounds){
            return this;
        }
        return d2;
    }
}
