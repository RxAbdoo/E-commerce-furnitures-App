package com.example.furnitures2;

public class DataHolder {
    public static  DataHolder dataHolder = null;
    private  String name;
    private String image;
    private String price;
    public static DataHolder getInstance()
    {
        if(dataHolder==null)
        {
            dataHolder = new DataHolder();

        }
        return  dataHolder;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
