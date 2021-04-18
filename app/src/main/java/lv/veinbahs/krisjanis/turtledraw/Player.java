package lv.veinbahs.krisjanis.turtledraw;

public class Player {
    public Coordinate2D position;
    public Direction2D direction;
    public boolean raised;

    public Player(Coordinate2D position, Direction2D direction, boolean raised) {
        this.position = position;
        this.direction = direction;
        this.raised = raised;
    }
}
