package com.example.user.enssingasari.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.user.enssingasari.R;

public class MenuArtefak extends AppCompatActivity {

    private ImageView candi, prasasti, arca, senjata, home;
    public static RelativeLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_artefak);
        loading = (RelativeLayout) findViewById(R.id.progressBar1);

        LoadAll lall = new LoadAll();
        lall.execute();

        setListener();
    }


    private class LoadAll extends AsyncTask<Integer, Void, Void> {

        private Bitmap[] bitmap;

        /*
            Memulai animasi loading fade-in dan fade-out tak berhingga
         */
        private void startLoading(){
            final Animation animInOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_out);
            ImageView loadingGbr = (ImageView) findViewById(R.id.loading_gbr_menu_artefak);
            ImageView loadingTeks = (ImageView) findViewById(R.id.loading_teks_menu_artefak);
            loadingGbr.startAnimation(animInOut);
            loadingTeks.startAnimation(animInOut);
        }

        /*
            Konstruktor memunculkan animasi loading
         */
        public LoadAll(){
            bitmap = new Bitmap[2];
            MenuArtefak.loading.setVisibility(View.VISIBLE);
            startLoading();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loadAllImage();
            MenuArtefak.loading.setVisibility(View.GONE);
            super.onPostExecute(aVoid);
        }

        private void loadImage(int x, int y){
            ImageView temp = (ImageView) findViewById(x);
            if (temp != null) {
                temp.setImageBitmap(bitmap[y]);
            }
        }

        private void loadAllImage(){
            loadImage(R.id.menu_artefak_background_dasar, 0);
            loadImage(R.id.menu_artefak_judul, 1);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bg_artefak);
            bitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.artefak_menu);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void setListener(){
        candi = (ImageView) findViewById(R.id.candi_button);
        prasasti = (ImageView) findViewById(R.id.prasasti_button);
        arca = (ImageView) findViewById(R.id.arca_button);
        senjata = (ImageView) findViewById(R.id.senjata_button);
        home = (ImageView) findViewById(R.id.home_artefak);

        candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), Candi.class);
                startActivity(i);
            }
        });

        prasasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), Prasasti.class);
                startActivity(i);
            }
        });

        arca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuArtefak.this, "Arca Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        senjata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuArtefak.this, "Senjata Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                goBack();
            }
        });
    }

    private void goBack(){
        finish();
    }

    @Override
    public void onBackPressed(){
        goBack();
        super.onBackPressed();
    }
}
