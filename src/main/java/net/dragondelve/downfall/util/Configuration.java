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

package net.dragondelve.downfall.util;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Determines the configuration of the program, currently chosen Ruleset and default values for gfx files.
 */
@XmlRootElement(name = "configuration")
public final class Configuration {
    private final StringProperty lastRulesPathname = new SimpleStringProperty(DownfallUtil.DEFAULT_RULES_PATHNAME);

    private final StringProperty defMaterialGFXPathname = new SimpleStringProperty(DownfallUtil.DEFAULT_MATERIAL_GFX_PATHNAME);

    private final StringProperty defBuildingGFXPathname = new SimpleStringProperty(DownfallUtil.DEFAULT_BUILDING_GFX_PATHNAME);

    private final BooleanProperty autoloadLastSave = new SimpleBooleanProperty(false);

    private final StringProperty lastSavegamePathname = new SimpleStringProperty(DownfallUtil.DEFAULT_SAVEGAME_PATHNAME);

    /**
     * Lightweight Accessor Method
     *
     * @return Pathname to the last loaded rules.
     */
    @XmlElement(name = "rules-pathname")
    public String getLastRulesPathname() {
        return lastRulesPathname.get();
    }

    /**
     * Lightweight mutator method.
     *
     * @param lastRulesPathname Pathname to the last loaded rules.
     */
    public void setLastRulesPathname(String lastRulesPathname) {
        this.lastRulesPathname.set(lastRulesPathname);
    }

    /**
     * Lightweight Accessor Method
     *
     * @return Pathname to the default image used for VisualMaterialTemplate
     */
    @XmlElement(name = "def-materials-gfx-pathname")
    public String getDefMaterialGFXPathname() {
        return defMaterialGFXPathname.get();
    }

    /**
     * Lightweight mutator method.
     *
     * @param defMaterialGFXPathname Pathname to the default image used for VisualMaterialTemplate
     */
    public void setDefMaterialGFXPathname(String defMaterialGFXPathname) {
        this.defMaterialGFXPathname.set(defMaterialGFXPathname);
    }

    /**
     * Lightweight Accessor Method
     *
     * @return Pathname to the default image used for VisualBuildingTemplate
     */
    @XmlElement(name = "def-buildings-gfx-pathname")
    public String getDefBuildingGFXPathname() {
        return defBuildingGFXPathname.get();
    }

    /**
     * Lightweight mutator method.
     *
     * @param defBuildingGFXPathname Pathname to the default image used for VisualBuildingTemplate
     */
    public void setDefBuildingGFXPathname(String defBuildingGFXPathname) {
        this.defBuildingGFXPathname.set(defBuildingGFXPathname);
    }

    /**
     * Lightweight accessor method
     *
     * @return Flag that determines if the last savegame should be loaded at startup.
     */
    @XmlElement(name = "autoload-last-savegame")
    public Boolean getAutoloadLastSave() {
        return autoloadLastSave.get();
    }

    /**
     * Lightweight mutator method.
     *
     * @param autoloadLastSave Flag that determines if the last savegame should be loaded at startup.
     */
    public void setAutoloadLastSave(Boolean autoloadLastSave) {
        this.autoloadLastSave.set(autoloadLastSave);
    }

    /**
     * Lightweight accessor method
     *
     * @return Pathname to last savegame that was loaded/saved.
     */
    @XmlElement(name = "last-savegame-pathname")
    public String getLastSavegamePathname() {
        return lastSavegamePathname.get();
    }

    /**
     * Lightweight mutator method.
     *
     * @param lastSavegamePathname Pathname to the last loaded rules.
     */
    public void setLastSavegamePathname(String lastSavegamePathname) {
        this.lastSavegamePathname.set(lastSavegamePathname);
    }
}
