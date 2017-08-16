package com.pinas.xburner.shopline.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.pinas.xburner.shopline.Model.FontManager;
import com.pinas.xburner.shopline.R;

/**
 * Created by john_dongalen on 8/16/2017.
 */

public class mAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView {

    public mAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public mAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs){
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextFont);
            setTypeface(new FontManager(context).getTypeFace(Integer.parseInt(a.getString(R.styleable.TextFont_setFont))));
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
