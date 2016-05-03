package com.example.user.enssingasari.data;

import com.example.user.enssingasari.R;
import com.example.user.enssingasari.activity.TokohMenu;

import java.util.ArrayList;

/**
 * Created by user on 5/3/2016.
 */
public class MenuData {
    private ArrayList<Menu> arrayMenu = new ArrayList<>();

    public MenuData(){
        Menu data= new Menu();

        //set Tokoh
        data.setName("Tokoh");
        data.setImage(R.drawable.main_menu_0010_icon_tokoh);
        data.setActivity(new TokohMenu());
        arrayMenu.add(data);

        //set Timeline
        data.setName("Timeline");
        data.setImage(R.drawable.main_menu_0011_icon_sejarah);
        arrayMenu.add(data);

        //set Artefak
        data.setName("Artefak");
        data.setImage(R.drawable.main_menu_0009_icon_artefak);
        arrayMenu.add(data);

        //set Peta
        data.setName("Peta");
        data.setImage(R.drawable.main_menu_0008_icon_peta);
        arrayMenu.add(data);

        //set Glosrium
        data.setName("Glosarium");
        data.setImage(R.drawable.main_menu_0007_icon_glosarium);
        arrayMenu.add(data);
    }

    public Menu getMenuDataIndex(int index){
        return arrayMenu.get(index);
    }

}
