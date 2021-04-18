package lv.veinbahs.krisjanis.turtledraw;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    protected int squareColor = Color.parseColor("#8B008B");
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

    protected View createSquare() {
        View square = new View(context);
        square.setBackgroundColor(squareColor);
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

        logger.log(Level.INFO, String.format("[position x] cx=%s, cox=%s, eox=%s", centerX, centerOffsetX, entityOffsetX));
        logger.log(Level.INFO, String.format("[position y] cy=%s, coy=%s, eoy=%s", centerY, centerOffsetY, entityOffsetY));
        params.setMargins(
                centerX + centerOffsetX + entityOffsetX,
                centerY + centerOffsetY + entityOffsetY,
                0,
                0
        );
    }

    public void render(GameState state) {
        // Remove old renders
        layout.removeAllViews();

        // Render player
        logger.log(Level.INFO, "[render entity] player");
        ImageView player = this.createPlayer();
        player.setRotation(Direction2DHelper.directionAsAngle(state.player.direction));
        this.setPosition(state.width, state.height, player, state.player.position);
        layout.addView(player);

        // Render squares
        int i = 0;
        for (Square squareData : state.squares) {
            logger.log(Level.INFO, String.format("[render entity] square no. %s", i));
            View squareView = this.createSquare();
            this.setPosition(state.width, state.height, squareView, squareData.position);
            layout.addView(squareView);
            i++;
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
