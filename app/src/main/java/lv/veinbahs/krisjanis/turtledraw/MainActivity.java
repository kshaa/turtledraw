package lv.veinbahs.krisjanis.turtledraw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected RelativeLayout squareLayout;
    protected RelativeLayout playerLayout;
    protected Player player;
    protected List<Square> squares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initGameInView();
    }

//                    <View
//    android:background="#ff0000"
//    android:layout_width="30dp"
//    android:layout_height="30dp">
//                </View>

//                    <ImageView
//    android:gravity="center"
//    android:id="@+id/turtle_player"
//    android:layout_width="30dp"
//    android:layout_height="30dp"
//    android:layout_marginTop="-30dp"
//    android:layout_marginRight="30dp"
//    app:srcCompat="@drawable/turtle">
//                </ImageView>

//    protected Player createPlayer(Context contextId, int imageId) {
//        ImageView player = new ImageView(contextId);
//        player.setImageResource(imageId);
//    }
//
//    protected Player createSquare(Context contextId, int color) {
//        View player = new ImageView(this);
//        player.setBackgroundColor(color);
//    }
//
//
//    protected void initGameInView() {
//        squareLayout = (RelativeLayout) findViewById(R.id.turtle_square_layer);
//        playerLayout = (RelativeLayout) findViewById(R.id.turtle_player_layer);
//        player = createPlayer(this, R.drawable.turtle);
////        Square square = createSquare(this, Color.parseColor("#ff0000"));
//    }
}