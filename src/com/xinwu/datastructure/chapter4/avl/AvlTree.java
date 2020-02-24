package com.xinwu.datastructure.chapter4.avl;

/**
 * AVL树是的带自平衡的二叉树，当任意节点的字数高度相差超过1，则需要自平衡
 */
public class AvlTree<E extends Comparable> {
    //静态内部类定义AVL树节点
    private static class AvlNode<E> {
        E element;
        AvlNode<E> left;
        AvlNode<E> right;
        int height; //该节点的高度

        public AvlNode(E element) {
            this(element, null, null);
        }

        public AvlNode(E element, AvlNode<E> left, AvlNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;  //树的高度默认0，定义空树的高度为-1
        }
    }

    //获取节点的高度
    private int height(AvlNode<E> t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode<E> insert(E e, AvlNode<E> t) {
        if (t == null) {
            return new AvlNode<>(e);
        }
        int compareResult = e.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(e, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (e.compareTo(t.left.element) < 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleRotateWithLeftChild(t);
                }
            }
        } else if (compareResult > 0) {
            t.right = insert(e, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (e.compareTo(t.right.element) > 0) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleRotateWithRightChild(t);
                }
            }
        } else {
            //树中已存在要插入的元素，这里什么都不做。
        }
        t.height = Math.max(t.left.height, t.right.height) + 1;
        return t;
    }

    //结合左单旋转的示意图写，easy
    private AvlNode<E> rotateWithLeftChild(AvlNode<E> t) {
        AvlNode<E> k1 = t.left;
        t.left = k1.right;
        k1.right = t;
        k1.height = Math.max(k1.left.height, k1.right.height) + 1;
        t.height = Math.max(t.left.height, t.right.height) + 1;
        return k1;
    }

    private AvlNode<E> rotateWithRightChild(AvlNode<E> t) {
        AvlNode<E> k2 = t.right;
        t.right = k2.left;
        k2.left = t;
        k2.height = Math.max(k2.left.height, k2.right.height) + 1;
        t.height = Math.max(t.left.height, t.right.height) + 1;
        return k2;
    }

    //这边一定要画出图来理解，直接看代码比较晦涩
    private AvlNode<E> doubleRotateWithLeftChild(AvlNode<E> t) {
        t.left = rotateWithRightChild(t.left);
        return rotateWithLeftChild(t);
    }

    private AvlNode<E> doubleRotateWithRightChild(AvlNode<E> t) {
        t.right = rotateWithLeftChild(t.right);
        return rotateWithRightChild(t);
    }

}