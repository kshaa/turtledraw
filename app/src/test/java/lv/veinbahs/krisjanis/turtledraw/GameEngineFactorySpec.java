package lv.veinbahs.krisjanis.turtledraw;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameEngineFactorySpec {
    @Test
    public void oddEnginePlayerPositionDirection() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 7);
        assertEquals(engine.getState().player.position, new Coordinate2D(2, 3));
        assertEquals(engine.getState().player.direction, Direction2D.N);
    }

    @Test
    public void evenEnginePlayerPositionDirection() {
        GameEngine engine = GameEngineFactory.createGameEngine(4, 6);
        assertEquals(engine.getState().player.position, new Coordinate2D(2, 3));
        assertEquals(engine.getState().player.direction, Direction2D.N);
    }

    @Test
    public void engineSizeException() {
        try {
            GameEngineFactory.createGameEngine(-1, -1);
        } catch (Error er) {
            assert(true);
            return;
        }

        assert(false);
    }
}