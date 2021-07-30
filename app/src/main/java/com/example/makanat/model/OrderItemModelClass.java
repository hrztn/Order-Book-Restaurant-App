package com.example.makanat.model;

import java.io.Serializable;

//manage, save, retrieve from firebase and send as intent extra
public class OrderItemModelClass implements Serializable {

    private String itemId;
    private String name;
    private String imageUrl;
    private String traderID;
    private String description;
    private String category;
    private String ingredient;
    private String condition;
    private int price;
    private  Double totalStars=0.0;
    private int total_reviews=0;

    public OrderItemModelClass(){

    }

    public OrderItemModelClass(String itemId, String name, String imageUrl, String traderID, String description, String category, String ingredient, String condition, int price) {
        this.itemId = itemId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.traderID = traderID;
        this.description = description;
        this.category = category;
        this.ingredient = ingredient;
        this.condition = condition;
        this.price = price;
    }

    public OrderItemModelClass(String name, String imageUrl, String traderID, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.traderID = traderID;
        this.description = description;
    }

    public OrderItemModelClass(String name, String imageUrl, String traderID, String description, String category, String ingredient, String condition, int price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.traderID = traderID;
        this.description = description;
        this.category = category;
        this.ingredient = ingredient;
        this.condition = condition;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTraderID() {
        return traderID;
    }

    public void setTraderID(String traderID) {
        this.traderID = traderID;
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(Double totalStars) {
        this.totalStars = totalStars;
    }

    public int getTotal_reviews() {
        return total_reviews;
    }

    public void setTotal_reviews(int total_reviews) {
        this.total_reviews = total_reviews;
    }
}
