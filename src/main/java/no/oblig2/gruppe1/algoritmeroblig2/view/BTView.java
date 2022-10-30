package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import no.oblig2.gruppe1.algoritmeroblig2.model.BST;
import no.oblig2.gruppe1.algoritmeroblig2.model.CanBeBTData;

/**
 * View klasse for fremvisning av selvet treet.
 * Klassen har ansvar for å tegne ut treet riktig.
 * @param <E> Krever å implementere Comparable for å kunne behandle data i et binær tre og krever CanBeBTData for å kunne sette bakgrunnsfarge ved søk fuksjon.
 */
public class BTView<E extends Comparable<E> & CanBeBTData> extends Pane {

    private final double radius = 15;
    private final double vGap = 50;
    private final BST<E> tree;

    private E selected;

    /**
     * Konstruktør for å opprette panelet som treet vises i. Krever å ta inn et binær tre BST eller andre utvidelser av BST.
     * @param tree Binærtre som skal visualiseres.
     */
    public BTView(BST<E> tree){
        this.tree = tree;
        setMinWidth(1200);
        setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Metode for å tegne ut alle noder i treet.
     */
    public void displayTree(){
        getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getMinWidth()/2, vGap, getMinWidth()/4, null);
        }
    }

    private void displayTree(BST.TreeNode<E> root, double x, double y, double hGap, DisplayNode<E> prev){
        DisplayNode<E> curr = new DisplayNode<>(root.element, x, y, radius);
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

    private void drawConnectingLine(DisplayNode<E> prev, DisplayNode<E> curr){
        ConnectedLine<E> line = new ConnectedLine<>(prev, curr);
        getChildren().add(line);

        // Viser Cubic control points for debug.
        // getChildren().addAll(line.controlPoints);
    }

    /**
     * Velger node som skal settes bakgrunn på (Eksempel ved find)
     * @param selected Node som skal utheves.
     */
    public void select(E selected) {
        this.selected = selected;
        selected.setSelected(true);
    }

    /**
     * Fjerner bakgrunn på node som sist ble vist.
     */
    public void clearSelect() {
        if(selected!=null)
            selected.setSelected(false);
    }
}
