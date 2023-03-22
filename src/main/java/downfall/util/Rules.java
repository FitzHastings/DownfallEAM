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

import downfall.realm.template.VisualBuildingTemplate;
import downfall.realm.template.VisualMaterialTemplate;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains reference to a user defined Ruleset. Ruleset determines Templates that can be used for different Materials and Buildings.
 * This class if fully annotated and can be saved to an XML file with JAXB
 */
@XmlRootElement(name="rules")
public class Rules {
    private List<VisualMaterialTemplate> materialTemplates = new ArrayList<>();
    private List<VisualBuildingTemplate> buildingTemplates = new ArrayList<>();

    /**
     * Lightweight accessor method.
     * @return full list of all available material templates
     */
    @XmlElementWrapper(name="material-templates")
    @XmlElement(name="material-template")
    public List<VisualMaterialTemplate> getMaterialTemplates() {
        return materialTemplates;
    }

    /**
     * Lightweight accessor method.
     * @return full list of all available building templates
     */
    @XmlElementWrapper(name="building-templates")
    @XmlElement(name="building-template")
    public List<VisualBuildingTemplate> getBuildingTemplates() {
        return buildingTemplates;
    }

    /**
     * Lightweight mutator method.
     * @param materialTemplates full list of all available material templates
     */
    public void setMaterialTemplates(List<VisualMaterialTemplate> materialTemplates) {
        this.materialTemplates = materialTemplates;
    }

    /**
     * Lightweight mutator method.
     * @param buildingTemplates full list of all available building templates
     */
    public void setBuildingTemplates(List<VisualBuildingTemplate> buildingTemplates) {
        this.buildingTemplates = buildingTemplates;
    }
}
