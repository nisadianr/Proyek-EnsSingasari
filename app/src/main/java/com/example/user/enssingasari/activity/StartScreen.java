package com.example.user.enssingasari.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.user.enssingasari.R;

//this class is for the start menu

public class StartScreen extends Activity implements View.OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        Button start = (Button) findViewById(R.id.start_button);
        start.setOnClickListener(this);

        Resources res = getResources();
        Drawable background = res.getDrawable(R.drawable.testing);

//        View viewBackgroud = (View) findViewById(R.id.backgroud_view);
//        viewBackgroud.setBackground(background);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rellayout);
        rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.title_background));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
