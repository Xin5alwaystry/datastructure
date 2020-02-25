package com.xinwu.datastructure.chapter5.separateChaining;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<E> {
    private static final int DEFAULT_TABLE_SIZE = 101;  //质数

    private int currentSize;

    //用固定长度的数组实现，每个数组的元素中放置一个list
    private List<E>[] theLists;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    private int myHash(E e) {
        int hashVal = e.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
    }

    /**
     * 当currentSize大于theLists的大小时，进行的再散列。否者列表的装的太满，将会影响效率。
     */
    private void reHash() {
        //保留旧的哈希表数据
        List<E>[] oldLists = theLists;
        theLists = new LinkedList[2 * theLists.length]; //不可以创建泛型数组
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
        //将旧哈希表的数据，插入新哈希表中
        for (List<E> oldList : oldLists) {
            for (E e : oldList) {
                insert(e);
            }
        }
    }

    public void insert(E e) {
        List<E> whichList = theLists[myHash(e)];
        if (!whichList.contains(e)) {
            whichList.add(e);
            currentSize++;
            if (currentSize > theLists.length) {
                reHash();
            }
        }
    }

    public void remove(E e) {
        List<E> whichList = theLists[myHash(e)];
        if (whichList.contains(e)) {
            whichList.remove(e);
            currentSize--;
        }
    }

    public boolean contains(E e) {
        List<E> whichList = theLists[myHash(e)];
        return whichList.contains(e);
    }

    public void makeEmpty() {
        for (List<E> theList : theLists) {
            theList.clear();
        }
        currentSize = 0;
    }

    //判断是否为质数
    private static boolean isPrime(int num) {
        if (num <= 3) {
            return num > 1;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return false;
    }

    //找出下一个质数
    private static int nextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }
}