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

package net.dragondelve.downfall.realm;

import net.dragondelve.downfall.realm.template.MaterialTemplate;
import net.dragondelve.downfall.realm.template.VisualMaterialTemplate;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a bundle of materials, like 30 Wood or 15 Stone.
 * Each Material has a template and is used to both show income and amount stockpiled. It is also used to show costs of operating a building
 */
@XmlRootElement(name="material")
public class Material {

    private final IntegerProperty idProperty = new SimpleIntegerProperty(-1);

    //I honestly would have overloaded +- operators for Material because you can just add/subtract their amounts if they have the same ID
    private final IntegerProperty amountProperty = new SimpleIntegerProperty(0);

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
    public Material(MaterialTemplate template, Integer amount) {
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
    public Material(Integer id, Integer amount) {
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
    public Integer getTemplateId() {
        return idProperty.get();
    }

    /**
     * Lightweight Accessor Method
     * @return Amount of materials in this bundle.
     */
    @XmlElement(name = "amount")
    public Integer getAmount() {
        return amountProperty.get();
    }

    /**
     * Lightweight Mutator Method
     * @param id Material Template id of a template that this bundle of materials represents
     */
    public void setTemplateId(Integer id) {
        idProperty.setValue(id);
    }

    /**
     * Lightweight Mutator Method
     * @param amount Amount of materials in this bundle.
     */
    public void setAmount(Integer amount) {
        amountProperty.setValue(amount);
    }
}