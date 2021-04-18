package lv.veinbahs.krisjanis.turtledraw;

import java.util.LinkedList;

public class GameState {
    public Player player;
    public LinkedList<Square> squares;
    int width;
    int height;

    public GameState(Player player, LinkedList<Square> squares, int width, int height) {
        this.player = player;
        this.squares = squares;
        this.width = width;
        this.height = height;
    }
}
