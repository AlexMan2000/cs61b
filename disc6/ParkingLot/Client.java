package ParkingLot;

/**
 * Created by AlexMan
 */
public class Client {
    public static void main(String[] args) {
        ParkingLot p = new ParkingLot();
        System.out.println(p);
        Car c = new CompactCar();
        System.out.println(p.getClosestAvailableLot(c));
    }
}
