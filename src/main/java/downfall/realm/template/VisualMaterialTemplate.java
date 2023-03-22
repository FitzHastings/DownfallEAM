package downfall.realm.template;

import downfall.util.Configuration;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

@XmlRootElement(name="gfx-material-template")
public class VisualMaterialTemplate extends MaterialTemplate{
    private final StringProperty pathToGFXProperty = new SimpleStringProperty();
    private Image GFX;
    private Boolean gfxInitialized = false;

    public VisualMaterialTemplate() {
        this("",-1,-1,-1,false, false);
    }

    public VisualMaterialTemplate(String name, int id,int defExportPrice, int defImportPrice, boolean isExportable, boolean isEphemeral) {
        this(name, id, defExportPrice, defImportPrice, isExportable, isEphemeral, Configurator.getInstance().getDefMaterialGFXPathname());
    }

    public VisualMaterialTemplate(String name, int id,int defExportPrice, int defImportPrice, boolean isExportable, boolean isEphemeral, String pathToFX) {
        super(name, id, defExportPrice, defImportPrice, isExportable, isEphemeral);
        this.pathToGFXProperty.setValue(pathToFX);
        this.pathToGFXProperty.setValue(Configurator.getInstance().getDefMaterialGFXPathname());
        updateGFX();
    }

    public StringProperty pathToGFXProperty() {
        return pathToGFXProperty;
    }


    //TODO: contemplate removing GFX from here or doing something better with loading (?)
    //TODO: I think something second loads these Graphics. MaterialEditorController (?)
    public Image updateGFX() {
        GFX = DownfallUtil.getInstance().loadImage(pathToGFXProperty.get());
        gfxInitialized = true;
        return GFX;
    }

    public Image getGFX() {
        if(!gfxInitialized)
            return updateGFX();
        else
            return GFX;
    }

    @XmlElement(name="path-to-gfx")
    public String getPathToGFX() {
        return pathToGFXProperty.get();
    }

    public void setPathToGFX(String pathToFX) {
        this.pathToGFXProperty.setValue(pathToFX);
    }
}
