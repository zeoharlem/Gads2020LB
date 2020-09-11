package com.zeoharlem.gads.gads2020lb;

import android.content.Context;
import android.graphics.Typeface;

public final class ViewAttributes {
    private Typeface mySpartanBlack;
    private Typeface mySpartanLight;
    private Typeface mySpartanRegular;
    private Typeface mySpartanExtraBold;
    private Typeface mySpartanBold;
    private Typeface mySpartanMedium;
    private Typeface mySpartanSemiBold;
    private Context context;

    private static ViewAttributes viewAttributes;

    private ViewAttributes(Context context) {
        mySpartanBlack      = Typeface.createFromAsset(context.getAssets(), "fonts/ls/SpartanMB-Black.otf");
        mySpartanBold       = Typeface.createFromAsset(context.getAssets(), "fonts/ls/LeagueSpartan-Bold.otf");
        mySpartanExtraBold  = Typeface.createFromAsset(context.getAssets(), "fonts/ls/SpartanMB-ExtraBold.otf");
        mySpartanRegular    = Typeface.createFromAsset(context.getAssets(), "fonts/ls/SpartanMB-Regular.otf");
        mySpartanLight      = Typeface.createFromAsset(context.getAssets(), "fonts/ls/SpartanMB-Light.otf");
        mySpartanSemiBold   = Typeface.createFromAsset(context.getAssets(), "fonts/ls/SpartanMB-SemiBold.otf");
        mySpartanMedium     = Typeface.createFromAsset(context.getAssets(), "fonts/ls/SpartanMB-Medium.otf");
    }

    public static ViewAttributes getIntanceViewAttributes(Context context){
        if(viewAttributes == null){
            viewAttributes  = new ViewAttributes(context);
        }
        return viewAttributes;
    }

    public Typeface getMySpartanBlack() {
        return mySpartanBlack;
    }

    public Typeface getMySpartanLight() {
        return mySpartanLight;
    }

    public Typeface getMySpartanRegular() {
        return mySpartanRegular;
    }

    public Typeface getMySpartanExtraBold() {
        return mySpartanExtraBold;
    }

    public Typeface getMySpartanBold() {
        return mySpartanBold;
    }

    public Typeface getMySpartanMedium() {
        return mySpartanMedium;
    }

    public Typeface getMySpartanSemiBold() {
        return mySpartanSemiBold;
    }
}
