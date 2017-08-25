package com.pinas.xburner.shopline.Model;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by john_dongalen on 8/15/2017.
 */

@IgnoreExtraProperties
public class Order {

    private String orderID;
    private String fullName;
    private String totalAmount;
    private String paymentMode = "";
    private String shippingAddress = "";
    private ArrayList<Products> products;
    private String status = "";

    public Order() {
    }

    public Order(String OrderID, String FullName, String ShippingAddress, String PaymentMode, String TotalAmount, ArrayList<Products> Products) {
        this.orderID = OrderID;
        this.fullName = FullName;
        this.shippingAddress = ShippingAddress;
        this.paymentMode = PaymentMode;
        this.totalAmount = TotalAmount;
        this.products = Products;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
