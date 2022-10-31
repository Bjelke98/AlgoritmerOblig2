package no.oblig2.gruppe1.algoritmeroblig2.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TreeControl extends HBox implements TextVariables{

    private TextField insertTF = numberInputTextField();
    private TextField deleteTF = numberInputTextField();
    private TextField findTF = numberInputTextField();

    private Button insertBTN = new Button(INSERT_TXT);
    private Button deleteBTN = new Button(DELETE_TXT);
    private Button findBTN = new Button(FIND_TXT);
    private Button clearBTN = new Button(CLEAR_TXT);
    private Button generateBTN = new Button(GENERATE_TXT);

    private Label statusText = new Label();

    // Variabler som brukes til listeners for bruker-input
    private SimpleIntegerProperty insert = new SimpleIntegerProperty();
    private SimpleIntegerProperty delete = new SimpleIntegerProperty();
    private SimpleIntegerProperty find = new SimpleIntegerProperty();

    public TreeControl(){
        setSpacing(10);
        setPadding(new Insets(10));

        insertTF.setOnAction(this::insertAction);
        deleteTF.setOnAction(this::deleteAction);
        findTF.setOnAction(this::findAction);

        insertBTN.setOnAction(this::insertAction);
        deleteBTN.setOnAction(this::deleteAction);
        findBTN.setOnAction(this::findAction);

        getChildren().addAll(
                insertTF, insertBTN,
                deleteTF, deleteBTN,
                findTF, findBTN,
                clearBTN,
                generateBTN,
                statusText
        );
    }

    /**
     * Opretter tekstfelt med 5 columncount
     * @return
     */
    private static TextField numberInputTextField(){
        TextField tf = new TextField();
        tf.setPrefColumnCount(5);
        return tf;
    }


    /**
     * Endrer verdi til "insert" og nullstiller TextField insertTF om input er gyldig Integer
     * @param e
     */
    private void insertAction(ActionEvent e){
        String txt = insertTF.getText();
        if(isInvalidInteger(txt)) return;
        insert.set(Integer.parseInt(txt));
        insertTF.clear();
    }
    /**
     * Endrer verdi til "delete" og nullstiller TextField deleteTF om input er gyldig Integer
     * @param e
     */
    private void deleteAction(ActionEvent e){
        String txt = deleteTF.getText();
        if(isInvalidInteger(txt)) return;
        delete.set(Integer.parseInt(txt));
        deleteTF.clear();
    }
    /**
     * Endrer verdi til "find" og nullstiller TextField findTF om input er gyldig Integer
     * @param e
     */
    private void findAction(ActionEvent e){
        String txt = findTF.getText();
        if(isInvalidInteger(txt)) return;
        find.set(Integer.parseInt(txt));
        findTF.clear();
    }

    /**
     *Returnerer: kan input parses til integer?
     * @param input
     * @return
     */
    private static boolean isInvalidInteger(String input){
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e){
            System.out.println("Invalid Input");
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

    public Button getGenerateBTN() {
        return generateBTN;
    }

    public Button getClearBTN() {
        return clearBTN;
    }

    public void setStatusText(String text) {
        statusText.setText("Status: "+text);
    }
}
