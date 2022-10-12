package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class DisplayNode<E> extends Group {
    private Text centerText;
    private Circle circle;
    private E element;

    public DisplayNode(E element, double radius) {
        this.element = element;
        circle = new Circle(radius, radius, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        centerText = new Text(element.toString());
        Bounds textBox = centerText.getLayoutBounds();
        centerText.setX(radius-textBox.getWidth()/2);
        centerText.setY(radius+textBox.getHeight()/2);
        getChildren().addAll(circle, centerText);
    }

    @Override
    public String toString() {
        return getLayoutBounds().toString();
    }
}
