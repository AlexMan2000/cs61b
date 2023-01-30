package ParkingLot;

import java.util.Comparator;

/**
 * Created by AlexMan
 */
public abstract class Space implements Comparable<Space>{
    private Integer size = 0;
    private Integer distance = 0;
    private String type = "";
    private Car place;

    public Integer getSize(){
        return this.size;
    }

    public void setSize(Integer value) {
        this.size = value;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer value) {
        this.distance = value;
    }

    public abstract boolean isCompatible(Car c);

    public void parkCar(Car c){
        if (isCompatible(c)) {
            this.place = c;
        } else {
            System.out.println("Cannot park, not enough space.");
        }
    }

    public void removeCar() {
        this.place = null;
    }

    @Override
    public int compareTo(Space o) {
        return this.distance - o.distance;
    }


    public static class SizeComparator implements Comparator<Space> {

        @Override
        public int compare(Space o1, Space o2) {
            return o1.size.compareTo(o2.size);
        }
    }

    public static class DistanceComparator implements Comparator<Space> {

        @Override
        public int compare(Space o1, Space o2) {
            return o1.distance.compareTo(o2.distance);
        }
    }

    public static Comparator<Space> getSizeComparator() {
        return new SizeComparator();
    }

    public static Comparator<Space> getDistanceComparator() {
        return new DistanceComparator();
    }

    @Override
    public String toString() {
        return "(" + this.type + "), size: " + this.size + ", distance: " + this.distance + ")";
    }
}
