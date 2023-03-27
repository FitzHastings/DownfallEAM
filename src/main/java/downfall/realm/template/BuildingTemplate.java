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

import downfall.realm.Material;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

/**
 * Used to generate new buildings. This class is annotated for conversion Integero a serializable form using JAXB
 */
@XmlRootElement(name="building-template")
public abstract class BuildingTemplate {
    private final IntegerProperty idProperty = new SimpleIntegerProperty(-1);

    private final StringProperty nameProperty = new SimpleStringProperty();

    private ObservableList<Material> inputMaterials = FXCollections.observableArrayList();

    private ObservableList<Material> outputMaterials = FXCollections.observableArrayList();

    private final IntegerProperty defConstructionCostProperty = new SimpleIntegerProperty();

    private ObservableList<Material> constructionMaterials = FXCollections.observableArrayList();

    private final IntegerProperty defConstructionTimeProperty = new SimpleIntegerProperty();

    private final BooleanProperty operatesImmediatelyProperty = new SimpleBooleanProperty();

    private final BooleanProperty ephemeralProperty = new SimpleBooleanProperty();

    /**
     * Default constructor. Does not provide any default values
     */
    public BuildingTemplate() {
        super();
    }

    /**
     *
     * @param id unique identifier used to differentiate different templates
     * @param name a human-readable name of the template.
     * @param inputMaterials a list of input materials that are used in production in this building per turn
     * @param outputMaterials a list of output materials that are produced in this building per turn
     * @param defConstructionCost construction cost per turn of construction
     * @param constructionMaterials a list of materials consumed during construction per turn of construction
     * @param defConstructionTime number of turns it takes to construct a building
     * @param operatesImmediately does the building operate immediately or do you need to finish its construction
     */
    public BuildingTemplate(Integer id, String name, List<Material> inputMaterials, List<Material> outputMaterials, Integer defConstructionCost, List<Material> constructionMaterials, Integer defConstructionTime, Boolean operatesImmediately) {
        this.idProperty.setValue(id);

        this.inputMaterials.clear();
        if(inputMaterials != null)
            this.inputMaterials.addAll(inputMaterials);

        this.outputMaterials.clear();
        if(outputMaterials != null)
            this.outputMaterials.addAll(outputMaterials);

        this.defConstructionCostProperty.setValue(defConstructionCost);

        this.constructionMaterials.clear();
        if (constructionMaterials != null)
            this.constructionMaterials.addAll(constructionMaterials);

        this.defConstructionTimeProperty.setValue(id);
        this.operatesImmediatelyProperty.set(operatesImmediately);
    }

    /**
     * Lightweight Accessor Method
     * @return unique identifier used to differentiate different templates as a property
     */
    public IntegerProperty idPropertyProperty() {
        return idProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return a human-readable name of the template as a property
     */
    public StringProperty nameProperty() {
        return nameProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return construction cost per turn of construction as a property
     */
    public IntegerProperty defConstructionCostProperty() {
        return defConstructionCostProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return number of turns it takes to construct a building as a property
     */
    public IntegerProperty defConstructionTimeProperty() {
        return defConstructionTimeProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return umber of turns it takes to construct a building as a property
     */
    public BooleanProperty operatesImmediatelyProperty() {
        return operatesImmediatelyProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return unique identifier used to differentiate different templates
     */
    @XmlElement(name="id")
    public Integer getId() {
        return idProperty.get();
    }

    /**
     * Lightweight Accessor Method
     * @return a human-readable name of the template
     */
    @XmlElement(name = "name")
    public String getName() {
        return nameProperty.get();
    }

    /**
     * Lightweight Accessor Method
     * @return a list of input materials that are used in production in this building per turn
     */
    @XmlElementWrapper(name="input-materials")
    @XmlElement(name="material")
    public ObservableList<Material> getInputMaterials() {
        return inputMaterials;
    }

    /**
     * Lightweight Accessor Method
     * @return a list of output materials that are produced in this building per turn
     */
    @XmlElementWrapper(name="output-materials")
    @XmlElement(name="material")
    public ObservableList<Material> getOutputMaterials() {
        return outputMaterials;
    }

    /**
     * Lightweight Accessor Method
     * @return construction cost per turn of construction
     */
    @XmlElement(name="def-construction-cost")
    public Integer getDefConstructionCost() {
        return defConstructionCostProperty.get();
    }

    /**
     * Lightweight Accessor Method
     * @return a list of materials consumed during construction per turn of construction
     */
    @XmlElementWrapper(name="construction-materials")
    @XmlElement(name="material")
    public ObservableList<Material> getConstructionMaterials() {
        return constructionMaterials;
    }

    /**
     * Lightweight Accessor Method
     * @return number of turns it takes to construct a building
     */
    @XmlElement(name="def-construction-time")
    public Integer getDefConstructionTime() {
        return defConstructionTimeProperty.get();
    }

    /**
     * Lightweight Accessor Method
     * @return does the building operate immediately or do you need to finish its construction as a property
     */
    @XmlElement(name="operates-immediately")
    public Boolean getOperatesImmediately() {
        return operatesImmediatelyProperty.get();
    }

    /**
     * Lightweight Mutator Method
     * @param id unique identifier used to differentiate different templates
     */
    public void setId(Integer id) {
        this.idProperty.setValue(id);
    }

    /**
     * Lightweight Mutator Method
     * @param name a human-readable name of the template
     */
    public void setName(String name) {
        this.nameProperty.setValue(name);
    }

    /**
     * Lightweight Mutator Method
     * @param inputMaterials a list of input materials that are used in production in this building per turn
     */
    public void setInputMaterials(ObservableList<Material> inputMaterials) {
        this.inputMaterials = inputMaterials;
    }

    /**
     * Lightweight Mutator Method
     * @param outputMaterials a list of output materials that are produced in this building per turn
     */
    public void setOutputMaterials(ObservableList<Material> outputMaterials) {
        this.outputMaterials = outputMaterials;
    }

    /**
     * Lightweight Mutator Method
     * @param defConstructionCost construction cost per turn of construction
     */
    public void setDefConstructionCost(Integer defConstructionCost) {
        this.defConstructionCostProperty.setValue(defConstructionCost);
    }

    /**
     * Lightweight Mutator Method
     * @param constructionMaterials a list of materials consumed during construction per turn of construction
     */
    public void setConstructionMaterials(ObservableList<Material> constructionMaterials) {
        this.constructionMaterials = constructionMaterials;
    }

    /**
     * Lightweight Mutator Method
     * @param defConstructionTime number of turns it takes to construct a building
     */
    public void setDefConstructionTime(Integer defConstructionTime) {
        this.defConstructionTimeProperty.setValue(defConstructionTime);
    }

    /**
     * Lightweight Mutator Method
     * @param operatesImmediately does the building operate immediately or do you need to finish its construction
     */
    public void setOperatesImmediately(Boolean operatesImmediately) {
        this.operatesImmediatelyProperty.set(operatesImmediately);
    }
}
