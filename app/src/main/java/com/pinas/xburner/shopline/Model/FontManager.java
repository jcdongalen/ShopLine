package com.pinas.xburner.shopline.Model;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by john_dongalen on 8/4/2017.
 */

public class FontManager {

    private Context mContext;
    public static int
            BLACK_CHANCERY = 0, CAVIAR_DREAMS_BOLD = 1, CAVIAR_DREAMS_BOLD_ITALIC = 2, CAVIAR_DREAMS_ITALIC = 3, CAVIAR_DREAMS_REGULAR = 4, COMFORTAA_BOLD = 5, COMFORTAA_LIGHT = 6, COMFORTAA_REGULAR = 7, FRAGMENTCORE = 8, GRUTCH_SHADED = 9, ROMAN_SD = 10;

    private String[] fonts = {"black_chancery.TTF", "caviar_dreams_bold.ttf", "caviar_dreams_bold_italic.ttf", "caviar_dreams_italic.ttf", "caviar_dreams_regular.ttf", "comfortaa_bold.ttf", "comfortaa_light.ttf", "comfortaa_regular.ttf", "fragmentcore.otf", "grutch_shaded.ttf", "roman_sd.ttf"};

    public FontManager(Context context) {
        this.mContext = context;
    }

    public Typeface getTypeFace(int FontType) {
        return Typeface.createFromAsset(mContext.getAssets(), "font/" + fonts[FontType]);
    }

}
