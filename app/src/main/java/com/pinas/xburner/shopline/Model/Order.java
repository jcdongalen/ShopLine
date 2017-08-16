package com.pinas.xburner.shopline.Model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by john_dongalen on 8/15/2017.
 */

@IgnoreExtraProperties
public class Order {

    String OrderID, FullName, Product1, Porduct1Count, Product1Amount, Product2, Product2Count, Product2Amount,Product3, Product3Count, Product3Amount, TotalAmount, PaymentMode, ShippingAddress;

    public Order(){

    }

}
