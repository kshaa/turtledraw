package lv.veinbahs.krisjanis.turtledraw;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

// GameRenderer is opinionated and capable of rendering GameState in a RelativeLayout.
// All grid entitities will be drawn at the specified size.
// The grid will be centered in the layout.
// Used colors and images are not customizable.
public class GameRenderer {
    protected Context context;
    protected RelativeLayout layout;
    protected int pixelWidth;
    protected int pixelHeight;

    protected int turtleImageResource = R.drawable.turtle;
    protected int squareColor = Color.parseColor("#9effb8");
    protected int wallColor = Color.parseColor("#3b3b3b");
    protected Logger logger;
    protected int lastWidth;
    protected int lastHeight;
    protected GameState lastState;

    public GameRenderer(Context context, RelativeLayout layout, int pixelWidthDip, int pixelHeightDip) {
        int density = (int) context.getResources().getDisplayMetrics().density;
        this.context = context;
        this.layout = layout;
        this.pixelWidth = density * pixelWidthDip;
        this.pixelHeight = density * pixelHeightDip;
        this.logger = Logger.getLogger(GameRenderer.class.getName());
    }

    protected LinearLayout.LayoutParams createLinearLayoutParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.width = this.pixelWidth;
        params.height = this.pixelHeight;
        params.gravity = Gravity.CENTER;

        return params;
    }

    protected ImageView createPlayer() {
        ImageView player = new ImageView(context);
        player.setImageResource(turtleImageResource);
        player.setLayoutParams(this.createLinearLayoutParams());

        return player;
    }

    protected View createSquare(int color) {
        View square = new View(context);
        square.setBackgroundColor(color);
        square.setLayoutParams(this.createLinearLayoutParams());

        return square;
    }

    protected void setPosition(int gameWidth, int gameHeight, View entity, Coordinate2D position) {
        // We're unsafely casting to LinearLayout.LayoutParams, because we know that type is used,
        // because we created it and later used it ourselves from this.createLinearLayoutParams
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) entity.getLayoutParams();
        int centerX = layout.getMeasuredWidth() / 2;
        int centerY = layout.getMeasuredHeight() / 2;
        int centerOffsetX = -((gameWidth * pixelWidth) / 2);
        int centerOffsetY = -((gameHeight * pixelHeight) / 2);
        int entityOffsetX = position.x * pixelWidth;
        int entityOffsetY = position.y * pixelHeight;

        logger.log(Level.FINE, String.format("[position x] cx=%s, cox=%s, eox=%s", centerX, centerOffsetX, entityOffsetX));
        logger.log(Level.FINE, String.format("[position y] cy=%s, coy=%s, eoy=%s", centerY, centerOffsetY, entityOffsetY));
        params.setMargins(
                centerX + centerOffsetX + entityOffsetX,
                centerY + centerOffsetY + entityOffsetY,
                0,
                0
        );
    }

    public void render() {
        if (this.lastState == null) this.logger.log(Level.WARNING, "[re-render] Trying to re-render a non-existent game state");
        this.render(this.lastState);
    }

    public void render(GameState state) {
        // Remove old renders
        layout.removeAllViews();

        // Render squares
        int squareCounter = 0;
        for (Square squareData : state.squares) {
            logger.log(Level.FINE, String.format("[render entity] square no. %s", squareCounter));
            View squareView = this.createSquare(squareColor);
            this.setPosition(state.width, state.height, squareView, squareData.position);
            layout.addView(squareView);
            squareCounter++;
        }

        // Render player
        logger.log(Level.FINE, "[render entity] player");
        ImageView player = this.createPlayer();
        player.setRotation(Direction2DHelper.directionAsAngle(state.player.direction));
        this.setPosition(state.width, state.height, player, state.player.position);
        layout.addView(player);

        // Render border walls
        LinkedList<Coordinate2D> walls = new LinkedList<Coordinate2D>();
        for (int i = -1; i < state.width + 1; i++) walls.add(new Coordinate2D(i, -1));
        for (int i = -1; i < state.width + 1; i++) walls.add(new Coordinate2D(i, state.height));
        for (int i = 0; i < state.height; i++) walls.add(new Coordinate2D(-1, i));
        for (int i = 0; i < state.height; i++) walls.add(new Coordinate2D(state.width, i));
        int wallCounter = 0;
        for (Coordinate2D wallData : walls) {
            logger.log(Level.FINE, String.format("[render entity] wall no. %s", wallCounter));
            View wallView = this.createSquare(wallColor);
            this.setPosition(state.width, state.height, wallView, wallData);
            layout.addView(wallView);
            wallCounter++;
        }

        // Remember this render for resize event purposes
        this.lastWidth = layout.getMeasuredWidth();
        this.lastHeight = layout.getMeasuredHeight();
        this.lastState = state;
    }

    public void onResize() {
        int layoutWidth = layout.getMeasuredWidth();
        int layoutHeight = layout.getMeasuredHeight();

        // Ignore resize events before anything has been actually rendered
        if (this.lastState == null) return;

        // Re-render scene if layout size changed
        if (this.lastWidth != layoutWidth || this.lastHeight != layoutHeight) {
            logger.log(Level.INFO, String.format("[resize] width=%s, height=%s", layoutWidth, layoutHeight));
            this.render(this.lastState);
        }
    }
}
