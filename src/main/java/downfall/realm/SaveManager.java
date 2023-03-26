package downfall.realm;

import downfall.util.Configurator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveManager {
    protected Savegame lastSavegame;

    /**
     * loads savegame from the last save.
     */
    public void loadLast() {
        loadFrom(Configurator.getInstance().getLastSavegamePathname());
    }

    public void loadFrom(String pathName) {
        File saveFile = new File(pathName);
        Logger.getAnonymousLogger().log(Level.FINE,"Savegame loading initiated with path: "+pathName);
        try {
            JAXBContext context = JAXBContext.newInstance(Savegame.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Savegame savegame = (Savegame) unmarshaller.unmarshal(saveFile);
            User.getInstance().setUserRealm(savegame.userRealm);

            Configurator.getInstance().loadAndApplyRules(savegame.pathToRules);
            Configurator.getInstance().setLastSavegamePathname(pathName);

            Logger.getAnonymousLogger().log(Level.FINE,"Savegame config loading successfully completed.");
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING,"Savegame config loading failed, loading default ");
        }
    }

    public void save(Savegame savegame) {
        saveTo(savegame, Configurator.getInstance().getLastSavegamePathname());
    }

    public void saveTo(Savegame savegame, String pathName) {
        File file = new File(pathName);
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Logger.getAnonymousLogger().log(Level.FINE, "Savegame saving initiated with path: "+pathName);
        try {
            JAXBContext context = JAXBContext.newInstance(Savegame.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(savegame, file);
            lastSavegame = savegame;
            Logger.getAnonymousLogger().log(Level.FINE, "Savegame saving successfully completed");
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING, "Couldn't save Savegame to path: "+pathName);
        }
    }
}
