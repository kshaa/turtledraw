package lv.veinbahs.krisjanis.turtledraw;

import java.util.Arrays;

// Coordinate grid is conceptually to be thought of as follows:
// -1/-1 +0/-1 +1/-1
// -1/+0 +0/-1 +1/+0
// -1/+1 +0/+1 +1/+1
public class Coordinate2D {
    public int x;
    public int y;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate2D(Coordinate2D c) {
        this.x = c.x;
        this.y = c.y;
    }

    public Coordinate2D add(Coordinate2D that) {
        Coordinate2D c = new Coordinate2D(this);
        c.x += that.x;
        c.y += that.y;
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate2D that = (Coordinate2D) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[] { x, y });
    }
}
