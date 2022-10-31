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

    private static final int GENERATE_AMMOUNT = 10;

    /**
     * Startmetode for applikasjonen.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException Kaster alle unntak slik at programmet ikke lukker ved en mindre feil.
     */
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
            if(avl.insert(new BTData<>(newValue.intValue()))){
                treeControl.setStatusText("Element inserted");
            } else {
                treeControl.setStatusText("Element is duplicated or not supported");
            }
            avlView.displayTree();
        });
        treeControl.deleteProperty().addListener((observable, oldValue, newValue) -> {
            if(avl.delete(new BTData<>(newValue.intValue()))){
                treeControl.setStatusText("Element deleted");
            } else {
                treeControl.setStatusText("Element not found");
            }
            avlView.displayTree();
        });
        treeControl.findProperty().addListener((observable, oldValue, newValue) -> {
            avlView.clearSelect();
            if(avl.search(new BTData<>(newValue.intValue()))){
                avlView.select(avl.get(new BTData<>(newValue.intValue())));
                treeControl.setStatusText("Element found");
            } else {
                treeControl.setStatusText("Element not found");

            }
        });

        treeControl.getClearBTN().setOnAction(e-> {
            avl.clear();
            avlView.displayTree();
            treeControl.setStatusText("Cleared");
        });
        treeControl.getGenerateBTN().setOnAction(e->{
            int inserted = 0;
            for (int i = 0; i<GENERATE_AMMOUNT; i++){
                if(avl.insert(new BTData<>((int)(Math.random()*100)))){
                    inserted++;
                }
            }
            treeControl.setStatusText("Generated: "+inserted+" of "+GENERATE_AMMOUNT+" requested");
            avlView.displayTree();
        });

    }

    public static void main(String[] args) {
        launch();
    }
}