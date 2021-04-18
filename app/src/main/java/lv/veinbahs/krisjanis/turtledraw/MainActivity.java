package lv.veinbahs.krisjanis.turtledraw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    protected GameEngine engine;
    protected GameRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = findViewById(R.id.turtle_playground);
        initGame(layout, 5, 5, 40, 40);
        renderGame();
        layout.getViewTreeObserver().addOnGlobalLayoutListener(this.renderer::onResize);
    }

    protected void initGame(RelativeLayout layout, int gameWidth, int gameHeight, int pixelWidth, int pixelHeight) {
        this.engine = GameEngineFactory.createGameEngine(gameWidth, gameHeight);
        this.renderer = new GameRenderer(this, layout, pixelWidth, pixelHeight);
    }

    protected void renderGame() {
        this.renderer.render(this.engine.getState());
    }
}