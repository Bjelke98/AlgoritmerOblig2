package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import no.oblig2.gruppe1.algoritmeroblig2.model.BST;

public class BTView extends Pane {

    enum Status {
        EMPTY("Ingen elementer i dette treet"),
        NOT_EMPTY("Treet har elementer"),
        BLANK("");

        public final String value;
        Status(String value){
            this.value = value;
        }
        public String withSize(int size){
            return value+" ("+size+")";
        }
    }

    private Status status = Status.BLANK;
    private double radius = 15; // Kan endres i GUI
    private double vGap = 50; // Kan endres i GUI

    private BST<Integer> tree = new BST<>();

    public BTView(){
        setStatus(Status.EMPTY);
    }

    public BTView(BST<Integer> tree){
        this.tree = tree;
        setStatus(tree.size()>0?Status.NOT_EMPTY:Status.EMPTY);
        setMinWidth(1200);
        setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void displayTree(){
        getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getMinWidth()/2, vGap, getMinWidth()/4, null);
        }
    }

    private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap, DisplayNode<Integer> prev){
        DisplayNode<Integer> curr = new DisplayNode<>(root.element, x, y, radius);
        if (root.left != null){
            displayTree(root.left, x-hGap, y+vGap, hGap/2, curr);
        }
        if (root.right != null){
            displayTree(root.right, x+hGap, y+vGap, hGap/2, curr);
        }
        if(prev!=null){
            drawConnectingLine(prev, curr);
        }
        getChildren().addAll(curr, curr.centerText);
    }

    private void drawConnectingLine(DisplayNode<Integer> prev, DisplayNode<Integer> curr){
        ConnectedLine<Integer> line = new ConnectedLine<>(prev, curr);
        getChildren().add(line);

        // Viser Cubic control points for debug.
//        getChildren().addAll(line.controlPoints);
    }

    private void setStatus(Status status) {
        this.status = status;
    }
}
