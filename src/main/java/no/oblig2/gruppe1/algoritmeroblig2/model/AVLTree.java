package no.oblig2.gruppe1.algoritmeroblig2.model;

public class AVLTree<E extends Comparable<E>> extends BST<E>{

    public AVLTree(){
    }

    public AVLTree(E[] objects){
        super(objects);
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e){
        return new AVLTreeNode<>(e);
    }

    public static class AVLTreeNode<E> extends BST.TreeNode<E>{
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
