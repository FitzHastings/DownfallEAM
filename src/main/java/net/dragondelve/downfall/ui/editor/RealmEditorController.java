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

package net.dragondelve.downfall.ui.editor;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import net.dragondelve.downfall.realm.Material;
import net.dragondelve.downfall.realm.Realm;
import net.dragondelve.downfall.realm.Tag;
import net.dragondelve.downfall.realm.template.VisualMaterialTemplate;
import net.dragondelve.downfall.ui.StageController;
import net.dragondelve.downfall.util.Configurator;
import net.dragondelve.downfall.util.DownfallUtil;
import net.dragondelve.mabel.button.ImageChooserButton;
import net.dragondelve.mabel.button.LogoTableColumn;
import net.dragondelve.mabel.button.SimpleTableEditor;
import net.dragondelve.mabel.fetcher.ConversionFetcher;
import net.dragondelve.mabel.fetcher.ConversionMaterialFetcher;
import net.dragondelve.mabel.fetcher.VisualFetcher;
import net.dragondelve.mabel.fetcher.VisualTagFetcher;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class for the Realm Editor. It is responsible for the creation of new Realms.
 * Controls /fxml/editors/RealmEditor.fxml and is annotated with @FXML where it references that FXML file.
 */
public class RealmEditorController implements StageController {
    private static final String STOCKPILE_NAME_COLUMN_NAME = "Stockpile";
    private static final String STOCKPILE_AMOUNT_COLUMN_NAME = "Amount";
    Stage stage = new Stage();
    @FXML
    private Button cancelButton;
    @FXML
    private Button createButton;
    @FXML
    private TextField diploRepTextField;
    @FXML
    private TextField infamyTextField;
    @FXML
    private TextField legitimacyTextField;
    @FXML
    private TextField powerProjectionTextField;
    @FXML
    private TextField prestigeTextField;
    @FXML
    private ImageChooserButton realmGFXButton;
    @FXML
    private TextField realmGFXTextField;
    @FXML
    private TextField realmNameTextField;
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageChooserButton rulerGFXButton;
    @FXML
    private TextField rulerGFXTextField;
    @FXML
    private TextField treasuryTextField;
    @FXML
    private TextField stabilityTextField;
    @FXML
    private SimpleTableEditor<Tag> tagEditor;
    @FXML
    private SimpleTableEditor<Material> stockpileEditor;

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {

        //init css
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);

        Realm realm = new Realm();

        //bind textFields
        realmNameTextField.textProperty().bindBidirectional(realm.nameProperty());
        treasuryTextField.textProperty().bindBidirectional(realm.treasuryProperty(), new NumberStringConverter());
        diploRepTextField.textProperty().bindBidirectional(realm.diplomaticReputationProperty(), new NumberStringConverter());
        powerProjectionTextField.textProperty().bindBidirectional(realm.powerProjectionProperty(), new NumberStringConverter());
        legitimacyTextField.textProperty().bindBidirectional(realm.legitimacyProperty(), new NumberStringConverter());
        prestigeTextField.textProperty().bindBidirectional(realm.prestigeProperty(), new NumberStringConverter());
        infamyTextField.textProperty().bindBidirectional(realm.infamyProperty(), new NumberStringConverter());
        stabilityTextField.textProperty().bindBidirectional(realm.stabilityProperty(), new NumberStringConverter());
        realmGFXTextField.textProperty().bindBidirectional(realm.realmPathToGFXProperty());
        rulerGFXTextField.textProperty().bindBidirectional(realm.rulerPathToGFXProperty());

        //init Tag Editor
        TableColumn<Tag, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setCellValueFactory(e -> e.getValue().tagProperty());

        VisualFetcher<Tag> visualTagFetcher = new VisualTagFetcher();
        visualTagFetcher.initialize(stage, FXCollections.observableList(Configurator.getInstance().getRules().getActorTags()));
        tagEditor.setFetcher(visualTagFetcher);
        tagEditor.setItems(realm.getTags());
        tagEditor.getTableView().getColumns().add(tagColumn);

        //Init file chooser buttons
        realmGFXButton.setOutput(realmGFXTextField);
        rulerGFXButton.setOutput(rulerGFXTextField);

        //init other buttons.
        cancelButton.setOnAction(e -> stage.close());
        createButton.setOnAction(e -> {
            Configurator.getInstance().setUserRealm(realm);
            stage.close();
        });

        //init stockpile editor
        LogoTableColumn<Material> stockpileLogoColumn = new LogoTableColumn<>();
        stockpileLogoColumn.setDefaultSizePolicy();
        stockpileLogoColumn.setCellValueFactory(e -> {
            VisualMaterialTemplate template = Configurator.getInstance().findMaterialTemplate(e.getValue());
            if (template == null)
                Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "VisualMaterialTemplate expected from Configuration returned null");
            return Objects.requireNonNull(template).pathToGFXProperty();
        });

        TableColumn<Material, String> stockpileNameColumn = new TableColumn<>(STOCKPILE_NAME_COLUMN_NAME);
        stockpileNameColumn.setCellValueFactory(e -> {
            VisualMaterialTemplate template = Configurator.getInstance().findMaterialTemplate(e.getValue());
            if (template == null)
                Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "VisualMaterialTemplate expected from Configuration returned null");
            return Objects.requireNonNull(template).nameProperty();
        });

        TableColumn<Material, Integer> stockpileAmountColumn = new TableColumn<>(STOCKPILE_AMOUNT_COLUMN_NAME);
        stockpileAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockpileAmountColumn.setCellValueFactory(e -> e.getValue().amountProperty().asObject());
        stockpileAmountColumn.setEditable(true);

        ConversionFetcher<Material, VisualMaterialTemplate> visualMaterialFetcher = new ConversionMaterialFetcher();
        visualMaterialFetcher.initialize(stage, FXCollections.observableList(Configurator.getInstance().getRules().getMaterialTemplates()));
        stockpileEditor.setFetcher(visualMaterialFetcher);
        stockpileEditor.setItems(realm.getStockpile());
        stockpileEditor.getTableView().getColumns().addAll(stockpileLogoColumn, stockpileNameColumn, stockpileAmountColumn);
    }

    /**
     * Lightweight mutator method.
     * Always should be called before the editor is displayed to the user.
     *
     * @param stage Stage on which this controller is displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
