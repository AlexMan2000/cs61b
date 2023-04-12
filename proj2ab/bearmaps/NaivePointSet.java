package bearmaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexMan
 */
public class NaivePointSet implements PointSet {

    private List<Point> lp;

    public NaivePointSet(List<Point> points) {
        this.lp = new ArrayList<>();
        for (Point point: points) {
            this.lp.add(point);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        // Since we want to implement the linear time search, any sequence data structure
        // should suffice, and we will just use ArrayList here.
        Point o = new Point(x, y);
        double minimumDistance = Double.MAX_VALUE;
        Point nearestPoint = null;
        for (Point p: lp) {
            double distance = Point.distance(o, p);
            if (distance < minimumDistance) {
                minimumDistance = distance;
                nearestPoint = p;
            }
        }
        return nearestPoint;
    }
}
