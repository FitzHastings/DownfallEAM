package downfall.realm.template;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.*;

/**
 * Used to generate new materials. This class is annotated for conversion into a serializable form using JAXB
 */
@XmlRootElement(name = "material-template")
public abstract class MaterialTemplate {
    protected StringProperty nameProperty = new SimpleStringProperty();
    protected IntegerProperty idProperty = new SimpleIntegerProperty(-1);
    protected IntegerProperty defExportPriceProperty = new SimpleIntegerProperty(-1);
    protected IntegerProperty defImportPriceProperty = new SimpleIntegerProperty(-1);
    protected BooleanProperty isExportableProperty = new SimpleBooleanProperty(false);
    protected BooleanProperty isEphemeralProperty = new SimpleBooleanProperty(false);

    /**
     * Default Constructor. Does not provide default values
     */
    public MaterialTemplate() {
        super();
    }

    /**
     *
     * @param name Human-readable name of the materials to be generated with this template
     * @param id Unique identifier used to differentiate different material templates
     * @param defExportPrice Default export price
     * @param defImportPrice Default import price
     * @param isExportable Value that determines if the material will be exportable from the realm
     * @param isEphemeral Value that determines if the material can be stockpiled or stored
     */
    public MaterialTemplate(String name, int id, int defExportPrice, int defImportPrice, boolean isExportable, boolean isEphemeral) {
        super();
        this.nameProperty.setValue(name);
        this.idProperty.setValue(id);
        this.defExportPriceProperty .setValue(defExportPrice);
        this.defImportPriceProperty.setValue(defImportPrice);
        this.isExportableProperty.setValue(isExportable);
        this.isEphemeralProperty.set(isEphemeral);
    }

    /**
     * Lightweight accessor method
     * @return Human readable name of the materials to be generated with this template as a property
     */
    public StringProperty nameProperty() {
        return nameProperty;
    }

    /**
     * Lightweight accessor method
     * @return Unique identifier used to differentiate different material templates as a property
     */
    public IntegerProperty idProperty() {
        return idProperty;
    }

    /**
     * Lightweight accessor method
     * @return Default export price as a property
     */
    public IntegerProperty defExportPriceProperty() {
        return defExportPriceProperty;
    }

    /**
     * Lightweight accessor method
     * @return Default import price as a property
     */
    public IntegerProperty defImportPriceProperty() {
        return defImportPriceProperty;
    }

    /**
     * Lightweight accessor method
     * @return Value that determines if the material will be exportable from the realm as a property
     */
    public BooleanProperty isExportableProperty() {
        return isExportableProperty;
    }

    /**
     * Lightweight accessor method
     * @return Value that determines if the material can be stockpiled or stored as a property
     */
    public BooleanProperty isEphemeralProperty() {
        return isEphemeralProperty;
    }

    /**
     * Lightweight accessor method
     * @return Human-readable name of the materials to be generated with this template
     */
    @XmlElement(name = "name")
    public String getName() {
        return nameProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Unique identifier used to differentiate different material templates
     */
    @XmlElement(name = "id")
    public int getId() {
        return idProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Default export price
     */
    @XmlElement(name = "def-export-template")
    public int getDefExportPrice() {
        return defExportPriceProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Default import price
     */
    @XmlElement(name = "def-import-price")
    public int getDefImportPrice() {
        return defImportPriceProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Value that determines if the material will be exportable from the realm
     */
    @XmlElement(name = "exportable")
    public boolean isExportable() {
        return isExportableProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Value that determines if the material can be stockpiled or stored
     */
    @XmlElement(name = "ephemeral")
    public boolean isEphemeral() { return  isEphemeralProperty.get();}


    /**
     * Lightweight mutator method
     * @param name Human-readable name of the materials to be generated with this template
     */
    public void setName(String name) {
        this.nameProperty.setValue(name);
    }

    /**
     * Lightweight mutator method
     * @param idProperty Unique identifier used to differentiate different material templates
     */
    public void setId(int idProperty) {
        this.idProperty.setValue(idProperty);
    }

    /**
     * Lightweight mutator method
     * @param defExportPriceProperty Default export price
     */
    public void setDefExportPrice(int defExportPriceProperty) {
        this.defExportPriceProperty.setValue(defExportPriceProperty);
    }

    /**
     * Lightweight mutator method
     * @param defImportPriceProperty Default import price
     */
    public void setDefImportPrice(int defImportPriceProperty) {
        this.defImportPriceProperty.setValue(defImportPriceProperty);
    }

    /**
     * Lightweight mutator method
     * @param exportable  Value that determines if the material will be exportable from the realm
     */
    public void setExportable(boolean exportable) {
        isExportableProperty.setValue(exportable);
    }

    /**
     * Lightweight mutator method
     * @param isEphemeral Value that determines if the material can be stockpiled or stored
     */
    public void setIsEphemeral(boolean isEphemeral) {
        this.isEphemeralProperty.set(isEphemeral);
    }
}
