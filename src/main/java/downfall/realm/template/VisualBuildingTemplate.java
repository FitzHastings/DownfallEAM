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
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.util.List;

/**
 * Template that is used when new buildings are generated. It stores a pathname that leads to its GFX and an JavaFX Image if a reference to that is required.
 */
@XmlRootElement(name="visual-building-template")
public class VisualBuildingTemplate extends BuildingTemplate{
    private final StringProperty pathToGFXProperty = new SimpleStringProperty();
    private Image GFX;
    private boolean gfxInitialized = false;

    /**
     * Default Constructor. Generates an invalid instance with id set to -1
     */
    public VisualBuildingTemplate() {
        this(-1, "",-1,-1,false);
    }

    /**
     *
     * @param id unique identifier used to differentiate different templates
     * @param name a human-readable name of the template.
     * @param defConstructionCost construction cost per turn of construction
     * @param defConstructionTime number of turns it takes to construct a building
     * @param operatesImmediately does the building operate immediately or do you need to finish its construction
     */
    public VisualBuildingTemplate(int id, String name, int defConstructionCost, int defConstructionTime, boolean operatesImmediately) {
        this(id, name, null, null, defConstructionCost, null, defConstructionTime, operatesImmediately, Configurator.getInstance().getDefBuildingGFXPathname());
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
     * @param pathToFX String pathname to an image file that represents this material. That Image should be square, but isn't required to be square
     */
    public VisualBuildingTemplate(int id, String name, List<Material> inputMaterials, List<Material> outputMaterials, int defConstructionCost, List<Material> constructionMaterials, int defConstructionTime, boolean operatesImmediately, String pathToFX) {
        super(id, name, inputMaterials, outputMaterials, defConstructionCost, constructionMaterials, defConstructionTime, operatesImmediately);
        this.pathToGFXProperty.setValue(pathToFX);
        this.pathToGFXProperty.setValue(Configurator.getInstance().getDefBuildingGFXPathname());
        updateGFX();
    }

    /**
     * Lightweight Accessor Method
     * @return pathname to an image file that represents this material as a property. That Image should be square, but isn't required to be square
     */
    public StringProperty pathToGFXProperty() {
        return pathToGFXProperty;
    }

    /**
     * Lightweight Accessor Method
     * @return String pathname to an image file that represents this material. That Image should be square, but isn't required to be square
     */
    @XmlElement(name="path-to-gfx")
    public String getPathToGFX() {
        return pathToGFXProperty.get();
    }

    /**
     * Lightweight Accessor Method that initiates graphics if they haven't been initialized
     * @return Image that represents this Building.
     */
    public Image getGFX() {
        if(!gfxInitialized)
            return updateGFX();
        else
            return GFX;
    }

    /**
     * Lightweight Mutator Method
     * @param pathToGFX String pathname to an image file that represents this material. That Image should be square, but isn't required to be square
     */
    public void setPathToGFX(String pathToGFX) {
        this.pathToGFXProperty.set(pathToGFX);
    }

    /**
     * Updates the Image representation building to comply with the current value of pathToFXProperty
     * @return new Image that has been updated.
     */
    public Image updateGFX() {
        //TODO: contemplate removing GFX from here or doing something better with loading (?)
        GFX = DownfallUtil.getInstance().loadImage(pathToGFXProperty.get());
        gfxInitialized = true;
        return GFX;
    }


}
