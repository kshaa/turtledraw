package lv.veinbahs.krisjanis.turtledraw;

import android.app.Activity;

public class GameController {
    protected Activity activity;
    protected GameEngine engine;
    protected GameRenderer renderer;
    protected GameControls controls;

    public GameController(Activity activity, GameEngine engine, GameRenderer renderer, GameControls controls) {
        this.activity = activity;
        this.engine = engine;
        this.renderer = renderer;
        this.controls = controls;
    }

    public void initialize() {
        this.controls.raise.setOnClickListener((view) -> { this.engine.playerRaise(); this.renderer.render(); });
        this.controls.lower.setOnClickListener((view) -> { this.engine.playerLower(); this.renderer.render(); });
        this.controls.plus90Deg.setOnClickListener((view) -> { this.engine.playerRotate(Rotation2D.Clockwise); this.renderer.render(); });
        this.controls.minus90Deg.setOnClickListener((view) -> { this.engine.playerRotate(Rotation2D.Anticlockwise); this.renderer.render(); });
        this.controls.move.setOnClickListener((view) -> { this.engine.playerForward(); this.renderer.render(); });
        this.controls.flush.setOnClickListener((view) -> { this.engine.flushSquares(); this.renderer.render(); });
        this.controls.fin.setOnClickListener((view) -> { this.activity.finishAffinity(); System.exit(0); });
    }
}
