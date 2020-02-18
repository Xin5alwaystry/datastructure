package com.xinwu.datastructure.chapter4.binarysearch;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(6);
        tree.insert(2);
        tree.insert(7);
        tree.printTree();
    }
}