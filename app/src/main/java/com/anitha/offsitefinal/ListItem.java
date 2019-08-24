package com.anitha.offsitefinal;

import android.graphics.drawable.Drawable;

public class ListItem {

    String name;
    Drawable image;
    Drawable image2;

    public ListItem(Drawable image, String name) {
        super();

        this.image = image;
        this.name = name;
    }
    public ListItem() {

    }
    public ListItem(String name) {
        super();
        this.name = name;

    }

    public ListItem(Drawable image) {
        super();
        this.image = image;
    }

    public ListItem(Drawable image2, int i) {
        super();
        this.image2=image2;
    }

    public Drawable getImage2() {
        return image2;
    }

    public void setImage2(Drawable image2) {
        this.image2 = image2;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
