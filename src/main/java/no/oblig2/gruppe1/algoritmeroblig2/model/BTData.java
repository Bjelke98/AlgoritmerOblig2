package no.oblig2.gruppe1.algoritmeroblig2.model;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Data klasse som kreves for å sette treet inn i en visuell visning.
 * @param <E>
 */
public class BTData<E extends Comparable<E>> implements Comparable<BTData<E>>, CanBeBTData{

    private final SimpleBooleanProperty selected = new SimpleBooleanProperty();
    private final E value;

    /**
     * Konstruktør for dataklasse
     * @param value verdi
     */
    public BTData(E value){
        this.value = value;
        selected.set(false);
    }

    @Override
    public boolean isSelected() {
        return selected.get();
    }

    @Override
    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    @Override
    public String toString() {
        return value.toString();
    }


    @Override
    public int compareTo(BTData<E> o) {
        return value.compareTo(o.value);
    }
}
