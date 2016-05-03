package com.example.user.enssingasari.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.enssingasari.R;

//this class is for the start menu

public class StartScreen extends Activity implements View.OnClickListener{
//    private FragmentManager fragmentManager ;
//    private FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
//        fragmentManager = getActivity().getFragmentManager();

        Button start = (Button) findViewById(R.id.start_button);
        start.setOnClickListener(this);
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.start_screen, container,false);
//        ImageButton startbutton = (ImageButton) view.findViewById(R.id.start_button);
//
//        startbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager=getFragmentManager();
//                view.setEnabled(false);
//                transaction = fragmentManager.beginTransaction().add(new MainMenu).hide(
//                        StartScreen.this
//                ).addToBackStack(StartScreen.class.getName());
//            }
//        })
//
//        return view;
//    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
