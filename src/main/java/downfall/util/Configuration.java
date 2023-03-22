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

package downfall.util;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "configuration")
public class Configuration {
    private final StringProperty lastRulesPathname = new SimpleStringProperty();

    private final StringProperty defMaterialGFXPathname = new SimpleStringProperty();

    private final StringProperty defBuildingGFXPathname = new SimpleStringProperty();

    public Configuration() {
        lastRulesPathname.setValue(DownfallUtil.DEFAULT_RULES_PATHNAME);
        defMaterialGFXPathname.setValue(DownfallUtil.DEFAULT_MATERIAL_GFX_PATHNAME);
        defBuildingGFXPathname.setValue(DownfallUtil.DEFAULT_BUILDING_GFX_PATHNAME);
    }

    @XmlElement(name = "rules-pathname")
    public String getLastRulesPathname() {
        return lastRulesPathname.get();
    }

    @XmlElement(name = "def-materials-gfx-pathname")
    public String getDefMaterialGFXPathname() {
        return defMaterialGFXPathname.get();
    }

    @XmlElement(name = "def-buildings-gfx-pathname")
    public String getDefBuildingGFXPathname() {
        return defBuildingGFXPathname.get();
    }

    public void setLastRulesPathname(String lastRulesPathname) {
        this.lastRulesPathname.set(lastRulesPathname);
    }

    public void setDefMaterialGFXPathname(String defMaterialGFXPathname) {
        this.defMaterialGFXPathname.set(defMaterialGFXPathname);
    }

    public void setDefBuildingGFXPathname(String defBuildingGFXPathname) {
        this.defBuildingGFXPathname.set(defBuildingGFXPathname);
    }
}
