package com.baizhi.factory;

public class CreateBean {

    public static Object getBean(String className){
        Class<?> clazz = null;
        Object o = null;
        try {

            clazz = Class.forName(className);
            o = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;

    }
}
