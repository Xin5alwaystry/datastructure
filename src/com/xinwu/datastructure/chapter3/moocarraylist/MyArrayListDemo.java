package com.xinwu.datastructure.chapter3.moocarraylist;

import java.util.Iterator;

public class MyArrayListDemo {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        Iterator<Integer> iterator = myArrayList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(myArrayList.size());
    }
}
