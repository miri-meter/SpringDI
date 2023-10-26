package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car {}
class SportsCar extends Car{}
class Truck extends Car {}

public class Main1 {
    public static void main(String[] args) throws Exception {
        Car car = (Car)getObject("car");
        System.out.println("car = " + car);
    }
    static Object getObject(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty(key));

        return (Car)clazz.newInstance();
    }


    static Car getCar() throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty("car"));

        return (Car)clazz.newInstance();
    }
}
