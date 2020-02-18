package com.xinwu.datastructure.chapter4.binarysearch;


public class BinarySearchTree<E extends Comparable> {
    //节点嵌套类
    private static class BinaryNode<E> {
        private E element;
        BinaryNode<E> left;  //左儿子
        BinaryNode<E> right;  //右儿子

        public BinaryNode(E element) {
            this(element, null, null);
        }

        BinaryNode(E element, BinaryNode<E> left, BinaryNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    //唯一的成员变量，表示树的根；空树则root=null
    private BinaryNode<E> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(E x) {
        return contains(x, root);
    }

    public E findMin() {
        return findMin(root).element;
    }

    public E findMax() {
        return findMax(root).element;
    }

    public void insert(E e) {
        root = insert(e, root);
    }

    public void remove(E e) {
        root = remove(e, root);
    }

    public void printTree() {
        printTree(root);
    }

    /**
     * 私有方法。判断树t是否包含e项
     */
    private boolean contains(E e, BinaryNode<E> t) {
        //关键点，首先要判断t是否为空，否者后面会出现空指针异常
        if (t == null) {
            return false;
        }

        int compareResult = e.compareTo(t.element);
        if (compareResult < 0) {
            return contains(e, t.left);
        } else if (compareResult > 0) {
            return contains(e, t.right);
        } else {
            return true;
        }
    }

    //找出树中最小的项，用递归方式
    private BinaryNode<E> findMin(BinaryNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    //找出树中最大的项，用非递归方式
    private BinaryNode<E> findMax(BinaryNode<E> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private BinaryNode<E> insert(E e, BinaryNode<E> t) {
        if (t == null) {
            return new BinaryNode<>(e, null, null);
        }
        int compareResult = e.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(e, t.left);
        } else if (compareResult > 0) {
            t.right = insert(e, t.right);
        } else {
            //该情况下即树中已存在该项，根据需求做相应处理即可
        }
        return t;
    }

    private BinaryNode<E> remove(E e, BinaryNode<E> t) {
        if (t == null) {
            return t;
        }

        int compareResult = e.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(e, t.left);
        } else if (compareResult > 0) {
            t.right = remove(e, t.right);
        } else if (t.left != null && t.right != null) {
            //当前节点就是要删除的节点，同时该节点有两个孩子的情况
            //通常做法：将该节点的值用它右子树的最小值替换，同时对右子树最小值递归删除
            t.element = findMin(t.right).element;
            remove(t.element, t.right);
        } else {
            //当前节点就是要删除的节点，同时为叶子节点或只有单个孩子的节点
            t = t.left != null ? t.left : t.right;
        }
        return t;
    }

    //输出二叉查找树（中序）
    private void printTree(BinaryNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
}