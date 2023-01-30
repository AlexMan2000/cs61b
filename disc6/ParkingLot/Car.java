package ParkingLot;

/**
 * Created by AlexMan
 */
public abstract class Car {
    private static Integer size = 0;

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer value) {
        this.size = value;
    }
}
