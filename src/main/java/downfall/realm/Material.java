package downfall.realm;

import downfall.realm.template.MaterialTemplate;
import downfall.realm.template.VisualMaterialTemplate;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a bundle of materials, like 30 Wood or 15 Stone.
 * Each Material has a template and is used to both show income and
 */
@XmlRootElement(name="material")
public class Material {

    protected IntegerProperty idProperty = new SimpleIntegerProperty(-1);

    //I honestly would have overloaded +- operators for Material because you can just add/subtract their amounts if they have the same ID
    protected IntegerProperty amountProperty = new SimpleIntegerProperty(0);

    /**
     * Initializes amount to a default value of 0.
     * @param template Template from which this material will be generated from
     */
    public Material(VisualMaterialTemplate template) {
        this(template, 0);
    }

    /**
     *
     * @param template Template from which this material will be generated from
     * @param amount Amount of materials in this bundle.
     */
    public Material(MaterialTemplate template, int amount) {
        idProperty.setValue(template.getId());
        amountProperty.setValue(amount);
    }

    /**
     * Default Constructor. it creates an invalid material. Its id is set to -1 and amount is set 0.
     */
    public Material(){
        this(-1, 0);
    }

    /**
     *
     * @param id Material Template id of a template that this bundle of materials represents
     * @param amount Amount of materials in this bundle.
     */
    public Material(int id, int amount) {
        idProperty.setValue(id);
        amountProperty.setValue(amount);
    }

    /**
     * Lightweight Accessor Method
     * @return Material Template id of a template that this bundle of materials represents
     */
    public IntegerProperty idProperty() {
        return idProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return Amount of materials in this bundle as a property.
     */
    public IntegerProperty amountProperty() {
        return amountProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return Material Template id of a template that this bundle of materials represents
     */
    @XmlElement(name = "template-id")
    public int getTemplateId() {
        return idProperty.get();
    }

    /**
     * Lightweight Accessor Method
     * @return Amount of materials in this bundle.
     */
    @XmlElement(name = "amount")
    public int getAmount() {
        return amountProperty.get();
    }

    /**
     * Lightweight Mutator Method
     * @param id Material Template id of a template that this bundle of materials represents
     */
    public void setTemplateId(int id) {
        idProperty.setValue(id);
    }

    /**
     * Lightweight Mutator Method
     * @param amount Amount of materials in this bundle.
     */
    public void setAmount(int amount) {
        amountProperty.setValue(amount);
    }
}