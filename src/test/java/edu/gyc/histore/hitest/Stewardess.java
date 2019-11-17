package edu.gyc.histore.hitest;

public class Stewardess extends Whore {
    public Stewardess(Integer price) {
        super("梁婷", price);
    }

    public Stewardess(String name, Integer price) {
        super(name, price);
    }

    @Override
    public String sexService() {
        return "Stewardess "+getName()+"空姐用制服诱惑你空寂的心灵！";
    }
}
