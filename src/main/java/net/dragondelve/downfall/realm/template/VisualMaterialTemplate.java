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

package net.dragondelve.downfall.realm.template;

import net.dragondelve.downfall.util.Configurator;
import net.dragondelve.downfall.util.DownfallUtil;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * Template that is used when new materials are generated. It stores a pathname that leads to its GFX and an JavaFX Image if a reference to that is required.
 * This Class if fully annotated for use with JAXB to be exported to an XML File.
 */
@XmlRootElement(name="gfx-material-template")
public final class VisualMaterialTemplate extends MaterialTemplate{
    private final StringProperty pathToGFXProperty = new SimpleStringProperty();
    private Image GFX;
    private Boolean gfxInitialized = false;

    /**
     * Default Constructor. it generates an invalid VisualMaterialTemplate with an id of -1
     */
    public VisualMaterialTemplate() {
        this("",-1,-1,-1,false, false);
    }

    /**
     * Initializes pathToGFX to the default MaterialGFXPathname defined in the current configuration.
     * @param name Human-readable name of the materials to be generated with this template
     * @param id Unique identifier used to differentiate different material templates
     * @param defExportPrice Default export price
     * @param defImportPrice Default import price
     * @param isExportable Value that determines if the material will be exportable from the realm
     * @param isEphemeral Value that determines if the material can be stockpiled or stored
     */
    public VisualMaterialTemplate(String name, Integer id,Integer defExportPrice, Integer defImportPrice, Boolean isExportable, Boolean isEphemeral) {
        this(name, id, defExportPrice, defImportPrice, isExportable, isEphemeral, Configurator.getInstance().getDefMaterialGFXPathname());
    }

    /**
     *
     * @param name Human-readable name of the materials to be generated with this template
     * @param id Unique identifier used to differentiate different material templates
     * @param defExportPrice Default export price
     * @param defImportPrice Default import price
     * @param isExportable Value that determines if the material will be exportable from the realm
     * @param isEphemeral Value that determines if the material can be stockpiled or stored
     * @param pathToGFX String pathname to an image file that represents this material. That Image should be square, but isn't required to be square
     */
    public VisualMaterialTemplate(String name, Integer id,Integer defExportPrice, Integer defImportPrice, Boolean isExportable, Boolean isEphemeral, String pathToGFX) {
        super(name, id, defExportPrice, defImportPrice, isExportable, isEphemeral);
        this.pathToGFXProperty.setValue(pathToGFX);
        this.pathToGFXProperty.setValue(Configurator.getInstance().getDefMaterialGFXPathname());
    }

    /**
     * Lightweight accessor method.
     * @return pathname to an image file that represents this material as a property.
     */
    public StringProperty pathToGFXProperty() {
        return pathToGFXProperty;
    }

    /**
     * Updates the Image representation material to comply with the current value of pathToGFXProperty
     * @return new Image that has been updated.
     */
    public Image updateGFX() {
        //TODO: contemplate removing GFX from here or doing something better with loading (?)
        //TODO: I think something second loads these Graphics. MaterialEditorController (?)
        GFX = DownfallUtil.getInstance().loadImage(pathToGFXProperty.get());
        gfxInitialized = true;
        return GFX;
    }

    /**
     * Lightweight accessor method that initiates graphics if they haven't been initialized
     * @return Image that represents this material.
     */
    public Image getGFX() {
        if(!gfxInitialized)
            return updateGFX();
        else
            return GFX;
    }

    /**
     * Lightweight accessor method.
     * @return String pathname to an image file that represents this material.
     */
    @XmlElement(name="path-to-gfx")
    public String getPathToGFX() {
        return pathToGFXProperty.get();
    }

    /**
     * Lightweight Mutator Method
     * @param pathToGFX String pathname to an image file that represents this material. That Image should be square, but isn't required to be square
     */
    public void setPathToGFX(String pathToGFX) {
        this.pathToGFXProperty.setValue(pathToGFX);
    }
}
