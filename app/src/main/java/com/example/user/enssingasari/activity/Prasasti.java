package com.example.lifeuniverse.storysingosari;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class Prasasti extends AppCompatActivity {

    private ImageView[] prasasti;
    private ImageView backToMenuArtefak, readMore, petaLokasi;
    public static RelativeLayout loading;
    public static Bitmap[] petaPrasasti_cache, activeButton_cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_prasasti);
        prasasti = new ImageView[4];
        loading = (RelativeLayout) findViewById(R.id.progressBar1);
        petaPrasasti_cache = new Bitmap[4];
        activeButton_cache = new Bitmap[4];
        LoadAll lall = new LoadAll();
        lall.execute();
        initSelektor();
        initListener();
    }

    private class LoadAll extends AsyncTask<Integer, Void, Void> {

        private Bitmap[] bitmap;

        /*
            Memulai animasi loading fade-in dan fade-out tak berhingga
         */
        private void startLoading(){
            final Animation animInOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_out);
            ImageView loadingGbr = (ImageView) findViewById(R.id.loading_gbr_prasasti);
            ImageView loadingTeks = (ImageView) findViewById(R.id.loading_teks_prasasti);
            loadingGbr.startAnimation(animInOut);
            loadingTeks.startAnimation(animInOut);
        }

        /*
            Konstruktor memunculkan animasi loading
         */
        public LoadAll(){
            bitmap = new Bitmap[2];
            Prasasti.loading.setVisibility(View.VISIBLE);
            startLoading();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loadAllImage();
            Prasasti.loading.setVisibility(View.GONE);
            super.onPostExecute(aVoid);
        }

        private void loadImage(int x, int y){
            ImageView temp = (ImageView) findViewById(x);
            if (temp != null) {
                temp.setImageBitmap(bitmap[y]);
            }
        }

        private void loadAllImage(){
            loadImage(R.id.prasasti_load_background_dasar, 0);
            loadImage(R.id.prasasti_load_teks_prasasti, 1);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
            bitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.teks_prasasti);

            if(Prasasti.petaPrasasti_cache[0] == null)
                Prasasti.petaPrasasti_cache[0] = BitmapFactory.decodeResource(getResources(), R.drawable.peta_singasari);

            if(Prasasti.petaPrasasti_cache[1] == null)
                Prasasti.petaPrasasti_cache[1] = BitmapFactory.decodeResource(getResources(), R.drawable.peta_mula_malurung);

            if(Prasasti.petaPrasasti_cache[2] == null)
                Prasasti.petaPrasasti_cache[2] = BitmapFactory.decodeResource(getResources(), R.drawable.peta_manjsuri);

            if(Prasasti.petaPrasasti_cache[3] == null)
                Prasasti.petaPrasasti_cache[3] = BitmapFactory.decodeResource(getResources(), R.drawable.peta_wurare);

            if(Prasasti.activeButton_cache[0] == null)
                Prasasti.activeButton_cache[0] = BitmapFactory.decodeResource(getResources(), R.drawable.singasari_highlight);

            if(Prasasti.activeButton_cache[1] == null)
                Prasasti.activeButton_cache[1] = BitmapFactory.decodeResource(getResources(), R.drawable.mula_malurung_highlight);

            if(Prasasti.activeButton_cache[2] == null)
                Prasasti.activeButton_cache[2] = BitmapFactory.decodeResource(getResources(), R.drawable.manjsuri_highlight);

            if(Prasasti.activeButton_cache[3] == null)
                Prasasti.activeButton_cache[3] = BitmapFactory.decodeResource(getResources(), R.drawable.wurare_highlight);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void initSelektor(){
        prasasti[0] = (ImageView) findViewById(R.id.prasasti_singasari);
        prasasti[1] = (ImageView) findViewById(R.id.prasasti_mula_malurung);
        prasasti[2] = (ImageView) findViewById(R.id.prasasti_manjsuri);
        prasasti[3] = (ImageView) findViewById(R.id.prasasti_wurare);
        backToMenuArtefak = (ImageView) findViewById(R.id.back_to_menu_artefak);
        readMore = (ImageView) findViewById(R.id.read_more);
        petaLokasi = (ImageView) findViewById(R.id.prasasti_load_peta_lokasi_prasasti);
    }

    private void setNormalButton(){

    }

    private void pindahPeta(Integer i){
        setNormalButton();
        petaLokasi.setVisibility(View.VISIBLE);
        petaLokasi.setImageBitmap(petaPrasasti_cache[i]);
    }

    // Jelas
    public void initListener(){

        prasasti[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pindahPeta(0);
            }
        });
        prasasti[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pindahPeta(1);
            }
        });
        prasasti[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pindahPeta(2);
            }
        });
        prasasti[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pindahPeta(3);
            }
        });
        backToMenuArtefak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goBack();
            }
        });
        readMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

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
