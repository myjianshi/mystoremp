package edu.gyc.histore.hitest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WhoreUtil {
    public static Whore makeLove(Integer price ) {
        Class clz=null;
        if (price>=900) {
            clz=Stewardess.class;


        }else  if(price>=600){
            clz=Ol.class;


        } else if (price>=300) {
            clz=Girl.class;


        }else {
            System.out.println("小伙子你想多了，便宜无好货！");
            return null;
        }

        try {
        Constructor<Whore> whoreConstructor=  clz.getConstructor( Integer.class);
            Whore whore=whoreConstructor.newInstance(price);
            return whore;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
