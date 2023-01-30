package ParkingLot;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by AlexMan
 */
public class ParkingLot {

    private LinkedList<Space> parkedLots;
    private PriorityQueue<Space> freeLots;

    public ParkingLot() {
        Comparator<Space> compare = Space.getDistanceComparator();
        freeLots = new PriorityQueue<>(compare);
        parkedLots = new LinkedList<>();
        int N = 100;
        for (int i = 0; i < N; i++) {
            double rand = StdRandom.uniform();
            int dist = StdRandom.uniform(1, 100);
            if (rand < 0.333) {
                Space s = new RegularSpace();
                s.setDistance(dist);
                freeLots.add(s);
            } else if (rand < 0.6667) {
                Space s = new CompactSpace();
                s.setDistance(dist);
                System.out.println(s.getDistance());
                System.out.println(s);
                freeLots.add(s);
            } else {
                Space s = new HandicappedSpace();
                s.setDistance(dist);
                freeLots.add(s);
            }
        }
    }

    public boolean isEmpty() {
        return freeLots.isEmpty();
    }

    public Integer freeSize() {
        return freeLots.size();
    }

    public void parkCar(Car c) {
        Space s = getClosestAvailableLot(c);
        if (s != null){
            s.parkCar(c);
        }
    }

    public void removeCar(Space s) {
        if (s != null) {
            s.removeCar();
        }
    }

    public Space getClosestAvailableLot(Car c) {
        if (!freeLots.isEmpty()) {
            Space sa = freeLots.peek();
            if (sa.isCompatible(c)) {
                sa = freeLots.poll();
                sa.parkCar(c);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FreeLots: [");
        for (Space s: freeLots) {
            sb.append(s);
        }
        sb.append("]. ParkedLots: [");
        for (Space s: parkedLots) {
            sb.append(s);
        }
        sb.append("]");
        return sb.toString();
    }
}
