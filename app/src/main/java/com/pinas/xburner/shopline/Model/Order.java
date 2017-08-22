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
    private String product1;
    private String product1Count;
    private String product2;
    private String product2Count;
    private String product3;
    private String product3Count;
    private String totalAmount;
    private String paymentMode;
    private String shippingAddress;
    private ArrayList<Product> products;

    public Order() {
    }

    public Order(String OrderID, String FullName, String ShippingAddress, String PaymentMode, String TotalAmount, ArrayList<Product> Products) {
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

    public String getProduct1() {
        return this.product1;
    }

    public void setProduct1(String product1) {
        this.product1 = product1;
    }

    public String getProduct1Count() {
        return this.product1Count;
    }

    public void setProduct1Count(String product1Count) {
        this.product1Count = product1Count;
    }

    public String getProduct2() {
        return this.product2;
    }

    public void setProduct2(String product2) {
        this.product2 = product2;
    }

    public String getProduct2Count() {
        return this.product2Count;
    }

    public void setProduct2Count(String product2Count) {
        this.product2Count = product2Count;
    }

    public String getProduct3() {
        return this.product3;
    }

    public void setProduct3(String product3) {
        this.product3 = product3;
    }

    public String getProduct3Count() {
        return this.product3Count;
    }

    public void setProduct3Count(String product3Count) {
        this.product3Count = product3Count;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
