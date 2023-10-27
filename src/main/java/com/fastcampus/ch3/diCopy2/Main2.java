package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car {}
class SportsCar extends Car{}
class Truck extends Car {}
class Engine {}

class AppContext{
    Map map ; //　객체저장소

    AppContext() {
        map = new HashMap();
        map.put("car", new SportsCar());
        map.put("Engine", new Engine());
    }

    Object getBean(String key) {
        return map.get(key);
    }
}

public class Main2 {
    public static void main(String[] args) throws Exception {
        Car car = (Car)getObject("car");
        Engine engine = (Engine) getObject("engine");
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
