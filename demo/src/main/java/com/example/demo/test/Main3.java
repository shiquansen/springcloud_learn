package com.example.demo.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        List<A> aList = new ArrayList<>();
        A a = new A();

        List<B> bList1 = new ArrayList<>();
        B b1 = new B();
        b1.setB("1");
        b1.setBb("11");
        bList1.add(b1);
        B b2 = new B();
        b2.setB("2");
        b2.setBb("22");
        bList1.add(b2);
        a.setBList(bList1);

        A a2 = new A();
        List<B> bList2 = new ArrayList<>();
        B b3 = new B();
        b3.setB("1");
        b3.setBb("11");
        bList2.add(b3);
        B b4 = new B();
        b4.setB("2");
        b4.setBb("22");
        bList2.add(b2);
        a2.setBList(bList2);
        aList.add(a);
        aList.add(a2);


        System.out.println("before:" +aList);
        for(A t : aList){
            List<B> bList = t.getBList();
            for(B b : bList){
                bList.removeIf(bbb -> bbb.getB().equals("1"));
            }
        }
        System.out.println("after:" +aList);

    }
}

@Data
class A{
    private List<B> bList;
}

@Data
class B{
    private String b;
    private String bb;
}
