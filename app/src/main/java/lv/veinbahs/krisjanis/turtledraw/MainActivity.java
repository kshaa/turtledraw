package lv.veinbahs.krisjanis.turtledraw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    protected GameEngine engine;
    protected GameRenderer renderer;
    protected GameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Draw main activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure engine
        this.engine = GameEngineFactory.createGameEngine(5, 5);

        // Configure renderer
        RelativeLayout layout = findViewById(R.id.turtle_playground);
        this.renderer = new GameRenderer(this, layout, 40, 40);
        this.renderer.render(this.engine.getState());
        layout.getViewTreeObserver().addOnGlobalLayoutListener(this.renderer::onResize);

        // Configure controller
        GameControls controls = new GameControls(
            findViewById(R.id.raise),
            findViewById(R.id.lower),
            findViewById(R.id.plus90Deg),
            findViewById(R.id.minus90Deg),
            findViewById(R.id.move),
            findViewById(R.id.flush),
            findViewById(R.id.fin)
        );
        this.controller = new GameController(this, this.engine, this.renderer, controls);
        this.controller.initialize();
    }
}