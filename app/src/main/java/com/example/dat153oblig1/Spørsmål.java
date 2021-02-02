package com.example.dat153oblig1;

import android.graphics.drawable.Drawable;
import android.app.Application;

import java.util.ArrayList;

public class Spørsmål extends Application {

    private ArrayList<Katt> katter = new ArrayList<>();

    /**
     * @param id
     * @param img
     * @param navn
     */
    public void addKatt(String id, String navn, Drawable img) {
        katter.add(new Katt(id, navn, img));
    }

    /**
     * @return
     */
    public ArrayList<Katt> getKatter() {

        return katter;
    }

    /**
     * @param index
     */
    public void deleteKatt(int index) {

        katter.remove(index);
    }

    public void clear() {

        katter.clear();
    }

    /**
     * Function for getting list count.
     *
     * @return list length
     */
    public int getCount() {

        return katter.size();
    }
}
