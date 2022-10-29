package no.oblig2.gruppe1.algoritmeroblig2.model;


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
            balancePath(e);
        }
        return successful;
    }

    @Override
    public boolean search(E e) {
        return super.search(e);
    }
    @Override
    public boolean delete(E e) {
        if(root == null) return false;

        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null){
            if (e.compareTo(current.element)<0){
                parent = current;
                current = current.right;
            } else if (e.compareTo(current.element) > 0){
                parent = current;
                current = current.left;
            }else {
                break;
            }
        }
        //findNode(current, parent, e);
        if (current == null) return false;

        if (current.left == null){
            if (parent == null){
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0){
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
                balancePath(parent.element);
            }
        } else  {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;

                balancePath(parentOfRightMost.element);
            }
        }
        size--;
        return true;
    }

    private void findNode(TreeNode<E> current, TreeNode<E> parent, E node){
        if (current != null){
            if (node.compareTo(current.element)<0){
                parent = current;
                current = current.right;
            } else if (node.compareTo(current.element) > 0){
                parent = current;
                current = current.left;
            }
            findNode(current, parent, node);
        }
    }


    private void balancePath(E e){
       balancePathRun(e, root, null);
    }

    private void balancePathRun(E e, TreeNode<E> current, TreeNode<E> parent){
        if (e.compareTo(current.element) < 0){
            balancePathRun(e,  current.left, current);
        } else if (e.compareTo(current.element) > 0){
            balancePathRun(e, current.right, current);
        }
        AVLTreeNode<E> A = (AVLTreeNode<E>) current;
        A.updateHeight();
        AVLTreeNode<E> parentOfA = (AVLTreeNode<E>) parent;

        switch (A.balanceFactor()){
            case -2 -> {
                if (((AVLTreeNode<E>)A.left).balanceFactor() <= 0){
                    balanceLL(A, parentOfA);
                } else {
                    balanceLR(A, parentOfA);
                }
            }
            case +2 -> {
                if (((AVLTreeNode<E>)A.right).balanceFactor() >= 0){
                    balanceRR(A, parentOfA);
                } else {
                    balanceRL(A, parentOfA);
                }
            }
        }
    }
    private void balanceLL(AVLTreeNode<E> A, AVLTreeNode<E> parent){
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.left;

        insertBalance(A, B, parent);

        A.left = B.right;
        B.right = A;
        A.updateHeight();
        B.updateHeight();
    }
    private void balanceLR(AVLTreeNode<E> A, TreeNode<E> parent){
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.left;
        AVLTreeNode<E> C = (AVLTreeNode<E>) B.right;

        insertBalance(A, C, parent);

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        A.updateHeight();
        B.updateHeight();
        C.updateHeight();
    }
    private void balanceRR(AVLTreeNode<E> A, TreeNode<E> parent){
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.right;

        insertBalance(A, B, parent);

        A.right = B.left;
        B.left = A;
        A.updateHeight();
        B.updateHeight();
    }
    private void balanceRL(TreeNode<E> A, TreeNode<E> parent){
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.right;
        AVLTreeNode<E> C = (AVLTreeNode<E>) B.left;

        insertBalance(A, C, parent);

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;
        ((AVLTreeNode<E>) A).updateHeight();
        B.updateHeight();
        C.updateHeight();
    }
    private void insertBalance(TreeNode<E> A , TreeNode<E> B, TreeNode<E> parent){
        if (A == root){
            root = B;
        } else {
            if (parent.left == A){
                parent.left = B;
            } else {
                parent.right = B;
            }
        }
    }




    private static class AVLTreeNode<E> extends BST.TreeNode<E>{
        protected int height = 0;

        public AVLTreeNode(){}

        public int balanceFactor(){
            if (this.right == null){
                return -this.height;
            } else if (this.left == null){
                return +this.height;
            } else {
                return ((AVLTreeNode<E>)this.right).height - ((AVLTreeNode<E>)this.left).height;
            }
        }

        private void updateHeight(){
            if (this.left == null && this.right == null){
                this.height = 0;
            } else if (this.left == null){
                this.height = 1 + ((AVLTreeNode<E>)this.right).height;
            } else if (this.right == null){
                this.height = 1 + ((AVLTreeNode<E>)this.left).height;
            } else{
                this.height = 1 + Math.max(((AVLTreeNode<E>)this.right).height, ((AVLTreeNode<E>)this.left).height);
            }
        }
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
