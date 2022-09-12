package com.tequip.cryptotracker.Model;

public class ScreenItem {
    int ScreenImg;

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public ScreenItem(String title, String description, int screenImg) {
        ScreenImg = screenImg;
    }


}
