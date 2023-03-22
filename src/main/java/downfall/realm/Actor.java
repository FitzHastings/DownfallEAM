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

package downfall.realm;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Actor is a basis common for all entities that can build or store or act on the board in any way shape or form.
 */
public abstract class Actor {
    protected StringProperty name = new SimpleStringProperty();
    protected IntegerProperty treasury = new SimpleIntegerProperty();
    protected ObservableList<Material> stockpile = FXCollections.emptyObservableList();
    protected ObservableList<Building> ownedBuildings = FXCollections.observableArrayList();
    protected ObservableList<Tag> tags = FXCollections.observableArrayList();

    /**
     * Lightweight accessor method
     * @return Actor name as a property.
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Lightweight accessor method
     * @return amount of money in the treasury as a property
     */
    public IntegerProperty treasuryProperty() {
        return treasury;
    }


    /**
     * Lightweight accessor method
     * @return Amount of money in the treasury
     */
    @XmlElement(name = "treasury")
    public int getTreasury() {
        return treasury.get();
    }

    /**
     * Lightweight accessor method
     * @return list of all materials in actor's stockpile
     */
    @XmlElementWrapper(name = "stockpile")
    @XmlElement(name = "material")
    public ObservableList<Material> getStockpile() {
        return stockpile;
    }

    /**
     * Lightweight accessor method
     * @return List of all owned buildings
     */
    @XmlElementWrapper(name = "owned-buildings")
    @XmlElement(name = "building")
    public ObservableList<Building> getOwnedBuildings() {
        return ownedBuildings;
    }

    /**
     * Lightweight accessor method
     * @return List of all tags applied to the actor
     */
    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    public ObservableList<Tag> getTags() {
        return tags;
    }

    /**
     * Lightweight mutator method
     * @param name Actor name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Lightweight mutator method
     * @param treasury Amount of money in the treasury
     */
    public void setTreasury(int treasury) {
        this.treasury.set(treasury);
    }

    /**
     * Lightweight mutator method
     * @param stockpile list of all materials in actor's stockpile
     */
    public void setStockpile(ObservableList<Material> stockpile) {
        this.stockpile = stockpile;
    }

    /**
     * Lightweight mutator method
     * @param ownedBuildings List of all owned buildings
     */
    public void setOwnedBuildings(ObservableList<Building> ownedBuildings) {
        this.ownedBuildings = ownedBuildings;
    }

    /**
     * Lightweight mutator method
     * @param tags List of all tags applied to the actor
     */
    public void setTags(ObservableList<Tag> tags) {
        this.tags = tags;
    }
}
