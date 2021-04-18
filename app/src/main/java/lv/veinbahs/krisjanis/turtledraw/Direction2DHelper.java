package lv.veinbahs.krisjanis.turtledraw;

public class Direction2DHelper {
    public static Coordinate2D directionAsCoordinate(Direction2D direction) {
        switch (direction) {
            case N:
                return new Coordinate2D(0, -1);
            case S:
                return new Coordinate2D(0, 1);
            case W:
                return new Coordinate2D(-1, 0);
            case E:
                return new Coordinate2D(1, 0);
            default:
                throw new Error("Unexpected direction");
        }
    }

    public static Direction2D directionRotate(Direction2D direction, Rotation2D rotation) {
        switch (rotation) {
            case Clockwise:
                switch (direction) {
                    case N:
                        return Direction2D.E;
                    case S:
                        return Direction2D.W;
                    case W:
                        return Direction2D.N;
                    case E:
                        return Direction2D.S;
                    default:
                        throw new Error("Unexpected direction");
                }
            case Anticlockwise:
                switch (direction) {
                    case N:
                        return Direction2D.W;
                    case S:
                        return Direction2D.E;
                    case W:
                        return Direction2D.S;
                    case E:
                        return Direction2D.N;
                    default:
                        throw new Error("Unexpected direction");
                }
            default:
                throw new Error("Unexpected rotation");
        }
    }
}
