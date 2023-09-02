package byog.Core;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position moveTo(int x, int y){
        return new Position(x, y);
    }

    public Position moveHorizon(int w) {
        return new Position(x + w - 1, y);
    }

    public Position moveVertical(int h) {
        return new Position(x, y + h - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Position p = (Position) obj;
        return p.x == this.x && p.y == this.y;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }
}
