package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        for(Method m : myClass.getDeclaredMethods()){
            myAdvice.invoke(m, obj, null);
        }

    }
}

class MyAdvice { // 부가기능
    Pattern p = Pattern.compile("a.*");

    boolean matches(Method m) {
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }
    void invoke(Method m, Object obj, Object... args) throws Exception {
        if(m.getAnnotation(Transactional.class)!=null)
            System.out.println("[before]{");
        
        m.invoke(obj, args); // aaa(), aaa2(), bbb() 호출가능

        if(m.getAnnotation(Transactional.class)!=null)
            System.out.println("}[after]");
    }
}

class MyClass { // 핵심기능
    @Transactional
    void aaa() {
        System.out.println("aaa() is called.");
    }
    void aaa2() {
        System.out.println("aaa2() is called.");
    }
    void bbb() {
        System.out.println("bbb() is called.");
    }

}

// AOP란?
// 부가기능 (advice)을 동적(실행중에)으로 추가해주는 기술
// 메서드의 시작 또는 끝에 자동으로 코드(advice)를 추가