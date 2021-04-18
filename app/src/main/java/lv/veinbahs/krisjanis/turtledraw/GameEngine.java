package lv.veinbahs.krisjanis.turtledraw;

public class GameEngine {
    protected GameState state;

    public GameEngine(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return this.state;
    }

    public void playerForward() {
        this.state.player.position.add(
            Direction2DHelper.directionAsCoordinate(
                this.state.player.direction
            )
        );
    }

    public void playerRotate(Rotation2D rotation) {
        this.state.player.direction = Direction2DHelper.directionRotate(
            this.state.player.direction,
            rotation
        );
    }

    public boolean isCoordinateSquare(Coordinate2D position) {
        for (Square square : this.state.squares) {
            if (square.position.equals(position)) {
                return true;
            }
        }

        return false;
    }
}

