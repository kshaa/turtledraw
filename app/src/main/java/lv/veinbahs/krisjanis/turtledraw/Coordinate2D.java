package lv.veinbahs.krisjanis.turtledraw;

import java.util.Arrays;
import java.util.Objects;

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

    public void add(Coordinate2D c) {
        this.x += c.x;
        this.y += c.y;
    }

    public void sub(Coordinate2D c) {
        this.x -= c.x;
        this.y -= c.y;
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
