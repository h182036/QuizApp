package com.example.dat153oblig1;

import android.graphics.drawable.Drawable;

public class Katt {

    String id;
    String navn;
    Drawable img;

    Katt (String id, String navn, Drawable img){
        this.id = id;
        this.navn = navn;
        this.img = img;
    }

    public String getId(){

        return id;
    }

    public String getNavn() {

        return navn;
    }


    public Drawable getImg() {

        return img;
    }

}

