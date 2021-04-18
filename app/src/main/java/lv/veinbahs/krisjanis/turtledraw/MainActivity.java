package lv.veinbahs.krisjanis.turtledraw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

        // Static configurations
        int cellSizeDp = 35; // Game board cell size in dp
        float sideMarginDp = 10; // Game board side margin in dp
        float magicHeightOffsetDp = 300; // This is subtracted from device height to calculate available board height

        // Calculate engine parameters based on device size & static configurations
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpHeight = (displayMetrics.heightPixels / displayMetrics.density) - sideMarginDp * 2 - magicHeightOffsetDp;
        float dpWidth = (displayMetrics.widthPixels / displayMetrics.density) - sideMarginDp * 2;
        int cellsX = (int) (dpWidth / cellSizeDp) - 2; // Two cells required for walls
        int cellsY = (int) (dpHeight / cellSizeDp) - 2; // Two cells required for walls

        // Configure engine
        this.engine = GameEngineFactory.createGameEngine(cellsX, cellsY);

        // Configure renderer
        RelativeLayout layout = findViewById(R.id.turtle_playground);
        this.renderer = new GameRenderer(this, layout, cellSizeDp, cellSizeDp);
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