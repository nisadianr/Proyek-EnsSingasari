package com.example.user.enssingasari.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.user.enssingasari.R;

import java.util.HashMap;

public class TokohMenuActivity extends Activity {

    private HashMap<Integer,Integer> data = attachData();
    private Dialog dialog_silsilah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tokoh_menu);

        final ImageView title_tokoh = (ImageView) findViewById(R.id.nama_tokoh);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        final ImageView image_tokoh = (ImageView) findViewById(R.id.image_big);
        final ImageView desc_tokoh = (ImageView) findViewById(R.id.tokoh_desc);
        ImageView silsilah = (ImageView) findViewById(R.id.silsilah_tokoh);
        Button home =(Button) findViewById(R.id.home_button);

        //set resource
        title_tokoh.setImageResource(R.drawable.title_tokoh_kenarok);
        image_tokoh.setImageResource(R.drawable.image_tokoh_kenarok);
        radioGroup.check(R.id.radio_tokoh1);

        //set listener
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        silsilah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_silsilah = new Dialog(TokohMenuActivity.this);
                dialog_silsilah.setTitle("Silsilah Tokohx");
                dialog_silsilah.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_silsilah.setContentView(R.layout.popup_silsilah_tokoh);
                dialog_silsilah.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                Button close_btn = (Button) dialog_silsilah.findViewById(R.id.close_button);
                close_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_silsilah.cancel();
                    }
                });
                dialog_silsilah.show();
             }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int title_res = 0;
                int desc_res =0;

                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio_tokoh1:
                        title_res = R.drawable.title_tokoh_kenarok;
                        desc_res = R.drawable.desc_tokoh_kenarok;
                        break;
                    case R.id.radio_tokoh2:
                        title_res = R.drawable.title_tokoh_kendedes;
                        desc_res = R.drawable.desc_tokoh_kendedes;
                        break;
                    case R.id.radio_tokoh3:
                        title_res = R.drawable.title_tokoh_tunggulametung;
                        desc_res = R.drawable.desc_tokoh_tunggulametung;
                        break;
                    case R.id.radio_tokoh4:
                        title_res = R.drawable.title_tokoh_empugandring;
                        desc_res = R.drawable.desc_tokoh_empugandring;
                        break;
                    case R.id.radio_tokoh5:
                        title_res = R.drawable.title_tokoh_parameswara;
                        desc_res = R.drawable.desc_tokoh_parameswara;
                        break;
                    case R.id.radio_tokoh6:
                        title_res = R.drawable.title_tokoh_kertanegara;
                        desc_res = R.drawable.desc_tokoh_kertanegara;
                        break;
                }
                image_tokoh.setImageResource(data.get(title_res));
                title_tokoh.setImageResource(title_res);
                desc_tokoh.setImageResource(desc_res);
            }
        });
    }

    HashMap<Integer,Integer> attachData(){
        HashMap<Integer,Integer> data = new HashMap<>();
        data.put(R.drawable.title_tokoh_kenarok,R.drawable.image_tokoh_kenarok);
        data.put(R.drawable.title_tokoh_kendedes,R.drawable.image_tokoh_kendedes);
        data.put(R.drawable.title_tokoh_tunggulametung,R.drawable.image_tokoh_tunggulametung);
        data.put(R.drawable.title_tokoh_empugandring,R.drawable.image_tokoh_empugandring);
        data.put(R.drawable.title_tokoh_parameswara,R.drawable.image_tokoh_parameswara);
        data.put(R.drawable.title_tokoh_kertanegara,R.drawable.image_tokoh_kertanegara);

        return data;
    }
}
