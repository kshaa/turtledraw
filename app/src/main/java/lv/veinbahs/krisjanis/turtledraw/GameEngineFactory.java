package lv.veinbahs.krisjanis.turtledraw;

import java.util.LinkedList;

public class GameEngineFactory {
    public static GameEngine createGameEngine(int width, int height) {
        if (width < 2) throw new Error("Game width must be larger than 2");
        if (height < 2) throw new Error("Game height must be larger than 2");
        Player player = new Player(new Coordinate2D(width / 2, height / 2), Direction2D.N, false);
        LinkedList<Square> squares = new LinkedList<Square>();
        GameState state = new GameState(player, squares, width, height);
        GameEngine engine = new GameEngine(state);
        engine.initialize();

        return engine;
    }
}
