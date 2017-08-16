package com.pinas.xburner.shopline.Custom;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

import com.pinas.xburner.shopline.R;

/**
 * Created by john_dongalen on 8/16/2017.
 */

public class mProgressDialog extends Dialog {


    public mProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.custom_progress_dialog);
    }

    public mProgressDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected mProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
