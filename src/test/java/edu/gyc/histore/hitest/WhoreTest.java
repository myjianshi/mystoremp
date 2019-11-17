package edu.gyc.histore.hitest;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WhoreTest {

    @Test
    void sexService() {


        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            int n= random.nextInt(1000);
            System.out.println("You pay "+n+"$.");
            if(WhoreUtil.makeLove(n)!=null)
                System.out.println(""+WhoreUtil.makeLove(n).sexService());
        }





    }

    @Test
    void hireflect() {
        String input = JOptionPane.showInputDialog("Input full class name:");
        try{
            Class cla = Class.forName(input);
            Method[] methods=cla.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.toString());
            }
            System.out.println("*********************");

            Field[] fields=cla.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.toString());
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Test
    void fucklzl() {
        Class whore=Stewardess.class;
        try {
            Constructor constructor = whore.getConstructor(String.class, Integer.class);
            Object o = constructor.newInstance("小娼妇林志玲", 38);
            Method method = whore.getMethod("sexService");
            System.out.println( method.invoke(o));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}