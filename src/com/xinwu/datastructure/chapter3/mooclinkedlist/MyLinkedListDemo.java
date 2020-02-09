package com.xinwu.datastructure.chapter3.mooclinkedlist;

import java.util.Iterator;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);

        myLinkedList.add(2, 5);

        Iterator<Integer> iterator = myLinkedList.iterator();

        //如果在使用迭代器之前对列表进行了结构上的更改，迭代器则失效
        //因此尽可能不要提前创建迭代器。
        myLinkedList.remove(0);

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
