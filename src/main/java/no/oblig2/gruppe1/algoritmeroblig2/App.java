package no.oblig2.gruppe1.algoritmeroblig2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.oblig2.gruppe1.algoritmeroblig2.model.AVLTree;
import no.oblig2.gruppe1.algoritmeroblig2.model.BTData;
import no.oblig2.gruppe1.algoritmeroblig2.view.BTView;
import no.oblig2.gruppe1.algoritmeroblig2.view.TreeControl;

import java.io.IOException;

public class App extends Application {

    private final AVLTree<BTData<Integer>> avl = new AVLTree<>();
    private BTView<BTData<Integer>> avlView;

    @Override
    public void start(Stage stage) throws IOException {

        avlView = new BTView<>(avl);

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
            avl.insert(new BTData<>(newValue.intValue()));
            avlView.displayTree();
        });
        treeControl.deleteProperty().addListener((observable, oldValue, newValue) -> {
            avl.delete(new BTData<>(newValue.intValue()));
            avlView.displayTree();
        });
        treeControl.findProperty().addListener((observable, oldValue, newValue) -> {
            avlView.clearSelect();
            avlView.select(avl.get(new BTData<>(newValue.intValue())));
        });

    }

    public static void main(String[] args) {
        launch();
    }
}