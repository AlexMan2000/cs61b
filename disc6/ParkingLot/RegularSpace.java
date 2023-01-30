package ParkingLot;

/**
 * Created by AlexMan
 */
public class RegularSpace extends Space{
    private Integer size = 3;
    private Integer distance;
    private String type = "RegularSpace";
    private Car place;

    @Override
    public boolean isCompatible(Car c) {
        return c.getSize() <= size;
    }

    @Override
    public String toString() {
        return "(" + this.type + "), size: " + this.size + ", distance: " + this.distance + ")";
    }
}
