package lv.veinbahs.krisjanis.turtledraw;

import android.widget.Button;

public class GameControls {
    Button raise;
    Button lower;
    Button plus90Deg;
    Button minus90Deg;
    Button move;
    Button flush;
    Button fin;

    public GameControls(Button raise, Button lower, Button plus90Deg, Button minus90Deg, Button move, Button flush, Button fin) {
        this.raise = raise;
        this.lower = lower;
        this.plus90Deg = plus90Deg;
        this.minus90Deg = minus90Deg;
        this.move = move;
        this.flush = flush;
        this.fin = fin;
    }
}
