package com.xinwu.datastructure.chapter3;

import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private E[] items;

    public MyArrayList() {
        doClear();
    }

    //暴露给外界使用
    public void clear() {
        doClear();
    }

    private void doClear() {
        size = 0;
        this.ensureCapacity(DEFAULT_CAPACITY);
    }

    private void ensureCapacity(int defaultCapacity) {
        if (defaultCapacity < size) {
            return;
        }
        E[] old = items;
        items = (E[]) new Object[defaultCapacity];  //不可直接new一个的泛型数据，因此使用此方式
        for (int i = 0; i < size; i++) {
            items[i] = old[i];
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void trimTosize() {
        ensureCapacity(size());
    }

    public E get(int i) {
        checkIndex(i);
        return items[i];
    }

    public E set(int i, E newVal) {
        checkIndex(i);
        E old = items[i];
        items[i] = newVal;
        return old;
    }

    public boolean add(E addVal) {
        add(size(), addVal);
        return true;
    }

    public void add(int i, E addVal) {
        if (items.length == size()) {
            //如果数组满了，扩容之
            ensureCapacity(size() * 2 + 1);
        }
        if (size() - i >= 0) System.arraycopy(items, i, items, i + 1, size() - i);
        items[i] = addVal;
        size++;
    }

    public E remove(int i) {
        E removeVal = items[i];
        if (size() - i >= 0) System.arraycopy(items, i + 1, items, i, size() - i);
        size--;
        return removeVal;
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            return items[current++];
        }

        @Override
        public void remove() {
            //避免歧义
            MyArrayList.this.remove(--current);
        }
    }
}
