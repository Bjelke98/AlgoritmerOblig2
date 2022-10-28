package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;

import java.util.ArrayList;

public class ConnectedLine<E> extends CubicCurve {

    /**
     * Class for debugging line
     */
    static class ControlPoint extends Circle {
        ControlPoint(DoubleProperty x, DoubleBinding y){
            setFill(Color.GREEN);
            setStroke(Color.BLACK);
            setRadius(5);
            centerXProperty().bind(x);
            centerYProperty().bind(y);
        }
    }

    public ArrayList<ControlPoint> controlPoints = new ArrayList<>();

    public ConnectedLine(DisplayNode<E> prev, DisplayNode<E> curr){

        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(3);

        startXProperty().bind(prev.centerXProperty());
        startYProperty().bind(prev.centerYProperty());
        // connect to edges
        //startYProperty().bind(prev.centerYProperty().add(prev.radiusProperty()));

        endXProperty().bind(curr.centerXProperty());
        endYProperty().bind(curr.centerYProperty());
        // connect to edges
        // endYProperty().bind(curr.centerYProperty().subtract(curr.radiusProperty()));

        controlX1Property().bind(
                prev.centerXProperty()
        );
        controlY1Property().bind(
                prev.centerYProperty().add(curr.centerYProperty().subtract(prev.centerYProperty()))
        );

        controlX2Property().bind(
                curr.centerXProperty()
        );
        controlY2Property().bind(
                curr.centerYProperty().subtract(curr.centerYProperty().subtract(prev.centerYProperty()))
        );

        controlPoints.add(new ControlPoint(
                prev.centerXProperty(),
                prev.centerYProperty().add(curr.centerYProperty().subtract(prev.centerYProperty()))
        ));
        controlPoints.add(new ControlPoint(
                curr.centerXProperty(),
                curr.centerYProperty().subtract(curr.centerYProperty().subtract(prev.centerYProperty()))
        ));

    }
}
