package uk.ac.kent.babylink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 42350 on 31/07/2017.
 */

public class MenuActivity extends AppCompatActivity {

    static final String TAG = "MenuActivity";

    Button pairButton;
    Button videoButton;
    Button messageButton;
    Button logoffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        pairButton = (Button) findViewById(R.id.pair_button);
        videoButton = (Button) findViewById(R.id.video_button);
        logoffButton = (Button) findViewById(R.id.logoff_button);

        View.OnClickListener pairListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GeneratorActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener videoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener logoffListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            }
        };

        pairButton.setOnClickListener(pairListener);
        videoButton.setOnClickListener(videoListener);
        logoffButton.setOnClickListener(logoffListener);
    }
}
