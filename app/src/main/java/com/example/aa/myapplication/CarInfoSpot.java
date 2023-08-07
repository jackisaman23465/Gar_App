package com.example.aa.myapplication;

public class CarInfoSpot {
    private int id = 0;
    private String carname;
    private String brand;
    private String model;
    private String fuelconsumption;
    private String enginedisplacement;
    private String price;

    public CarInfoSpot(String carname, String brand, String model, String fuelconsumption, String enginedisplacement, String price){
        this(0, carname, brand, model, fuelconsumption, enginedisplacement, price);
    }

    public CarInfoSpot(int id, String carname, String brand, String model, String fuelconsumption, String enginedisplacement, String price) {
        this.id = id;
        this.carname = carname;
        this.brand = brand;
        this.model = model;
        this.fuelconsumption = fuelconsumption;
        this.enginedisplacement = enginedisplacement;
        this.price = price;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setCarname(String name) {
        this.carname = carname;
    }

    public String getCarname(){
        return carname;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel(){
        return model;
    }

    public void setFuelconsumption(String fuelconsumption) {
        this.fuelconsumption = fuelconsumption;
    }

    public String getFuelconsumption(){
        return fuelconsumption;
    }

    public void setEnginedisplacement(String enginedisplacement) {
        this.enginedisplacement = enginedisplacement;
    }

    public String getEnginedisplacement(){
        return enginedisplacement;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice(){
        return price;
    }

}
