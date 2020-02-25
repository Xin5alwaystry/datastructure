package com.xinwu.datastructure.chapter5.quadraticProbe;

/**
 * 平法探测法。缺点会造成二次聚集
 */
public class QuadraticProbingHashTable<E> {
    private static final int DEFAULT_TABLE_SIZE = 11; // prime
    private int currentSize;  //目前哈希表已有的元素个数
    private HashEntry<E>[] theArray;

    //静态内部类。定义哈希表项
    private static class HashEntry<E> {
        private E element;
        private boolean isActive; //lazy delete，如果被删除了标记为false

        public HashEntry(E element) {
            this(element, true);
        }

        public HashEntry(E element, boolean isActive) {
            this.element = element;
            this.isActive = isActive;
        }
    }

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocatingArray(size);
    }

    public void insert(E e) {
        int currentPos = findPos(e);
        //元素已经存在于哈希表中，不作操作
        if (isActive(currentPos)) {
            return;
        }
        theArray[currentPos] = new HashEntry<E>(e);
        if (++currentSize > theArray.length / 2) {
            rehash();
        }
    }

    private void rehash() {
        HashEntry<E>[] oldArray = theArray;
        allocatingArray(nextPrime(2 * theArray.length));
        currentSize = 0;

        for (HashEntry<E> which : oldArray) {
            if (which != null && which.isActive) {
                insert(which.element);
            }
        }
    }

    private void remove(E e) {
        int currentPos = findPos(e);
        if (isActive(currentPos)) {
            theArray[currentPos].isActive = false;  //lazy delete
        }
    }

    private boolean contains(E e) {
        int currentPos = findPos(e);
        return isActive(currentPos);
    }
    
    private void makeEmpty() {
        currentSize = 0;
        for (HashEntry<E> eHashEntry : theArray) {
            eHashEntry = null;
        }
    }

    private int findPos(E e) {
        int offset = 1;
        int currentPos = myHash(e);

        HashEntry<E> which = theArray[currentPos];
        while (which != null && !which.element.equals(e)) {
            //按平方探测，着重理解
            currentPos += offset;
            offset += 2;
            if (currentPos >= theArray.length) {
                currentPos -= theArray.length;
            }
        }
        return currentPos;
    }

    private int myHash(E e) {
        int hashVal = e.hashCode();
        hashVal %= theArray.length;
        if (hashVal < 0) {
            hashVal += theArray.length;
        }
        return hashVal;
    }

    private boolean isActive(int pos) {
        return theArray[pos] != null && theArray[pos].isActive;
    }

    private void allocatingArray(int arraySize) {
        if (isPrime(arraySize)) {
            theArray = new HashEntry[arraySize];
        } else {
            theArray = new HashEntry[nextPrime(arraySize)];
        }
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
        return true;
    }

    //找出下一个质数
    private static int nextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }
}