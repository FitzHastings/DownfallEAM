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

package downfall.ui.main;

import downfall.fx.ExitButton;
import downfall.fx.MaximizeButton;
import downfall.fx.MinimizeButton;
import downfall.realm.*;
import downfall.ui.StageController;
import downfall.ui.editor.BuildingsEditorController;
import downfall.ui.editor.MaterialsEditorController;
import downfall.ui.main.tabs.RealmScreenController;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Controller for the main GUI of Downfall EAM.
 * Controls /fxml/DownfallMain.fxml and is annotated with @FXML where it references that FXML file.
 */
public class DownfallMainController implements StageController {
    @FXML
    private MenuItem buildingsEditItem;

    @FXML
    private MenuItem materialsEditItem;

    @FXML
    private MenuItem importRulesItem;

    @FXML
    private MenuItem newRealm;

    @FXML
    private MenuItem loadRealm;

    @FXML
    private MenuItem saveRealm;

    @FXML
    private MenuItem saveRealmTo;

    @FXML
    private MenuItem exportRulesItem;

    @FXML
    private AnchorPane realmAnchorPane;

    @FXML
    private BorderPane rootPane;

    @FXML
    private MinimizeButton minimizeButton;

    @FXML
    private MaximizeButton maximizeButton;

    @FXML
    private ExitButton exitButton;

    private Stage stage;

    RealmScreenController realmScreenController = new RealmScreenController();

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {
        materialsEditItem.setOnAction(e -> openEditor(DownfallUtil.getInstance().getURLMaterialsEditorFXML(), new MaterialsEditorController(), "Materials Editor"));
        buildingsEditItem.setOnAction(e -> openEditor(DownfallUtil.getInstance().getURLBuildingsEditorFXML(), new BuildingsEditorController(), "Buildings Editor"));

        importRulesItem.setOnAction(e -> importRules());
        exportRulesItem.setOnAction(e -> exportRules());
        newRealm.setOnAction(e -> newRealmAction());
        loadRealm.setOnAction(e -> loadRealmAction());
        saveRealm.setOnAction(e -> saveRealmAction());
        saveRealmTo.setOnAction(e -> saveRealmToAction());

        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);

        minimizeButton.setStage(stage);
        maximizeButton.setStage(stage);
        exitButton.setStage(stage);

        initializeTabs();
    }

    /**
     * Lightweight Mutator Method.
     * Always should be called before the stage is displayed to the user.
     * @param stage Stage on which this controller is displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates a stage on which an editor controlled by a StageController can be displayed.
     * Loads an FXML File from the URL, sets the controller and finally displays the stage.
     * @param editorFXMLURL URL to a FXML file that contains the editor's gui information.
     * @param controller controller to be used for the new Stage.
     * @param title text title to be displayed on the new Stage.
     */
    private void openEditor(URL editorFXMLURL, StageController controller, String title) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(editorFXMLURL);
            loader.setController(controller);
            controller.setStage(stage);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exports current ruleset to an XML File at a destination selected by a user with a JavaFX FileChooser.
     * Saves the new destination as lastLoadedRules in the Configuration.
     */
    private void exportRules() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Rules");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml rules file","*.xml"));
        fileChooser.setInitialDirectory(new File("rules"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null)
            Configurator.getInstance().saveRules(selectedFile.getPath());
    }

    /**
     * Imports and applies a ruleset from an XML File at a destination selected by a user with a JavaFX FileChooser.
     * Saves the new destination as lastLoadedRules in the Configuration
     */
    private void importRules() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Rules");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml rules file","*.xml"));
        fileChooser.setInitialDirectory(new File("rules"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null)
            Configurator.getInstance().loadAndApplyRules(selectedFile.getPath());
    }

    /**
     * loads the content of RealmScreen.fxml and puts its root node into the "Realm" tab.
     */
    private void initializeRealmTab() {
        try {
            FXMLLoader loader = new FXMLLoader(DownfallUtil.getInstance().getURLRealmScreenFXML());
            loader.setController(realmScreenController);
            Node screen = loader.load();
            realmAnchorPane.getChildren().add(screen);
            AnchorPane.setBottomAnchor(screen, 0.0);
            AnchorPane.setLeftAnchor(screen, 0.0);
            AnchorPane.setRightAnchor(screen, 0.0);
            AnchorPane.setTopAnchor(screen, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes all Tabs with their respective content.
     */
    private void initializeTabs() {
        initializeRealmTab();
    }

    /**
     * Forces an update on all tabs.
     */
    private void updateTabs() {
        updateRealmTab();
    }

    /**
     * Forces an upgrade on Realm Tab
     */
    private void updateRealmTab() {
        realmScreenController.update();
    }

    /**
     * Generates new test realm, and sets it as a user's realm.
     */
    private void newRealmAction() {
        Configurator.getInstance().getUserRealm().setInfamy(10);
        Configurator.getInstance().getUserRealm().setLegitimacy(50);
        Configurator.getInstance().getUserRealm().setDiplomaticReputation(1);
        Configurator.getInstance().getUserRealm().setPrestige(50);
        Configurator.getInstance().getUserRealm().setStability(0.0);
        Configurator.getInstance().getUserRealm().setPowerProjection(0);
        Configurator.getInstance().getUserRealm().setTreasury(24000);
        Configurator.getInstance().getUserRealm().setName("Test Realm");

        ObservableList<Material> materials = FXCollections.observableArrayList();
        materials.add(new Material(1, 10));
        materials.add(new Material(2,10));
        materials.add(new Material(3,20));
        materials.add(new Material(4, 20));
        materials.add(new Material(5, 20));
        materials.add(new Material(6, 10));
        materials.add(new Material(7, 5));
        materials.add(new Material(8, 5));
        materials.add(new Material(9, 10));
        materials.add(new Material(10, 20));
        Configurator.getInstance().getUserRealm().setStockpile(materials);

        ObservableList<Tag> tags = FXCollections.observableArrayList();
        tags.add(new Tag(1,"Reichstpakt", true));
        tags.add(new Tag(2,"Test Faction", true));
        tags.add(new Tag(3,"Custom Faction", true));
        tags.add(new Tag(4,"Player Faction", true));
        tags.add(new Tag(5,"Test Tag", false));
        tags.add(new Tag(6,"Custom Tag", false));
        tags.add(new Tag(7,"Player Tag", false));
        tags.add(new Tag(8,"Bim Bam Bom", false));
        Configurator.getInstance().getUserRealm().setTags(tags);

        ObservableList<Building> buildings = FXCollections.observableArrayList();
        buildings.add(new Building(1, true));
        buildings.add(new Building(1, true));
        buildings.add(new Building(2, false));
        Configurator.getInstance().getUserRealm().setOwnedBuildings(buildings);
    }

    /**
     * Loads test realm
     */
    private void loadRealmAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Savegame");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml save file","*.xml"));
        fileChooser.setInitialDirectory(new File("save"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null) {
            Configurator.getInstance().getSaveManager().loadFrom(selectedFile.getPath());
            updateTabs();
        }
    }

    /**
     * Saves test realm
     */
    private void saveRealmAction() {
        Configurator.getInstance().getSaveManager().saveToLast();
    }

    /**
     * Saves test realm to filepath
     */
    private void saveRealmToAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Savegame");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml save file","*.xml"));
        fileChooser.setInitialDirectory(new File("save"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null)
            Configurator.getInstance().getSaveManager().saveTo(selectedFile.getPath());
    }
 }
