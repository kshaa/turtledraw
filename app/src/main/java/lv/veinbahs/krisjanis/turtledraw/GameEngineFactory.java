package lv.veinbahs.krisjanis.turtledraw;

import java.util.Collections;
import java.util.List;

public class GameEngineFactory {
    public static GameEngine createGameEngine(int width, int height) {
        if (width < 2) throw new Error("Game width must be larger than 2");
        if (height < 2) throw new Error("Game height must be larger than 2");
        Player player = new Player(new Coordinate2D(0, 0), Direction2D.N);
        List<Square> squares = Collections.emptyList();
        GameState state = new GameState(player, squares, width, height);
        return new GameEngine(state);
    }
}
