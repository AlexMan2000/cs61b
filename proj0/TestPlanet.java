/**
 * Created by AlexMan
 */
public class TestPlanet {

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */
    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkCalcDistance(){
        System.out.println("Checking the distance");
        Planet Samh = new Planet(1, 0, 0, 0, 10, "jupiter.gif");
        Planet Aegir = new Planet(1, 0, 0, 0, 10, "jupiter.gif");
        Planet Rocinante = new Planet(1, 0, 0, 0, 10, "jupiter.gif");
        // ...
    }

    public static void main(String[] args) {
        checkCalcDistance();
    }
}
