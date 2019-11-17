package edu.gyc.histore.hitest;

import lombok.Getter;


public class Girl extends Whore {

    public Girl(Integer price) {
        super("欣小萌", price);
    }

    @Override
    public String sexService() {
        return "萝莉"+getName()+"的服务喜欢吗？";
    }
}
