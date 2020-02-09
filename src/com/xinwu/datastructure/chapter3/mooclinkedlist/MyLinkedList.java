package com.xinwu.datastructure.chapter3.mooclinkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {

    private static class Node<E> {
        public E element;
        public Node<E> prev;
        public Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> beginMarker;
    private Node<E> endMarker;
    private int size;
    private int modCount;

    public MyLinkedList() {
        doClear();
    }

    private void doClear() {
        //空链表，画图一目了然
        beginMarker = new Node<E>(null, null, null);
        endMarker = new Node<E>(null, beginMarker, null);
        beginMarker.next = endMarker; //为什么不在初始化的时候将头节点的next指定为尾节点？应该是因为那个时候尾节点也还未new。

        size = 0;
        modCount++;
    }

    //暴露给外界使用的清空列表方法
    public void clear() {
        doClear();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(E addVal) {
        addBefore(endMarker, addVal);
        return true;
    }

    public void add(int idx, E addVal) {
        addBefore(getNode(idx), addVal);
    }

    public E get(int idx) {
        return getNode(idx).element;
    }

    public E set(int idx, E setVal) {
        Node<E> node = getNode(idx);
        E oldVal = node.element;
        node.element = setVal;
        return oldVal;
    }

    private Node<E> getNode(int idx, int lower, int upper) {
        Node<E> node;
        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException();
        }
        int mid = (lower + upper) / 2;
        if (idx < mid) {
            node = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                //i=0时，循环体中将node指向第2（idx=i+1）个节点，因此最后一个循环体i=idx-1时，node指向的节点下标为idx。
                node = node.next;
            }
        } else {
            node = endMarker;
            for (int i = size; i > idx; i--) {
                //i=size时，循环体中将node指向最后一个节点（可以理解成下标为size-1），因此最后一个循环体中i=idx+1，node指向下标为idx的节点。
                node = node.prev;
            }
        }
        return node;
    }


    //在指定节点的前面添加新节点，结合双链表图理解
    private void addBefore(Node<E> p, E addVal) {
        Node<E> addNode = new Node<>(addVal, p.prev, p);
        p.prev.next = addNode;
        p.prev = addNode;

        size++;
        modCount++;
    }

    public E remove(int idx) {
        return remove(getNode(idx));
    }

    //删除指定节点，修改该指定节点的前驱节点和后继节点的引用
    private E remove(Node<E> removeNode) {
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        size--;
        modCount++;
        return removeNode.element;
    }

    private Node<E> getNode(int idx) {
        return getNode(idx, 0, size - 1);
    }

    private class MyLinkedListIterator implements Iterator<E> {
        private Node<E> current = beginMarker.next;
        private int expectModCount = modCount;
        private boolean okToRemove = false;


        @Override
        public boolean hasNext() {
            //如果相对当前节点的下一个节点不是尾节点，则有下一个节点。
            return current != endMarker;
        }

        @Override
        public E next() {
            if (modCount != expectModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E nowVal = current.element;
            current = current.next;
            okToRemove = true;
            return nowVal;
        }

        @Override
        public void remove() {
            if (modCount != expectModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectModCount++;
            okToRemove = false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }
}
