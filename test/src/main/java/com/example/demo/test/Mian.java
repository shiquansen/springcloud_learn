package com.example.demo.test;

public class Mian {



    public static void main(String[] args) {
        Abc abc = new Abc();
    }
}
class Abc{
    static {
        System.out.println("无static");
    }

    static {
        System.out.println("static");
    }

    public Abc(){
        System.out.println("构造方法");
    }
}