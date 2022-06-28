package com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {

        System.out.println(String.format("123123", "123"));


//        List<ReviewElement> list = new ArrayList<>();
//        list.add(new ReviewElement("1","task1","role1","user1"));
//        list.add(new ReviewElement("1","task1","role1","user2"));
//        list.add(new ReviewElement("1","task2","role2","user3"));
//        list.add(new ReviewElement("1","task2","role2","user1"));
//        list.add(new ReviewElement("2","task1","role1","user1"));
//        list =  list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o ->
//                o.getLink() + ";" + o.getTask() + ";" + o.getRole()))), ArrayList::new));
//        System.out.println(list.size());

    }
}

@Data
@AllArgsConstructor
class ReviewElement{
    private String link;
    private String task;
    private String role;
    private String user;


}
