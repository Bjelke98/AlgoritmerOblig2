package no.oblig2.gruppe1.algoritmeroblig2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import no.oblig2.gruppe1.algoritmeroblig2.model.AVLTree;
import no.oblig2.gruppe1.algoritmeroblig2.model.BST;
import no.oblig2.gruppe1.algoritmeroblig2.view.BTView;
import no.oblig2.gruppe1.algoritmeroblig2.view.TreeControl;

import java.io.IOException;
import java.util.Arrays;

public class App extends Application {

    private final AVLTree<Integer> avl = new AVLTree<>();
    BTView avlView;

    @Override
    public void start(Stage stage) throws IOException {

        //avl.addAll(Arrays.asList(1));
        avlView = new BTView(avl);

        TreeControl treeControl = new TreeControl();

        BorderPane root = new BorderPane();
        root.setCenter(avlView);
        root.setTop(treeControl);

        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("Algoritmer Oblig2 Gruppe1");
        stage.setScene(scene);
        stage.show();

        avlView.displayTree();

        treeControl.insertProperty().addListener((observable, oldValue, newValue) -> {
            avl.insert(newValue.intValue());
            avlView.displayTree();
        });
        treeControl.deleteProperty().addListener((observable, oldValue, newValue) -> {
            avl.delete(newValue.intValue());
            avlView.displayTree();
        });
        treeControl.findProperty().addListener((observable, oldValue, newValue) -> {

            avlView.displayTree();
        });

    }

    public static void main(String[] args) {
        launch();
    }
}