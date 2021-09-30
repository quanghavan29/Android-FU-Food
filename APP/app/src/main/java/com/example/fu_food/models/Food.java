package com.example.fu_food.models;

public class Food {

    private String id;
    private String name;
    private int price;
    private String foodType;
    private String imageUrl;
    private int salesQuantity;
    private float reviewPoint;
    private int numberOfReview;
    private FoodCategory foodCategory;
    private Restaurant restaurant;

    public Food() {
    }

    public Food(String id, String name, int price, String foodType, String imageUrl, int salesQuantity, float reviewPoint, int numberOfReview, FoodCategory foodCategory, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.foodType = foodType;
        this.imageUrl = imageUrl;
        this.salesQuantity = salesQuantity;
        this.reviewPoint = reviewPoint;
        this.numberOfReview = numberOfReview;
        this.foodCategory = foodCategory;
        this.restaurant = restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public float getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(float reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public int getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(int numberofReview) {
        this.numberOfReview = numberofReview;
    }


    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", foodType='" + foodType + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", salesQuantity=" + salesQuantity +
                ", reviewPoint=" + reviewPoint +
                ", numberOfReview=" + numberOfReview +
                ", restaurant=" + restaurant +
                '}';
    }
}
