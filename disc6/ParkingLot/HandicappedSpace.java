package ParkingLot;

/**
 * Created by AlexMan
 */
public class HandicappedSpace extends Space{
    private Integer size = 1;
    private Integer distance;
    private String type = "HandicappedSpace";
    private Car place;

    @Override
    public boolean isCompatible(Car c) {
        return c.getSize() <= size;
    }

    @Override
    public String toString() {
        return "(" + type + "), size: " + size + ", distance: " + distance + ")";
    }

}
