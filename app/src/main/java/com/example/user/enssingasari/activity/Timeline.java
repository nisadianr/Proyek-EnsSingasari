package com.example.lifeuniverse.storysingosari;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Timeline extends AppCompatActivity {

    private ImageView line, lineLink[], bgPopup, home, close, right, left;
    private TextViewPlus textPopup;
    private TextViewPlus textDasar;
    private FrameLayout layoutPopup;
    private LinearLayout textLink;
    public static RelativeLayout relUtama;
    private TextView tTextLink[];
    public static Bitmap bgPopup_cache[];
    private static Integer lastCounter = 0, counter = 0, scrW = 0, scrH = 0;
    private boolean popupMode;
    public static RelativeLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timeline);

        lineLink = new ImageView[7];
        tTextLink = new TextView[7];
        bgPopup_cache = new Bitmap[7];
        loading = (RelativeLayout) findViewById(R.id.progressBar1);
        relUtama = (RelativeLayout) findViewById(R.id.rel_utama);
        popupMode = false;
        LoadAll lall = new LoadAll();
        lall.execute();
        initUkuranLayar();
        initSelektorGambar();
        initListener();
        hideAllLink();
        hidePopup();
    }

    private class LoadAll extends AsyncTask<Integer, Void, Void> {

        private Bitmap[] bitmap;

        /*
            Memulai animasi loading fade-in dan fade-out tak berhingga
         */
        private void startLoading(){
            final Animation animInOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_out);
            ImageView loadingGbr = (ImageView) findViewById(R.id.loading_gbr_timeline);
            ImageView loadingTeks = (ImageView) findViewById(R.id.loading_teks_timeline);
            loadingGbr.startAnimation(animInOut);
            loadingTeks.startAnimation(animInOut);
        }

        /*
            Konstruktor memunculkan animasi loading
         */
        public LoadAll(){
            bitmap = new Bitmap[7];
            Timeline.loading.setVisibility(View.VISIBLE);
            startLoading();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loadAllImage();
            Timeline.loading.setVisibility(View.GONE);
            super.onPostExecute(aVoid);
        }

        private void loadImage(int x, int y){
            ImageView temp = (ImageView) findViewById(x);
            if (temp != null) {
                temp.setImageBitmap(bitmap[y]);
            }
        }

        private void loadAllImage(){
            loadImage(R.id.background_dasar, 0);
            loadImage(R.id.sejarah, 1);
            loadImage(R.id.gambar_arca_candi, 2);
            loadImage(R.id.tulisan_timeline, 3);
            loadImage(R.id.gambar_timeline, 4);
            loadImage(R.id.line_link_1, 5);
            loadImage(R.id.line_link_2, 5);
            loadImage(R.id.line_link_3, 5);
            loadImage(R.id.line_link_4, 5);
            loadImage(R.id.line_link_5, 5);
            loadImage(R.id.line_link_6, 5);
            loadImage(R.id.line_link_7, 5);
            loadImage(R.id.bgPopup, 6);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
            bitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.sejarah);
            bitmap[2] = BitmapFactory.decodeResource(getResources(), R.drawable.foto_arca_candi);
            bitmap[3] = BitmapFactory.decodeResource(getResources(), R.drawable.timeline);
            bitmap[4] = BitmapFactory.decodeResource(getResources(), R.drawable.line);
            bitmap[5] = BitmapFactory.decodeResource(getResources(), R.drawable.line_link);
            bitmap[6] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_1);

            if(Timeline.bgPopup_cache[0] == null)
                Timeline.bgPopup_cache[0] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_1);

            if(Timeline.bgPopup_cache[1] == null)
                Timeline.bgPopup_cache[1] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_2);

            if(Timeline.bgPopup_cache[2] == null)
                Timeline.bgPopup_cache[2] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_3);

            if(Timeline.bgPopup_cache[3] == null)
                Timeline.bgPopup_cache[3] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_4);

            if(Timeline.bgPopup_cache[4] == null)
                Timeline.bgPopup_cache[4] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_5);

            if(Timeline.bgPopup_cache[5] == null)
                Timeline.bgPopup_cache[5] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_6);

            if(Timeline.bgPopup_cache[6] == null)
                Timeline.bgPopup_cache[6] = BitmapFactory.decodeResource(getResources(), R.drawable.frame_popup_7);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void initUkuranLayar(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        scrW = size.x;
        scrH = size.y;
    }

    public void initSelektorGambar(){

        layoutPopup = (FrameLayout) findViewById(R.id.popup_view);
        bgPopup = (ImageView) findViewById(R.id.bgPopup);
        textPopup = (TextViewPlus) findViewById(R.id.textPopup);
        textDasar = (TextViewPlus) findViewById(R.id.teks_utama);

        Double sizeT;

        // Magic Number
        // Angka ini dari hasil perbandingan coba-coba
        // Demi tampilan responsif
        sizeT = 0.03325 * scrH;
        textDasar.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeT.floatValue());
        sizeT = 0.010020833 * scrH;
        textDasar.setLineSpacing(sizeT.floatValue(), .8f);

        sizeT = 0.038807292 * scrH;
        textPopup.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeT.floatValue());
        sizeT = 0.006825521 * scrH;
        textPopup.setLineSpacing(sizeT.floatValue(), .8f);

        home = (ImageView) findViewById(R.id.home);
        close = (ImageView) findViewById(R.id.close_button);
        right = (ImageView) findViewById(R.id.right_button);
        left = (ImageView) findViewById(R.id.left_button);
        textLink = (LinearLayout) findViewById(R.id.text_link);
        initLineLinkSelector();
    }

    /*
        Init selektor utk dummy TextView dan garis link dibawah popup
     */
    public void initLineLinkSelector(){
        lineLink[0] = (ImageView) findViewById(R.id.line_link_1);
        lineLink[1] = (ImageView) findViewById(R.id.line_link_2);
        lineLink[2] = (ImageView) findViewById(R.id.line_link_3);
        lineLink[3] = (ImageView) findViewById(R.id.line_link_4);
        lineLink[4] = (ImageView) findViewById(R.id.line_link_5);
        lineLink[5] = (ImageView) findViewById(R.id.line_link_6);
        lineLink[6] = (ImageView) findViewById(R.id.line_link_7);

        tTextLink[0] = (TextView) findViewById(R.id.text_link_1);
        tTextLink[1] = (TextView) findViewById(R.id.text_link_2);
        tTextLink[2] = (TextView) findViewById(R.id.text_link_3);
        tTextLink[3] = (TextView) findViewById(R.id.text_link_4);
        tTextLink[4] = (TextView) findViewById(R.id.text_link_5);
        tTextLink[5] = (TextView) findViewById(R.id.text_link_6);
        tTextLink[6] = (TextView) findViewById(R.id.text_link_7);
    }


    // Counter utk next dan prev (left arrow + right arrow popup)
    private Integer nextCounter(){
        counter++;
        counter %= 7;

        return counter;
    }
    private Integer prevCounter(){
        counter = counter + 6;
        counter %= 7;

        return counter;
    }

    // Jelas
    public void initListener(){
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                if(popupMode)
                    hidePopup();
                else
                    goBack();
            }
        });

        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                if(popupMode) hidePopup();
            }
        });

        right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(nextCounter());
            }
        });

        left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(prevCounter());
            }
        });

        tTextLink[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(counter = 0);
            }
        });

        tTextLink[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(counter = 1);
            }
        });

        tTextLink[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(counter = 2);
            }
        });

        tTextLink[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(counter = 3);
            }
        });

        tTextLink[4].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(counter = 4);
            }
        });

        tTextLink[5].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(counter = 5);
            }
        });

        tTextLink[6].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                lastCounter = counter;
                showPopup(counter = 6);
            }
        });
    }

    /*
        Menyembunyikan garis bercahaya sebelumnya ketika berganti popup
     */
    private void hideLastLineLink(){
        lineLink[lastCounter].setVisibility(View.INVISIBLE);
    }

    /*
        Menyembunyikan garis bercahaya dibawah popup
     */
    private void showLineLink(Integer i){
        hideLastLineLink();
        lineLink[i].setVisibility(View.VISIBLE);
    }

    public void hideAllLink(){
        for(ImageView v : lineLink)
            v.setVisibility(View.INVISIBLE);
    }

    /*
        Menyembunyikan popup
     */
    public void hidePopup(){
        hideLastLineLink();
        layoutPopup.setVisibility(View.GONE);
        popupMode = false;
        close.setVisibility(View.GONE);
        right.setVisibility(View.GONE);
        left.setVisibility(View.GONE);
    }

    /*
        Menampilkan popup
     */
    private void showPopup(Integer i){
        switch(i){
            case 0: showPopup1(); break;
            case 1: showPopup2(); break;
            case 2: showPopup3(); break;
            case 3: showPopup4(); break;
            case 4: showPopup5(); break;
            case 5: showPopup6(); break;
            case 6: showPopup7(); break;
        }
        if(!popupMode) {
            layoutPopup.setVisibility(View.VISIBLE);
            close.setVisibility(View.VISIBLE);
            right.setVisibility(View.VISIBLE);
            left.setVisibility(View.VISIBLE);
        }

        popupMode = true;
    }

    // Cukup Jelas
    private void showPopup1(){
        bgPopup.setImageBitmap(bgPopup_cache[0]);
        //textPopup.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame_text_1));
        textPopup.setText(R.string.teks_panjang_1);
        showLineLink(0);
    }

    // Cukup Jelas
    private void showPopup2(){
        bgPopup.setImageBitmap(bgPopup_cache[1]);
        //textPopup.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame_text_2));
        textPopup.setText(R.string.teks_panjang_2);
        showLineLink(1);
    }

    // Cukup Jelas
    private void showPopup3(){
        bgPopup.setImageBitmap(bgPopup_cache[2]);
        //textPopup.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame_text_3));
        textPopup.setText(R.string.teks_panjang_3);
        showLineLink(2);
    }

    // Cukup Jelas
    private void showPopup4(){
        bgPopup.setImageBitmap(bgPopup_cache[3]);
        //textPopup.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame_text_4));
        textPopup.setText(R.string.teks_panjang_4);
        showLineLink(3);
    }

    // Cukup Jelas
    private void showPopup5(){
        bgPopup.setImageBitmap(bgPopup_cache[4]);
        //textPopup.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame_text_5));
        textPopup.setText(R.string.teks_panjang_5);
        showLineLink(4);
    }

    // Cukup Jelas
    private void showPopup6(){
        bgPopup.setImageBitmap(bgPopup_cache[5]);
        //textPopup.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame_text_6));
        textPopup.setText(R.string.teks_panjang_6);
        showLineLink(5);
    }

    // Cukup Jelas
    private void showPopup7(){
        bgPopup.setImageBitmap(bgPopup_cache[6]);
        //textPopup.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame_text_7));
        textPopup.setText(R.string.teks_panjang_7);
        showLineLink(6);
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



    // HISTORY - Sometimes i'll need this

    /*
    private void initUkuranLayar(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        scrW = size.x;
        scrH = size.y;
    }

    public static int calculateInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private void setGambar(int id, int drawable, int w, int h){
        ImageView temp = (ImageView) findViewById(id);
        temp.setImageBitmap(decodeSampledBitmapFromResource(getResources(), drawable, w, h));
    }

    private void initSemuaGambar(){
        setGambar(R.id.background_dasar, R.drawable.bg, scrW, scrH);
        setGambar(R.id.background_2, R.drawable.bg2, scrW, scrH);
        aq.id(R.id.background_dasar).image(R.drawable.bg).visible();
        aq.id(R.id.background_2).image(R.drawable.bg2).visible();
        aq.id(R.id.teks_utama).image(R.drawable.teksutama).visible();
        aq.id(R.id.gambar_arca_candi).image(R.drawable.foto_arca_candi).visible();
        aq.id(R.id.tulisan_timeline).image(R.drawable.timeline).visible();
        aq.id(R.id.line).image(R.drawable.line).visible();
    }
    */
