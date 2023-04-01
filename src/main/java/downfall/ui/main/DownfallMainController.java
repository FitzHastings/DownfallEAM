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
import downfall.fx.LogoTableColumn;
import downfall.fx.MaximizeButton;
import downfall.fx.MinimizeButton;
import downfall.realm.*;
import downfall.realm.template.VisualMaterialTemplate;
import downfall.ui.StageController;
import downfall.ui.editor.BuildingsEditorController;
import downfall.ui.editor.MaterialsEditorController;
import downfall.ui.editor.RealmEditorController;
import downfall.ui.editor.TagsEditorController;
import downfall.ui.main.tabs.RealmScreenController;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private MenuItem tagsEditItem;

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
    private MenuItem configureItem;

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

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label treasuryLabel;

    @FXML
    private TableView<Material> stockpileTableView;

    private Stage stage;

    private Double xOffset;

    private Double yOffset;

    final RealmScreenController realmScreenController = new RealmScreenController();

    private static final String STOCKPILE_NAME_COLUMN_NAME = "Stockpile";
    private static final String STOCKPILE_AMOUNT_COLUMN_NAME = "#";
    private static final Integer STOCKPILE_AMOUNT_COLUMN_WIDTH = 50;

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {
        materialsEditItem.setOnAction(e -> openEditor(DownfallUtil.getInstance().getURLMaterialsEditorFXML(), new MaterialsEditorController(), "Materials Editor"));
        buildingsEditItem.setOnAction(e -> openEditor(DownfallUtil.getInstance().getURLBuildingsEditorFXML(), new BuildingsEditorController(), "Buildings Editor"));
        tagsEditItem     .setOnAction(e -> openEditor(DownfallUtil.getInstance().getURLTagsEditorFXML(),      new TagsEditorController(),      "Tags Editor"));

        newRealm.setOnAction(e -> {
            openEditor(DownfallUtil.getInstance().getURLRealmEditorFXML(), new RealmEditorController(), "New Realm");
            update();
        });

        importRulesItem.setOnAction(e -> importRules());
        exportRulesItem.setOnAction(e -> exportRules());
        loadRealm.setOnAction(e -> loadRealmAction());
        saveRealm.setOnAction(e -> saveRealmAction());
        saveRealmTo.setOnAction(e -> saveRealmToAction());

        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);

        minimizeButton.setStage(stage);
        maximizeButton.setStage(stage);
        exitButton.setStage(stage);

        menuBar.setOnMousePressed(e->{
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });

        menuBar.setOnMouseDragged(e-> {
            if (!stage.isMaximized()) {
                stage.setX(e.getScreenX() + xOffset);
                stage.setY(e.getScreenY() + yOffset);
            }
        });

        //intialize Stockpile TableView
        LogoTableColumn<Material> stockpileLogoColumn = new LogoTableColumn<>();
        stockpileLogoColumn.setDefaultSizePolicy();
        stockpileLogoColumn.setCellValueFactory(e-> {
            VisualMaterialTemplate template = Configurator.getInstance().findMaterialTemplate(e.getValue());
            if(template == null)
                Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "VisualMaterialTemplate expected from Configuration returned null");
            return template.pathToGFXProperty();
        });

        TableColumn<Material, String> stockpileNameColumn = new TableColumn<>(STOCKPILE_NAME_COLUMN_NAME);
        stockpileNameColumn.setCellValueFactory(e ->{
            VisualMaterialTemplate template = Configurator.getInstance().findMaterialTemplate(e.getValue());
            if(template == null)
                Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "VisualMaterialTemplate expected from Configuration returned null");
            return template.nameProperty();
        });

        TableColumn<Material, Integer> stockpileAmountColumn = new TableColumn<>(STOCKPILE_AMOUNT_COLUMN_NAME);
        stockpileAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockpileAmountColumn.setCellValueFactory(e -> e.getValue().amountProperty().asObject());
        stockpileAmountColumn.setEditable(true);
        stockpileAmountColumn.setPrefWidth(STOCKPILE_AMOUNT_COLUMN_WIDTH);
        stockpileAmountColumn.setMinWidth(STOCKPILE_AMOUNT_COLUMN_WIDTH*1.5);
        stockpileAmountColumn.setMaxWidth(STOCKPILE_AMOUNT_COLUMN_WIDTH*2);

        stockpileTableView.getColumns().addAll(stockpileLogoColumn, stockpileNameColumn, stockpileAmountColumn);
        stockpileTableView.setItems(Configurator.getInstance().getUserRealm().getStockpile());

        initializeTabs();

        update();
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
            stage.initOwner(this.stage);
            stage.setTitle(title);
            stage.showAndWait();
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
     * Executes general update sequence for all scene elements.
     */
    private void update() {
        treasuryLabel.textProperty().bindBidirectional(Configurator.getInstance().getUserRealm().treasuryProperty(), new NumberStringConverter());

        updateTabs();
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
