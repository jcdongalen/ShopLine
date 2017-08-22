package com.pinas.xburner.shopline.Model;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by john_dongalen on 8/10/2017.
 */

@IgnoreExtraProperties
public class Products {

    private String productID, name, description, category, amount, stock, totalamount;

    public Products(){

    }

    public Products(String ProductID, String Name, String Description, String Category, String Amount){
        this.productID = ProductID;
        this.name = Name;
        this.description = Description;
        this.category = Category;
        this.amount = Amount;
    }

    public Products(String ProductID, String Name, String Description, String Category, String Amount, String Stock){
        this.productID = ProductID;
        this.name = Name;
        this.description = Description;
        this.category = Category;
        this.amount = Amount;
        this.stock = Stock;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
