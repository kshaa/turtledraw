package lv.veinbahs.krisjanis.turtledraw;

import java.util.LinkedList;

public class GameEngine {
    protected GameState state;

    public GameEngine(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return this.state;
    }

    public void initialize() {
        this.leaveTurtlePrint();
    }

    protected void leaveTurtlePrint() {
        if (!this.state.player.raised && !this.isSquareThere(this.state.player.position)) {
            this.state.squares.add(new Square(this.state.player.position));
        }
    }

    public boolean playerForward() {
        Coordinate2D destination = this.state.player.position.add(
            Direction2DHelper.directionAsCoordinate(
                this.state.player.direction
            )
        );

        if (destination.x < 0) return false;
        if (destination.y < 0) return false;
        if (destination.x > (this.state.width - 1)) return false;
        if (destination.y > (this.state.height - 1)) return false;

        this.state.player.position = destination;
        this.leaveTurtlePrint();

        return true;
    }

    public void playerRotate(Rotation2D rotation) {
        this.state.player.direction = Direction2DHelper.directionRotate(
            this.state.player.direction,
            rotation
        );
    }

    public void playerRaise() {
        this.state.player.raised = true;
    }

    public void playerLower() {
        this.state.player.raised = false;
        this.leaveTurtlePrint();
    }

    public boolean isSquareThere(Coordinate2D position) {
        for (Square square : this.state.squares) {
            if (square.position.equals(position)) {
                return true;
            }
        }

        return false;
    }

    public void flushSquares() {
        this.state.squares = new LinkedList<Square>();
        this.leaveTurtlePrint();
    }
}

