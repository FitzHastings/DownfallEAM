package downfall.util;

import downfall.realm.template.BuildingTemplate;
import downfall.realm.template.VisualBuildingTemplate;
import downfall.realm.template.VisualMaterialTemplate;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="rules")
public class Rules {
    private List<VisualMaterialTemplate> materialTemplates = new ArrayList<>();
    private List<VisualBuildingTemplate> buildingTemplates = new ArrayList<>();

    @XmlElementWrapper(name="material-templates")
    @XmlElement(name="material-template")
    public List<VisualMaterialTemplate> getMaterialTemplates() {
        return materialTemplates;
    }

    @XmlElementWrapper(name="building-templates")
    @XmlElement(name="building-template")
    public List<VisualBuildingTemplate> getBuildingTemplates() {
        return buildingTemplates;
    }

    public void setBuildingTemplates(List<VisualBuildingTemplate> buildingTemplates) {
        this.buildingTemplates = buildingTemplates;
    }

    public void setMaterialTemplates(List<VisualMaterialTemplate> materialTemplates) {
        this.materialTemplates = materialTemplates;
    }
}
