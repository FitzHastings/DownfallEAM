package downfall.realm;

import jakarta.xml.bind.annotation.XmlElement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Building {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);

    private final BooleanProperty isOperating = new SimpleBooleanProperty(false);

    public IntegerProperty idProperty() {
        return id;
    }

    public BooleanProperty isOperatingProperty() {
        return isOperating;
    }

    @XmlElement(name="id")
    public int getId() {
        return id.get();
    }

    @XmlElement(name="is-operating")
    public boolean isOperating() {
        return isOperating.get();
    }

    public void setId(int id) {
        this.id.setValue(id);
    }

    public void setOperating(boolean isOperating) {
        this.isOperating.setValue(isOperating);
    }
}
