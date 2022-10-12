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
        bst.addAll(Arrays.asList(5, 3, 4, 2, 1, 8, 7, 9, 6));

        BTView treeView = new BTView(bst);
        treeView.setMinWidth(600);
        treeView.setMinHeight(400);

        treeView.displayTree();
        treeView.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));

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