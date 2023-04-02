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

import downfall.realm.Savegame;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple implementation of save manager that uses the Configurator class to store the LastSavegamePathname.
 * If a savegame is loaded it also tries to find, load and apply the rules that were used when last saving this savegame.
 */
final class SimpleSaveManager implements SaveManager{
    /**
     * Loads and applies the savegame from last pathname used and attempts to find,
     * load and validate the rules that are referenced in the savegame.
     */
    @Override
    public void loadFromLast() {
        loadFrom(Configurator.getInstance().getLastSavegamePathname());
    }

    /**
     * Loads and applies the savegame from a given pathname and attempts to find,
     * load and validate the rules that are referenced in the savegame.
     * @param pathname pathname to a savegame file.
     */
    @Override
    public void loadFrom(String pathname) {
        File saveFile = new File(pathname);
        Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.FINE,"Savegame loading initiated with path: "+pathname);
        try {
            JAXBContext context = JAXBContext.newInstance(Savegame.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Savegame savegame = (Savegame) unmarshaller.unmarshal(saveFile);

            Configurator.getInstance().setUserRealm(savegame.getUserRealm());
            Configurator.getInstance().loadAndApplyRules(savegame.getPathToRules());
            Configurator.getInstance().setLastSavegamePathname(pathname);

            Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.FINE,"Savegame config loading successfully completed.");
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING,"Savegame config loading failed, loading default ");
        }
    }

    /**
     * Formulates a new Savegame based on the state of the Configurator and then saves it
     * to the last pathname used.
     */
    @Override
    public void saveToLast() {
        saveTo(Configurator.getInstance().getLastSavegamePathname());
    }

    /**
     * Formulates a new Savegame ba
     * @param pathname pathname to a file in which the savegame data will be recorded.
     */
    @Override
    public void saveTo(String pathname) {
        Savegame savegame = new Savegame();
        savegame.setPathToRules(Configurator.getInstance().getLastRulesPathname());
        savegame.setUserRealm(Configurator.getInstance().getUserRealm());

        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.FINE, "Savegame saving initiated with path: "+pathname);
        try {
            JAXBContext context = JAXBContext.newInstance(Savegame.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(savegame, file);
            Configurator.getInstance().setLastSavegamePathname(pathname);
            Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.FINE, "Savegame saving successfully completed");
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "Couldn't save Savegame to path: "+pathname);
        }
    }
}
