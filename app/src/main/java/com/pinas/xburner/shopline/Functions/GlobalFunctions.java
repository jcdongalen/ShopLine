package com.pinas.xburner.shopline.Functions;

import android.provider.Settings;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by john_dongalen on 8/17/2017.
 */

public class GlobalFunctions {

    public GlobalFunctions(){

    }

    public static String getOrderID(){
        String formattedDate = new SimpleDateFormat("YYMMdd-HHmmss").format(Calendar.getInstance(TimeZone.getDefault()).getTime());

        return "ID00" + formattedDate;
    }

}
