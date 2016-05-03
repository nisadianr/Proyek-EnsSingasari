package com.example.user.enssingasari.data;

import android.app.Activity;

/**
 * Created by user on 5/3/2016.
 */
public class Menu {
    private String name;
    private int image;
    private Activity activity;

    //setter
    public void setName(String s){
        name = s;
    }
    public void setImage(int id){
        image = id;
    }
    public void setActivity(Activity act){
        activity = act;
    }

    //getter
    public String getName(){
        return name;
    }
    public int getImage_big(){
        return image;
    }
    public Activity getActivity(){
        return activity;
    }
}
