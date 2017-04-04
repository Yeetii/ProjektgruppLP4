package com.journaldev.customlistview;



public class Accomondation {

    String address;
    HouseType houseType;
    int price;
    double area;
    int searchers;
    int image;



    public Accomondation(String address, HouseType houseType, int price, double area, int searchers, int image) {
        this.address=address;
        this.houseType=houseType;
        this.price=price;
        this.area=area;
        this.searchers=searchers;
        this.image=image;
    }


    public String getAddress() {
        return address;
    }


    public String getHouseType() {
        return houseType.toString();
    }


    public String getPrice() {
        return Integer.toString(price);
    }


    public String getArea() {
        return Double.toString(area);
    }

    public String getSearchers(){
        return Integer.toString(searchers);
    }

    public int getImage(){
        return image;
    }
}
