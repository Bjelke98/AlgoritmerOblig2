package no.oblig2.gruppe1.algoritmeroblig2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import no.oblig2.gruppe1.algoritmeroblig2.model.BST;
import no.oblig2.gruppe1.algoritmeroblig2.view.BTView;

import java.io.IOException;
import java.util.Arrays;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();

        BST<Integer> bst = new BST<>();
        bst.addAll(Arrays.asList(1, 2, 3, 4));

        BTView treeView = new BTView(bst);

        treeView.setPrefWidth(600);
        treeView.setPrefHeight(400);

        treeView.displayTree();
        treeView.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        root.setCenter(treeView);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Algoritmer Oblig2 Gruppe1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}