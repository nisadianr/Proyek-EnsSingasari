package com.example.lifeuniverse.storysingosari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DriverTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_driver_test);

        setListener();
    }

    private void setListener(){
        Button keTimeline = (Button) findViewById(R.id.ke_timeline);
        Button keMenuArtefak = (Button) findViewById(R.id.ke_menu_artefak);

        keTimeline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pindahKeTimeline();
            }
        });

        keMenuArtefak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pindahKeMenuArtefak();
            }
        });
    }

    private void pindahKeTimeline(){
        Intent i = new Intent(getApplicationContext(), Timeline.class);
        startActivity(i);
    }

    private void pindahKeMenuArtefak(){
        Intent i = new Intent(getApplicationContext(), MenuArtefak.class);
        startActivity(i);
    }
}
