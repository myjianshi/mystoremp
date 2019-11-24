package edu.gyc.histore.hitest;

public abstract class Whore {
    private String name;
    private int price;

    public Whore(Integer price) {

        this.price = price;
    }
    public Whore(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public abstract String sexService();
}
