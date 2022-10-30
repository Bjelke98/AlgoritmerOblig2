package no.oblig2.gruppe1.algoritmeroblig2.model;

import javafx.beans.property.SimpleBooleanProperty;

public interface CanBeBTData {
    boolean isSelected();
    public void setSelected(boolean selected);
    SimpleBooleanProperty selectedProperty();
}
