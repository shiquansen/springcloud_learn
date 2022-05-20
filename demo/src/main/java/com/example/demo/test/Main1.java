package com.example.demo.test;


public class Main1 {
    public static void main(String[] args) {
            new User() {
                public User abc() {
                    return this.getString();
                }
            }.abc();
        }
}


class User{
    User getString(){
        System.out.println("hello");
        return this;
    }
}
