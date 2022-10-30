package no.oblig2.gruppe1.algoritmeroblig2.model;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Interface for å sikre at objekt kan endre bakgrunn.
 */
public interface CanBeBTData {
    boolean isSelected();
    void setSelected(boolean selected);
    SimpleBooleanProperty selectedProperty();
}
