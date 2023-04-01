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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Building represents an actual building. Each building must refer to a valid BuildingTemplate by id.
 * References its template by ID.
 * This class if fully annotated for JAXB
 */
public class Building {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);

    private final BooleanProperty isOperating = new SimpleBooleanProperty(false);

    /**
     * Default Constructor. Generates an invalid Building with id = -1 and isOperating set to false.
     */
    public Building() {
        this.id.set(-1);
        this.isOperating.set(false);
    }

    /**
     *
     * @param id id of the building template associated with this building.
     * @param isOperating flag that determines if the building is operating right now.
     */
    public Building(Integer id, Boolean isOperating) {
        this.id.set(id);
        this.isOperating.set(isOperating);
    }
    /**
     * Lightweight accessor method
     * @return id of the building template associated with this building as a property.
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Lightweight accessor method
     * @return flag that determines if the building is operating right now as a property.
     */
    public BooleanProperty isOperatingProperty() {
        return isOperating;
    }

    /**
     * Lightweight accessor method
     * @return id of the building template associated with this building.
     */
    @XmlElement(name="id")
    public Integer getId() {
        return id.get();
    }

    /**
     * Lightweight accessor method
     * @return flag that determines if the building is operating right now as a property.
     */
    @XmlElement(name="is-operating")
    public Boolean isOperating() {
        return isOperating.get();
    }

    /**
     * Lightweight mutator method
     * @param id id of the building template associated with this building. Should be set to -1 to mark that this building is invalid.
     */
    public void setId(Integer id) {
        this.id.setValue(id);
    }

    /**
     * Lightweight mutator method
     * @param isOperating flag that determines if the building is operating right now
     */
    public void setOperating(Boolean isOperating) {
        this.isOperating.setValue(isOperating);
    }
}
