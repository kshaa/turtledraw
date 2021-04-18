package lv.veinbahs.krisjanis.turtledraw;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameEngineSpec {
    @Test
    public void playerDirection() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 5);
        assertEquals(engine.state.player.direction, Direction2D.N);
        engine.playerRotate(Rotation2D.Clockwise);
        assertEquals(engine.state.player.direction, Direction2D.E);
        engine.playerRotate(Rotation2D.Anticlockwise);
        engine.playerRotate(Rotation2D.Anticlockwise);
        assertEquals(engine.state.player.direction, Direction2D.W);
    }

    @Test
    public void player0yBoundExceptions() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 5);
        assertTrue(engine.playerForward());
        assertTrue(engine.playerForward());
        assertFalse(engine.playerForward());
    }

    @Test
    public void player0xBoundExceptions() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 5);
        engine.playerRotate(Rotation2D.Anticlockwise);
        assertTrue(engine.playerForward());
        assertTrue(engine.playerForward());
        assertFalse(engine.playerForward());
    }

    @Test
    public void playerHyBoundExceptions() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 5);
        engine.playerRotate(Rotation2D.Anticlockwise);
        engine.playerRotate(Rotation2D.Anticlockwise);
        assertTrue(engine.playerForward());
        assertTrue(engine.playerForward());
        assertFalse(engine.playerForward());
    }

    @Test
    public void playerWxBoundExceptions() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 5);
        engine.playerRotate(Rotation2D.Clockwise);
        assertTrue(engine.playerForward());
        assertTrue(engine.playerForward());
        assertFalse(engine.playerForward());
    }

    @Test
    public void squareTracing() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 5);
        engine.playerForward();
        assertTrue(engine.isSquareThere(new Coordinate2D(2, 2)));
    }

    @Test
    public void squareFlushing() {
        GameEngine engine = GameEngineFactory.createGameEngine(5, 5);
        engine.playerForward();
        engine.flushSquares();
        assertFalse(engine.isSquareThere(new Coordinate2D(2, 2)));
    }
}