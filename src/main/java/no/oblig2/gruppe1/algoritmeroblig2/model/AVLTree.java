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

    /**
     * setter inn i treet og så balanserer
     * treet seg med balancePath metoden
     * @param e
     * @return
     */
    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (successful){
            balancePath(e);
        }
        return successful;
    }

    /**
     * leter etter element som blir
     * spurt om
     * @param e
     * @return
     */
    @Override
    public boolean search(E e) {
        return super.search(e);
    }

    /**
     * sletter element som blir spurt om
     * @param e
     * @return
     */
    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = findParent(e);
        TreeNode<E> current;
        // en sjekk for å finne ut om current er root
        if (parent == null){
            current = root;
        } else {
            // sjekker om current ligger i venstre eller høyre i parent og legger den inn
            current = e.compareTo(parent.element) < 0 ? parent.left : parent.right;
        }
        if (current == null) return false;
        balanceAndDelete(current, parent, e);
        size--;
        return true;
    }

    /**
     * sletter elementet og balanserer treet
     * om det er behov
     * @param current
     * @param parent
     * @param e
     */
    private void balanceAndDelete(TreeNode<E> current, TreeNode<E> parent, E e){
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
    }

    /**
     * finner parent til elementet
     * @param e
     * @return
     */
    private TreeNode<E> findParent(E e){
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null){
            if (e.compareTo(current.element)<0){
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
            } else break;
        }
        return parent;
    }

    /**
     * setter igang rekursjon metode for å balansere
     * treet
     * @param e
     */
    private void balancePath(E e){
       balancePathRun(e, root, null);
    }

    /**
     * rekursiv metode for å gå igjennom treet og balansere det
     * sjekker om e er større eller mindre current for og så
     * sende den videre i treet
     * etter den sender videre, så sjekker den om omerådet er balansert
     * @param e
     * @param current
     * @param parent
     */
    private void balancePathRun(E e, TreeNode<E> current, TreeNode<E> parent){
        if (e.compareTo(current.element) < 0){
            balancePathRun(e,  current.left, current);
        } else if (e.compareTo(current.element) > 0){
            balancePathRun(e, current.right, current);
        }
        AVLTreeNode<E> A = (AVLTreeNode<E>) current;
        A.updateHeight();
        AVLTreeNode<E> parentOfA = (AVLTreeNode<E>) parent;
        /* bruker balancefactor for å sjekke hvordan elementene skal
           balanseres */
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

    /**
     * balanserer treet på venstre side
     * @param A
     * @param parent
     */
    private void balanceLL(AVLTreeNode<E> A, AVLTreeNode<E> parent){
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.left;

        insertBalance(A, B, parent);

        A.left = B.right;
        B.right = A;
        A.updateHeight();
        B.updateHeight();
    }

    /**
     * balanserer treet på venstre til høyre
     * @param A
     * @param parent
     */
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

    /**
     * balanserer treet på høre side
     * @param A
     * @param parent
     */
    private void balanceRR(AVLTreeNode<E> A, TreeNode<E> parent){
        AVLTreeNode<E> B = (AVLTreeNode<E>) A.right;

        insertBalance(A, B, parent);

        A.right = B.left;
        B.left = A;
        A.updateHeight();
        B.updateHeight();
        System.out.println();
    }

    /**
     * balanserer treet på høyre til venstre
     * @param A
     * @param parent
     */
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

    /**
     * hjelpemetode siden alle balance metodene gjorde det samme
     * med å sette parent sine barn på riktige sider
     * @param A
     * @param B
     * @param parent
     */
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

        /**
         * returnerer en verdi som viser om treet er balansert eller ikke
         * ¨med å sjekke høyden til elementene
         * @return
         */
        public int balanceFactor(){
            if (this.right == null){
                return -this.height;
            } else if (this.left == null){
                return +this.height;
            } else {
                return ((AVLTreeNode<E>)this.right).height - ((AVLTreeNode<E>)this.left).height;
            }
        }

        /**
         * updaterer høyden etter endringer som skjer under
         * balansering
         */
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
