package ParkingLot;

/**
 * Created by AlexMan
 */
public class CompactSpace extends Space{
    private Integer size = 2;
    private Integer distance;
    private String type = "CompactSpace";
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
