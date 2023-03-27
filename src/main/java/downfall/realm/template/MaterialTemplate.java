// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package downfall.realm.template;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.*;

/**
 * Used to generate new materials. This class is annotated for conversion Integero a serializable form using JAXB
 */
@XmlRootElement(name = "material-template")
public abstract class MaterialTemplate {
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final IntegerProperty idProperty = new SimpleIntegerProperty(-1);
    private final IntegerProperty defExportPriceProperty = new SimpleIntegerProperty(-1);
    private final IntegerProperty defImportPriceProperty = new SimpleIntegerProperty(-1);
    private final BooleanProperty isExportableProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty isEphemeralProperty = new SimpleBooleanProperty(false);

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
    public MaterialTemplate(String name, Integer id, Integer defExportPrice, Integer defImportPrice, Boolean isExportable, Boolean isEphemeral) {
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
    public Integer getId() {
        return idProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Default export price
     */
    @XmlElement(name = "def-export-template")
    public Integer getDefExportPrice() {
        return defExportPriceProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Default import price
     */
    @XmlElement(name = "def-import-price")
    public Integer getDefImportPrice() {
        return defImportPriceProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Value that determines if the material will be exportable from the realm
     */
    @XmlElement(name = "exportable")
    public Boolean isExportable() {
        return isExportableProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return Value that determines if the material can be stockpiled or stored
     */
    @XmlElement(name = "ephemeral")
    public Boolean isEphemeral() { return  isEphemeralProperty.get();}


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
    public void setId(Integer idProperty) {
        this.idProperty.setValue(idProperty);
    }

    /**
     * Lightweight mutator method
     * @param defExportPriceProperty Default export price
     */
    public void setDefExportPrice(Integer defExportPriceProperty) {
        this.defExportPriceProperty.setValue(defExportPriceProperty);
    }

    /**
     * Lightweight mutator method
     * @param defImportPriceProperty Default import price
     */
    public void setDefImportPrice(Integer defImportPriceProperty) {
        this.defImportPriceProperty.setValue(defImportPriceProperty);
    }

    /**
     * Lightweight mutator method
     * @param exportable  Value that determines if the material will be exportable from the realm
     */
    public void setExportable(Boolean exportable) {
        isExportableProperty.setValue(exportable);
    }

    /**
     * Lightweight mutator method
     * @param isEphemeral Value that determines if the material can be stockpiled or stored
     */
    public void setIsEphemeral(Boolean isEphemeral) {
        this.isEphemeralProperty.set(isEphemeral);
    }
}
