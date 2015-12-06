package com.example.champs.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Filip on 2015-11-15.
 */
public class MatchesActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches);

        ImageView image =  (ImageView) findViewById(R.id.imageRank);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), RankingActivity.class);
                startActivity(nextScreen);
            }
        });
    }
}
