package no.oblig2.gruppe1.algoritmeroblig2.model;

import java.util.ArrayList;

public class AVLTree<E extends Comparable<E>> extends BST<E>{

    public AVLTree(){}

    public AVLTree(E[] objects){
        super(objects);
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e){
        return new AVLTreeNode<>(e);
    }


    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (successful){
            balancePath(e, (E) root, null);
        }
        return successful;
    }

    @Override
    public boolean search(E e) {
        return super.search(e);
    }

    @Override
    public boolean delete(E e) {
        return super.delete(e);
    }


    private void balancePath(E e, E current, E parent){
        if (e.compareTo(current) < 0){
            balancePath(e, (E)((AVLTreeNode) current).left, current);
        } else if (e.compareTo(current) > 0){
            balancePath(e, (E)((AVLTreeNode) current).right, current);
        }
        AVLTreeNode<E> A = (AVLTreeNode<E>) current;
        updateHeight(A);
        AVLTreeNode<E> parentOfA = (AVLTreeNode<E>) parent;

        switch (balanceFactor(A)){
            case -2 -> {
                if (balanceFactor((AVLTreeNode<E>) A.left) <= 0){
                    balanceLL(A, parentOfA);
                } else {
                    balanceLR(A, parentOfA);
                }
            }
            case +2 -> {
                if (balanceFactor((AVLTreeNode<E>) A.right) <= 0){
                    balanceRR(A, parentOfA);
                } else {
                    balanceRL(A, parentOfA);
                }
            }
        }


    }
    private void balanceLL(TreeNode<E> A, TreeNode<E> parent){
        TreeNode<E> B = A.left;

        if (A.equals(root)){
            root = B;
        } else {
            if (parent.left.equals(A)){
                parent.left = B;
            } else {
                parent.right = B;
            }
        }

        A.left = B.right;
        B.right = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);

    }


    private void balanceLR(TreeNode<E> e, TreeNode<E> parent){

    }
    private void balanceRR(TreeNode<E> e, TreeNode<E> parent){

    }
    private void balanceRL(TreeNode<E> e, TreeNode<E> parent){

    }

    private void updateHeight(AVLTreeNode<E> node){
        if (node.left == null && node.right == null){
            node.height = 0;
        } else if (node.left == null){
            node.height = 1 + ((AVLTreeNode)node.right).height;
        } else if (node.right == null){
            node.height = 1 + ((AVLTreeNode)node.left).height;
        } else{
            node.height = 1 + ((AVLTreeNode)node.left).height + ((AVLTreeNode)node.right).height;
        }
    }

    private int balanceFactor(AVLTreeNode<E> node){
        if (node.right == null){
            return -node.height;
        } else if (node.left == null){
            return node.height;
        } else {
           return ((AVLTreeNode)node.right).height - ((AVLTreeNode)node.left).height;
        }
    }

    private static class AVLTreeNode<E> extends BST.TreeNode<E>{
        protected int height = 0;

        public AVLTreeNode(){}

        public AVLTreeNode(E e){
            super(e);
        }

        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
    }
}
