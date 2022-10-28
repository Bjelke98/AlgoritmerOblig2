package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TreeControl extends HBox implements TextVariables{

    private TextField insertTF = numberInputTextField();
    private TextField deleteTF = numberInputTextField();
    private TextField findTF = numberInputTextField();

    private Button insertBTN = new Button(INSERT_TXT);
    private Button deleteBTN = new Button(DELETE_TXT);
    private Button findBTN = new Button(FIND_TXT);

    private SimpleIntegerProperty insert = new SimpleIntegerProperty();
    private SimpleIntegerProperty delete = new SimpleIntegerProperty();
    private SimpleIntegerProperty find = new SimpleIntegerProperty();

    public TreeControl(){
        setSpacing(10);
        setPadding(new Insets(10));

        insertTF.setOnAction(this::insertAction);
        deleteBTN.setOnAction(this::insertAction);
        findBTN.setOnAction(this::insertAction);

        insertBTN.setOnAction(this::insertAction);
        deleteBTN.setOnAction(this::deleteAction);
        findBTN.setOnAction(this::findAction);
        getChildren().addAll(
                insertTF, insertBTN,
                deleteTF, deleteBTN,
                findTF, findBTN
        );
    }

    private static TextField numberInputTextField(){
        TextField tf = new TextField();
        tf.setPrefColumnCount(5);
        return tf;
    }



    private void insertAction(ActionEvent e){
        String txt = insertTF.getText();
        if(isInvalidInteger(txt)) return;
        insert.set(Integer.parseInt(txt));
        insertTF.clear();
    }
    private void deleteAction(ActionEvent e){
        String txt = deleteTF.getText();
        if(isInvalidInteger(txt)) return;
        delete.set(Integer.parseInt(txt));
        deleteTF.clear();
    }
    private void findAction(ActionEvent e){
        String txt = deleteTF.getText();
        if(isInvalidInteger(txt)) return;
        find.set(Integer.parseInt(txt));
        findTF.clear();
    }

    private static boolean isInvalidInteger(String input){
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e){
            return true;
        }
    }



    public int getInsert() {
        return insert.get();
    }

    public SimpleIntegerProperty insertProperty() {
        return insert;
    }

    public int getDelete() {
        return delete.get();
    }

    public SimpleIntegerProperty deleteProperty() {
        return delete;
    }

    public int getFind() {
        return find.get();
    }

    public SimpleIntegerProperty findProperty() {
        return find;
    }
}
