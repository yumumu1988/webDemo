package com.ymm.web.model;

public class Person {
    private String name;
    private int age;
    private boolean gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "[" +
                "name: " + name + "; " +
                "age: " + age + "; " +
                "male: " + gender + "; " +
                "]";
    }
}
