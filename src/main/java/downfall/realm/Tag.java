package downfall.realm;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A Tag is a String value that can be applied to Actors. This is used in certain scripts, events can determine access to special mechanics.
 * This class is fully annotated for serialization with JAXB
 */
@XmlRootElement(name = "tag")
public class Tag {
    private final StringProperty tag = new SimpleStringProperty();

    /**
     * Lightweight accessor method
     * @return Tag title as a property
     */
    public StringProperty tagProperty() {
        return tag;
    }

    /**
     * Lightweight accessor method
     * @return Tag title as value
     */
    @XmlElement(name="value")
    public String getTag() {
        return tag.get();
    }

    /**
     * Lightweight mutator method
     * @param tag Tag name
     */
    public void setTag(String tag) {
        this.tag.set(tag);
    }
}
