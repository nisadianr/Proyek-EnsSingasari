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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TokohMenuActivity extends Activity {

    private HashMap<Integer,JSONObject> data_image;
    int title_res = R.drawable.title_tokoh_kenarok;
    private Dialog dialog_silsilah;

    public TokohMenuActivity() {
        try {
            data_image = attachDataImage();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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
        ImageView trivia = (ImageView) findViewById(R.id.trivia_button);

        //set resource
        title_tokoh.setImageResource(R.drawable.title_tokoh_kenarok);
        image_tokoh.setImageResource(R.drawable.image_tokoh_kenarok);
        radioGroup.check(R.id.radio_tokoh1);

        //set listener
        trivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog_trivia = new Dialog(TokohMenuActivity.this);
                dialog_trivia.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_trivia.setContentView(R.layout.popup_trivia_tokoh);

                ImageView description = (ImageView) dialog_trivia.findViewById(R.id.desc_tokoh_trivia);
                ImageView image = (ImageView) dialog_trivia.findViewById(R.id.image_tokoh_trivia);
                try {
                    description.setImageResource(getImageTokoh(title_res,"tdesc"));
                    image.setImageResource(getImageTokoh(title_res,"timage"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Button close_btn = (Button) dialog_trivia.findViewById(R.id.close_button);
                close_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_trivia.cancel();
                    }
                });
                dialog_trivia.show();
            }
        });
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

                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio_tokoh1:
                        title_res = R.drawable.title_tokoh_kenarok;
                        break;
                    case R.id.radio_tokoh2:
                        title_res = R.drawable.title_tokoh_kendedes;
                        break;
                    case R.id.radio_tokoh3:
                        title_res = R.drawable.title_tokoh_tunggulametung;
                        break;
                    case R.id.radio_tokoh4:
                        title_res = R.drawable.title_tokoh_empugandring;
                        break;
                    case R.id.radio_tokoh5:
                        title_res = R.drawable.title_tokoh_parameswara;
                        break;
                    case R.id.radio_tokoh6:
                        title_res = R.drawable.title_tokoh_kertanegara;
                        break;
                }
                try {
                    image_tokoh.setImageResource(getImageTokoh(title_res,"image"));
                    desc_tokoh.setImageResource(getImageTokoh(title_res,"desc"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                title_tokoh.setImageResource(title_res);
            }
        });
    }

    private Integer getImageTokoh(Integer id,String code) throws JSONException {
        return (Integer) data_image.get(id).get(code);
    }

    HashMap<Integer,JSONObject> attachDataImage() throws JSONException {
        HashMap<Integer,JSONObject> data = new HashMap<>();
        data.put(R.drawable.title_tokoh_kenarok,
                new JSONObject().put("image",R.drawable.image_tokoh_kenarok)
                .put("desc",R.drawable.desc_tokoh_kenarok)
                .put("timage",R.drawable.trivia_image_kenarok)
                .put("tdesc",R.drawable.trivia_desc_kenarok));

        data.put(R.drawable.title_tokoh_kendedes,
                new JSONObject().put("image",R.drawable.image_tokoh_kendedes)
                .put("desc",R.drawable.desc_tokoh_kendedes)
                .put("timage",R.drawable.trivia_image_kendedes)
                .put("tdesc",R.drawable.trivia_desc_kendedes));

        data.put(R.drawable.title_tokoh_tunggulametung,
                new JSONObject().put("image",R.drawable.image_tokoh_tunggulametung)
                        .put("desc",R.drawable.desc_tokoh_tunggulametung)
                        .put("timage",R.drawable.trivia_image_tunggulametung)
                        .put("tdesc",R.drawable.trivia_desc_tunggulametung));

        data.put(R.drawable.title_tokoh_empugandring,
                new JSONObject().put("image",R.drawable.image_tokoh_empugandring)
                        .put("desc",R.drawable.desc_tokoh_empugandring)
                        .put("timage",R.drawable.trivia_image_empugandring)
                        .put("tdesc",R.drawable.trivia_desc_empugandring));

        data.put(R.drawable.title_tokoh_parameswara,
                new JSONObject().put("image",R.drawable.image_tokoh_parameswara)
                        .put("desc",R.drawable.desc_tokoh_parameswara)
                        .put("timage",R.drawable.trivia_image_parameswara)
                        .put("tdesc",R.drawable.trivia_desc_parameswara));

        data.put(R.drawable.title_tokoh_kertanegara,
                new JSONObject().put("image",R.drawable.image_tokoh_kertanegara)
                        .put("desc",R.drawable.desc_tokoh_kertanegara)
                        .put("timage",R.drawable.trivia_image_kertanegara)
                        .put("tdesc",R.drawable.trivia_desc_kertanegara));


        return data;
    }
}
