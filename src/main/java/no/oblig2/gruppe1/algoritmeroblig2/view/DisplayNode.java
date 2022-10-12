package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Text;

public class DisplayNode<E> extends Circle {

    public Text centerText;
    private E element;

    public DisplayNode(E element, double x, double y, double radius) {
        super(x, y, radius);
        this.element = element;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);

        centerText = new Text(element.toString());
        Bounds textBox = centerText.getLayoutBounds();

        // Sørger for at text alltid er i center av sirkelen.
        centerText.xProperty().bind(this.centerXProperty().subtract(textBox.getWidth()/2));
        centerText.yProperty().bind(this.centerYProperty().add(textBox.getHeight()/3));

        // Lar bruker dra noder sidelengs (Satt på text også slik at bruker får dratt ved klikk på text inne i sirkelen)
        setOnMouseDragged(this::dragCircle);
        centerText.setOnMouseDragged(this::dragCircle);
    }
    private void dragCircle(MouseEvent e){
        setCenterX(e.getX());
    }
}
