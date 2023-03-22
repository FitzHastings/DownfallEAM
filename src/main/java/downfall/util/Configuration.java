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
