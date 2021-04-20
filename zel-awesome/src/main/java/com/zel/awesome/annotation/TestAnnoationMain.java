package com.zel.awesome.annotation;

import java.lang.reflect.Field;

public class TestAnnoationMain {

    @TestAnnotation(min = 6, max = 10, description = "用户名长度在6-10个字符之间")
    private String name;

    @TestAnnotation(min = 6, max =10, description = "密码长度在6-10个字符之间")
    private String pasdword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasdword() {
        return pasdword;
    }

    public void setPasdword(String pasdword) {
        this.pasdword = pasdword;
    }

    public static void valid(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            //获取属性上的 @TestAnnotation 注解
            TestAnnotation test = field.getAnnotation(TestAnnotation.class);
            if(test != null){
                field.setAccessible(true);//设置属性可访问

                if("class java.lang.String".equals(field.getGenericType().toString())){//字符串类型的才判断长度
                    String value = (String)field.get(obj);
                    if(value != null && ((value.length() > test.max()) || value.length() < test.min())){
                        System.out.println(test.description());
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        TestAnnoationMain app = new TestAnnoationMain();
        app.setName("abc");
        app.setPasdword("1234556");

        try {
            valid(app);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
