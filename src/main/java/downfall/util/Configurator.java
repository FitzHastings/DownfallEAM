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

import downfall.realm.Material;
import downfall.realm.template.VisualBuildingTemplate;
import downfall.realm.template.VisualMaterialTemplate;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *  Non Instantiable configurator class that is responsible for loading, and storing the Configuration of the program.
 */
public class Configurator {
    private static final String CONFIG_PATH = DownfallUtil.DEFAULT_CONFIG_PATHNAME;
    private static final String DEFAULT_RULES_PATH = "rules/default.xml";

    private static final Configurator instance = new Configurator();

    private Configuration configuration = new Configuration();

    private Rules rules = new Rules();

    /**
     * Private constructor to make this class non instantiable.
     */
    private Configurator() {super();}

    /**
     * loads and applies rules stored as lastLoadedRules in the current configuration
     */
    public void loadAndApplyRules() {
        loadAndApplyRules(configuration.getLastRulesPathname());
    }

    /**
     * loads and applies rules stored at pathname and changes the configuration to remember the new pathname as lastLoaderRules
     * @param pathname pathname to rules to be loaded and applied
     */
    public void loadAndApplyRules(String pathname) {
        configuration.setLastRulesPathname(pathname);
        rules = loadRules(pathname);
    }

    /**
     * Lightweight accessor method.
     * @return Currently applied rules.
     */
    public Rules getRules() {
        return rules;
    }

    /**
     * Loads configuration from CONFIG_PATH defined in this class.
     */
    public void loadConfiguration() {
        File config = new File(CONFIG_PATH);
        Logger.getLogger("Downfall").log(Level.FINE, "Configuration loading initiated with path: " + CONFIG_PATH);
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            configuration = (Configuration) unmarshaller.unmarshal(config);
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING,"Configuration loading failed, attempting to save a default configuration");
            saveConfiguration();
        }
    }

    /**
     * Saves configuration to an XML firle at CONFIG_PATH defined in this class
     */
    public void saveConfiguration() {
        File config = new File(CONFIG_PATH);
        Logger.getLogger("Downfall").log(Level.FINE, "Configuration saving initiated with path: " + CONFIG_PATH);
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(configuration, config);
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING,"Configuration saving failed =C");
        }
    }

    /**
     *
     * @param pathname pathname to an xml file where rules to be downloaded are located.
     * @return rules that have been loaded. If loading from pathname fails, returns defaultRules isnted.
     */
    private Rules loadRules(String pathname) {
        File rulesFile = new File(pathname);
        Logger.getAnonymousLogger().log(Level.FINE,"Rules loading initiated with path: "+pathname);
        try {
            JAXBContext context = JAXBContext.newInstance(Rules.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Logger.getAnonymousLogger().log(Level.FINE,"Rules config loading successfully completed.");
            return (Rules) unmarshaller.unmarshal(rulesFile);
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING,"Rules config loading failed, loading default ");
            Rules rules = loadDefaultRules();
            saveRules(rules, pathname);
            return rules;
        }
    }

    /**
     * Saves currently applied rules to an XML file at DEFAULT_RULES_PATH defined in this class
     */
    public void saveRules() {
        saveRules(DEFAULT_RULES_PATH);
    }

    /**
     * Saves currently applied rules to an XML file at a given pathname
     * @param pathName pathname to a file that can be written.
     */
    public void saveRules(String pathName){
        configuration.setLastRulesPathname(pathName);
        saveRules(rules, pathName);
    }

    /**
     * Saves given rules to an XML files at a given pathname
     * @param rules rules to be saved.
     * @param pathName pathname to a file that can be written.
     */
    private void saveRules(Rules rules, String pathName) {
        File file = new File(pathName);
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Logger.getAnonymousLogger().log(Level.FINE, "Rules saving initiated with path: "+pathName);
        try {
            JAXBContext context = JAXBContext.newInstance(Rules.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(rules, file);
            Logger.getAnonymousLogger().log(Level.FINE, "Rules saving successfully completed");
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING, "Couldn't save Rules to path: "+pathName);
        }
    }

    /**
     * Lightweight Accessor Method
     * @return pathname to the default GFX of VisualMaterialTemplate in the current configuration.
     */
    public String getDefMaterialGFXPathname() {
        return configuration.getDefMaterialGFXPathname();
    }

    /**
     * Lightweight Accessor Method
     * @return pathname to the default GFX of VisualBuildingTemplate in the current configuration.
     */
    public String getDefBuildingGFXPathname() {
        return configuration.getDefBuildingGFXPathname();
    }

    /**
     * NOT a lightweight accessor method
     * @param material material that has an id set to represent a specific template.
     * @return VisualMaterialTemplate that is associated with that material. If none found returns null.
     */
    public VisualMaterialTemplate getTemplate(Material material) {
        if(material == null)
            return null;
        List<VisualMaterialTemplate> list = rules.getMaterialTemplates().stream().filter(visualMaterialTemplate -> visualMaterialTemplate.getId() == material.getTemplateId()).collect(Collectors.toList());
        if(list.size() > 1) {
            Logger.getLogger("Downfall").log(Level.WARNING, "Multiple("+list.size()+") IDs found for template with id = "+material.getTemplateId());
        } else if(list.size() <= 0) {
            Logger.getLogger("Downfall").log(Level.SEVERE, "No Template found for id = "+material.getTemplateId());
            return null;
        }
        return list.get(0);
    }

    /**
     * Loads predefined default rules that are hard coded.
     * @return default rules that .
     */
    private Rules loadDefaultRules() {
        Rules rules = new Rules();
        List<VisualMaterialTemplate> materialTemplates = new ArrayList<>();
        materialTemplates.add(new VisualMaterialTemplate("Grain",1,5,8,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Meat",2,8,12,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Cloth",3,12,16,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Clay",4,4,7,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Bricks",5,9,14,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Wood",6,5,8,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Hardwood",7,6,12,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Furniture",8,16,22,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Copper",9,3,6,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Bronze",10,4,8,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Iron",11,6,12,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Steel",12,9,18,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Thaumium",13,20,30,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Gold",14,50,50,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Bronze Tools",15,20,24,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Iron Tools",16,24,30,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Steel Tools",17,32,44,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Magic Tools",18,44,72,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Bronze Weapons",19,24,30,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Iron Weapons",20,28,36,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Steel Weapons",21,36,50,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Magic Weapons",22,48,78,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Siege Equipment",23,50,75,true, false));
        materialTemplates.add(new VisualMaterialTemplate("Services",24,-1,10,false, false));

        List<VisualBuildingTemplate> buildingTemplates = new ArrayList<>();

        VisualBuildingTemplate farmTemplate = new VisualBuildingTemplate();
        farmTemplate.setId(1);
        farmTemplate.setName("Farm (Iron Tools)");
        farmTemplate.setDefConstructionTime(30);
        farmTemplate.setDefConstructionCost(30);
        farmTemplate.getInputMaterials().add(new Material(17, 1));
        farmTemplate.getOutputMaterials().add(new Material(1, 10));
        farmTemplate.getConstructionMaterials().add(new Material(7, 10));
        farmTemplate.getConstructionMaterials().add(new Material(17, 1));
        buildingTemplates.add(farmTemplate);

        VisualBuildingTemplate barnTemplate = new VisualBuildingTemplate();
        barnTemplate.setId(2);
        barnTemplate.setName("Barn (Iron Tools)");
        barnTemplate.setDefConstructionTime(30);
        barnTemplate.setDefConstructionCost(30);
        barnTemplate.getInputMaterials().add(new Material(17, 1));
        barnTemplate.getInputMaterials().add(new Material(1,10));
        barnTemplate.getOutputMaterials().add(new Material(2, 10));
        barnTemplate.getConstructionMaterials().add(new Material(7, 10));
        barnTemplate.getConstructionMaterials().add(new Material(17, 1));
        buildingTemplates.add(barnTemplate);

        VisualBuildingTemplate mineTemplate = new VisualBuildingTemplate();
        mineTemplate.setId(3);
        mineTemplate.setName("Iron Mine (Iron Tools)");
        mineTemplate.setDefConstructionTime(30);
        mineTemplate.setDefConstructionCost(30);
        mineTemplate.getInputMaterials().add(new Material(17, 1));
        mineTemplate.getOutputMaterials().add(new Material(12, 10));
        mineTemplate.getConstructionMaterials().add(new Material(6, 5));
        mineTemplate.getConstructionMaterials().add(new Material(7, 5));
        mineTemplate.getConstructionMaterials().add(new Material(17, 1));
        buildingTemplates.add(mineTemplate);

        rules.setMaterialTemplates(materialTemplates);
        rules.setBuildingTemplates(buildingTemplates);
        return rules;
    }

    /**
     * Lightweight Accessor Method
     * @return The only instance of this class.
     */
    public static Configurator getInstance() {
        return instance;
    }
}
