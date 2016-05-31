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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.enssingasari.R;

public class Candi extends AppCompatActivity {

    public static Bitmap[] asetBackgroundTab, asetProfile, asetKarakteristik, asetBagian, asetLokasi;
    public static RelativeLayout loading;
    private LinearLayout[] layoutTab;
    private ImageView[] bgTab;
    private ImageView viewTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_candi);
        loading = (RelativeLayout) findViewById(R.id.progressBar1);
        initSelektor();
        initListener();
        alokasiAset();
        LoadAll lall = new LoadAll();
        lall.execute();
    }


    /*
        Inisialiasi data view yang akan dimanipulasi
     */
    private void initSelektor(){
        layoutTab = new LinearLayout[4];
        layoutTab[0] = (LinearLayout) findViewById(R.id.candi_konten_tab_profile);
        layoutTab[1] = (LinearLayout) findViewById(R.id.candi_konten_tab_karakteristik);
        layoutTab[2] = (LinearLayout) findViewById(R.id.candi_konten_tab_bagian);
        layoutTab[3] = (LinearLayout) findViewById(R.id.candi_konten_tab_lokasi);

        viewTab = (ImageView) findViewById(R.id.candi_load_candi_show_up);

        bgTab = new ImageView[4];
        bgTab[0] = (ImageView) findViewById(R.id.candi_load_pos_1);
        bgTab[1] = (ImageView) findViewById(R.id.candi_load_pos_2);
        bgTab[2] = (ImageView) findViewById(R.id.candi_load_pos_3);
        bgTab[3] = (ImageView) findViewById(R.id.candi_load_pos_4);
    }

    /*
        Inisialisasi Aksi Klik
     */
    private void initListener(){
        TextView[] listenerTab = new TextView[4];
        listenerTab[0] = (TextView) findViewById(R.id.candi_trigger_profile);
        listenerTab[1] = (TextView) findViewById(R.id.candi_trigger_karakteristik);
        listenerTab[2] = (TextView) findViewById(R.id.candi_trigger_bagian);
        listenerTab[3] = (TextView) findViewById(R.id.candi_trigger_lokasi);

        listenerTab[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setKontenTab(0);
            }
        });

        listenerTab[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setKontenTab(1);
            }
        });

        listenerTab[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setKontenTab(2);
            }
        });

        listenerTab[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setKontenTab(3);
            }
        });

        ImageView[] listenerTabBagian = new ImageView[8];
        listenerTabBagian[0] = (ImageView) findViewById(R.id.candi_trigger_bagian_1_button);
        listenerTabBagian[1] = (ImageView) findViewById(R.id.candi_trigger_bagian_2_button);
        listenerTabBagian[2] = (ImageView) findViewById(R.id.candi_trigger_bagian_3_button);
        listenerTabBagian[3] = (ImageView) findViewById(R.id.candi_trigger_bagian_41_button);
        listenerTabBagian[4] = (ImageView) findViewById(R.id.candi_trigger_bagian_42_button);
        listenerTabBagian[5] = (ImageView) findViewById(R.id.candi_trigger_bagian_51_button);
        listenerTabBagian[6] = (ImageView) findViewById(R.id.candi_trigger_bagian_52_button);

        listenerTabBagian[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetBagianSub(1);
            }
        });

        listenerTabBagian[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetBagianSub(2);
            }
        });

        listenerTabBagian[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetBagianSub(3);
            }
        });

        listenerTabBagian[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetBagianSub(4);
            }
        });

        listenerTabBagian[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetBagianSub(5);
            }
        });

        listenerTabBagian[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetBagianSub(6);
            }
        });

        listenerTabBagian[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetBagianSub(7);
            }
        });

        ImageView[] listenerTabLokasi = new ImageView[5];
        listenerTabLokasi[0] = (ImageView) findViewById(R.id.candi_trigger_lokasi_kidal_button);
        listenerTabLokasi[1] = (ImageView) findViewById(R.id.candi_trigger_lokasi_sumberawan_button);
        listenerTabLokasi[2] = (ImageView) findViewById(R.id.candi_trigger_lokasi_singasari_button);
        listenerTabLokasi[3] = (ImageView) findViewById(R.id.candi_trigger_lokasi_jawi_button);
        listenerTabLokasi[4] = (ImageView) findViewById(R.id.candi_trigger_lokasi_jago_button);

        listenerTabLokasi[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetLokasiSub(0);
            }
        });

        listenerTabLokasi[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetLokasiSub(1);
            }
        });

        listenerTabLokasi[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetLokasiSub(2);
            }
        });

        listenerTabLokasi[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetLokasiSub(3);
            }
        });

        listenerTabLokasi[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setAsetLokasiSub(4);
            }
        });

        ImageView homeButton = (ImageView) findViewById(R.id.candi_home_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                goBack();
            }
        });

    }

    /*
        Initial View
     */
    public void initView(){
        setKontenTab(0);
        setAsetProfile();
    }

    /*
        Fungsi: Alokasi Gambar yang akan di cache jadi Bitmap
     */
    private void alokasiAset(){

        /*
            Background Tab
         */
        asetBackgroundTab = new Bitmap[4];

        /*
            -- Profile
            Kiri: Teks (statik)
            Kanan: Candi
         */
        asetProfile = new Bitmap[1];


        /*
            -- Karakteristik
            Kiri: Teks (tabel statik)
            Kanan: Candi
         */
        asetKarakteristik = new Bitmap[1];


        /*
            -- Bagian
            Kiri: [Teks] = 1 (statik)
            exclude: [1n, 2n, 3n, 41n, 42n, 51n, 51n, 1, 2, 3, 41, 42, 51, 51] = 14
            Kanan: [candi0, candi1, candi2, candi3, candi41, candi42, candi51, candi52] = 8
         */
        asetBagian = new Bitmap[8];


        /*
            -- Bagian
            Kiri: [Teks] = 1 (statik)
            exclude: [kidaln, sumberawann, singasarin, jawin, jagon, kidal, sumberawan, singasari, jawi, jago]
         */
        asetLokasi = new Bitmap[5];
    }

    /*
        Fungsi: Menyembunyikan semua konten tab
     */
    private void hideSemuaKontenTab(){
        for(int i = 0; i < 4; i++)
            layoutTab[i].setVisibility(View.GONE);
    }

    /*
        Fungsi: Memunculkan konten tab ke-i
     */
    public void setKontenTab(int i){
        hideSemuaKontenTab();
        layoutTab[i].setVisibility(View.VISIBLE);
        setAsetBackgroundTab(i);
        switch (i){
            case 0:
                setAsetProfile();
                break;
            case 1:
                setAsetKarakteristik();
                break;
            case 2:
                setAsetBagian();
                break;
            case 3:
                setAsetLokasi();
                break;
        }
    }

    public void setAsetBackgroundTab(int i){
        int j = i;
        for(int k = 0; k < 4; k++)
            bgTab[k].setImageBitmap(asetBackgroundTab[(j + k) % 4]);
    }

    /*
        Fungsi mengeset gambar awal pada bagian kanan saat tab Profile >> lihat wireframe
     */
    public void setAsetProfile(){
        viewTab.setImageBitmap(asetProfile[0]);
    }

    /*
        Fungsi mengeset gambar awal pada bagian kanan saat tab Karakteristik >> lihat wireframe
     */
    public void setAsetKarakteristik(){
        viewTab.setImageBitmap(asetKarakteristik[0]);
    }

    /*
        Fungsi mengeset gambar awal pada bagian kanan saat tab Bagian >> lihat wireframe
     */
    public void setAsetBagian(){
        viewTab.setImageBitmap(asetBagian[0]);
    }

    /*
        Fungsi mengeset gambar awal pada bagian kanan saat button pada
        tab Bagian di klik >> lihat wireframe
     */
    public void setAsetBagianSub(int i){
        viewTab.setImageBitmap(asetBagian[i]);
    }

    /*
        Fungsi mengeset gambar awal pada bagian kanan saat tab Lokasi >> lihat wireframe
     */
    public void setAsetLokasi(){
        viewTab.setImageBitmap(asetLokasi[0]);
    }

    /*
        Fungsi mengeset gambar awal pada bagian kanan saat button pada
        tab Lokasi di klik >> lihat wireframe
     */
    public void setAsetLokasiSub(int i){
        viewTab.setImageBitmap(asetLokasi[i]);
    }

    private class LoadAll extends AsyncTask<Integer, Void, Void> {

        private Bitmap[] bitmap;

        /*
            Memulai animasi loading fade-in dan fade-out tak berhingga
         */
        private void startLoading(){
            final Animation animInOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_out);
            ImageView loadingGbr = (ImageView) findViewById(R.id.loading_gbr_candi);
            ImageView loadingTeks = (ImageView) findViewById(R.id.loading_teks_candi);
            loadingGbr.startAnimation(animInOut);
            loadingTeks.startAnimation(animInOut);
        }

        /*
            Konstruktor memunculkan animasi loading
         */
        public LoadAll(){
            bitmap = new Bitmap[6];
            Candi.loading.setVisibility(View.VISIBLE);
            startLoading();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loadAllImage();
            initView();
            Candi.loading.setVisibility(View.GONE);
            super.onPostExecute(aVoid);
        }

        private void loadImage(int x, int y){
            ImageView temp = (ImageView) findViewById(x);
            if (temp != null) {
                temp.setImageBitmap(bitmap[y]);
            }
        }

        private void loadAllImage(){
            loadImage(R.id.candi_load_background_dasar, 0);
            loadImage(R.id.candi_load_background_dasar_2, 1);
            loadImage(R.id.candi_load_profile_teks, 2);
            loadImage(R.id.candi_load_karakteristik_tabel, 3);
            loadImage(R.id.candi_load_bagian_teks, 4);
            loadImage(R.id.candi_load_lokasi_teks, 5);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Frame Tab
            if(asetBackgroundTab[0] == null)
                asetBackgroundTab[0] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_subcandi_profile);

            if(asetBackgroundTab[1] == null)
                asetBackgroundTab[1] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_subcandi_karakteristik);

            if(asetBackgroundTab[2] == null)
                asetBackgroundTab[2] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_subcandi_bagian);

            if(asetBackgroundTab[3] == null)
                asetBackgroundTab[3] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_subcandi_lokasi);

            // Background 1, 2
            bitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bg_loading);
            bitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_subcandi_black_border);

            // Aset Profil (statik)
            bitmap[2] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_profile_teks_1);

            // Aset Profile
            if(asetProfile[0] == null)
                asetProfile[0] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_profile_peraga);

            //---

            // Aset Karakteristik (statik)
            bitmap[3] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_karakteristik_tabel);

            // Aset Karakteristik
            if(asetKarakteristik[0] == null)
                asetKarakteristik[0] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_karakteristik_beda_candi);

            //---

            // Aset Bagian (statik)
            bitmap[4] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_teks);

            // Aset Bagian
            if(asetBagian[0] == null)
                asetBagian[0] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_0);

            if(asetBagian[1] == null)
                asetBagian[1] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_1);

            if(asetBagian[2] == null)
                asetBagian[2] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_2);

            if(asetBagian[3] == null)
                asetBagian[3] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_3);

            if(asetBagian[4] == null)
                asetBagian[4] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_41);

            if(asetBagian[5] == null)
                asetBagian[5] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_42);

            if(asetBagian[6] == null)
                asetBagian[6] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_51);

            if(asetBagian[7] == null)
                asetBagian[7] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_bagian_kanan_52);

            //---

            // Aset Bagian (statik)
            bitmap[5] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_lokasi_teks);

            // Aset Lokasi
            if(asetLokasi[0] == null)
                asetLokasi[0] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_lokasi_kidal);

            if(asetLokasi[1] == null)
                asetLokasi[1] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_lokasi_sumberawan);

            if(asetLokasi[2] == null)
                asetLokasi[2] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_lokasi_singasasri);

            if(asetLokasi[3] == null)
                asetLokasi[3] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_lokasi_jawi);

            if(asetLokasi[4] == null)
                asetLokasi[4] = BitmapFactory.decodeResource(getResources(), R.drawable.candi_lokasi_jago);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
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
