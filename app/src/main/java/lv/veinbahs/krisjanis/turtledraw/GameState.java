package lv.veinbahs.krisjanis.turtledraw;

import java.util.List;

public class GameState {
    public Player player;
    public List<Square> squares;
    int width;
    int height;

    public GameState(Player player, List<Square> squares, int width, int height) {
        this.player = player;
        this.squares = squares;
        this.width = width;
        this.height = height;
    }
}
