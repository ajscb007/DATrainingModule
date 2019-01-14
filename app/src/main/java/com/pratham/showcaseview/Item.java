package com.pratham.showcaseview;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by AJ on 08-01-2019.
 */

public class Item {
    private String Title;
    private Bitmap Image;

    public Item() {
    }

    public Item(String title, Bitmap image) {
        Title = title;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }
}
