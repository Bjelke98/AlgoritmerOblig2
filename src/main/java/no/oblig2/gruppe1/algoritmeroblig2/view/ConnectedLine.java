package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import no.oblig2.gruppe1.algoritmeroblig2.model.CanBeBTData;

import java.util.ArrayList;

/**
 * Klasse for å tegne linje mellom 2 noder.
 * @param <E>
 */
public class ConnectedLine<E extends CanBeBTData> extends CubicCurve {

    /**
     * Klasse for debugging av kontrollpunkt for kurve fremvisning.
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

    /**
     * Liste for å vise frem kontroll punkt.
     */
    public ArrayList<ControlPoint> controlPoints = new ArrayList<>();

    /**
     * Konstruktør for å opprette linje mellom 2 noder.
     * (Notat) Bruker bindings for å automatisk flytte linje etter posisjonen til noden.
     * @param prev forrige node
     * @param curr siste node
     */
    public ConnectedLine(DisplayNode<E> prev, DisplayNode<E> curr){

        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(3);

        startXProperty().bind(prev.centerXProperty());
        startYProperty().bind(prev.centerYProperty());

        endXProperty().bind(curr.centerXProperty());
        endYProperty().bind(curr.centerYProperty());

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
