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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import net.dragondelve.downfall.realm.Material;
import net.dragondelve.downfall.realm.template.VisualBuildingTemplate;
import net.dragondelve.downfall.realm.template.VisualMaterialTemplate;
import net.dragondelve.downfall.ui.StageController;
import net.dragondelve.downfall.util.Configurator;
import net.dragondelve.downfall.util.DownfallUtil;
import net.dragondelve.mabel.button.ImageChooserButton;
import net.dragondelve.mabel.button.LogoTableColumn;
import net.dragondelve.mabel.button.SimpleTableEditor;
import net.dragondelve.mabel.fetcher.ConversionFetcher;
import net.dragondelve.mabel.fetcher.ConversionMaterialFetcher;
import net.dragondelve.mabel.fetcher.SimpleBuildingTemplateFetcher;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class for the Buildings Editor. It is responsible for the creation and editing of VisualBuildingTemplates in the current ruleset.
 * Controls /fxml/editors/BuildingsEditor.fxml and is annotated with @FXML where it references that FXML file.
 */
public final class BuildingsEditorController implements StageController {

    ObservableList<VisualBuildingTemplate> buildingTemplates = FXCollections.emptyObservableList();
    ObservableList<Material> inputMaterials = FXCollections.emptyObservableList();
    ObservableList<Material> outputMaterials = FXCollections.emptyObservableList();
    ObservableList<Material> constructionMaterials = FXCollections.emptyObservableList();
    Stage stage;
    @FXML
    private SimpleTableEditor<VisualBuildingTemplate> buildingEditor;
    @FXML
    private TextField constructionCostField;
    @FXML
    private SimpleTableEditor<Material> inputMaterialEditor;
    @FXML
    private TextField constructionTimeField;
    @FXML
    private SimpleTableEditor<Material> outputMaterialEditor;
    @FXML
    private TextField nameTextField;
    @FXML
    private SimpleTableEditor<Material> constructionMaterialEditor;
    @FXML
    private ImageChooserButton pathToGFXButton;
    @FXML
    private TextField pathToGFXTextField;
    @FXML
    private Button okButton;
    @FXML
    private CheckBox operatesImmediatelyCheckBox;
    @FXML
    private BorderPane rootPane;

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {
        //init css
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);

        //gets all available building instances from the current configuration
        buildingTemplates = FXCollections.observableList(Configurator.getInstance().getRules().getBuildingTemplates());

        //Configuring editor
        buildingEditor.setItems(buildingTemplates);
        buildingEditor.setFetcher(new SimpleBuildingTemplateFetcher());
        buildingEditor.getTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null)
                unbindBuilding(oldValue);
            displayBuilding(newValue);
        });

        //Configuring table columns
        LogoTableColumn<VisualBuildingTemplate> buildingLogoColumn = new LogoTableColumn<>();
        buildingLogoColumn.setDefaultSizePolicy();
        buildingLogoColumn.setCellValueFactory(e -> e.getValue().pathToGFXProperty());

        TableColumn<VisualBuildingTemplate, String> buildingNameColumn = new TableColumn<>("Building");
        buildingNameColumn.setCellValueFactory(e -> e.getValue().nameProperty());
        buildingEditor.getTableView().getColumns().addAll(buildingLogoColumn, buildingNameColumn);

        //configuring material editors
        configureMaterialEditor(inputMaterialEditor, "Material", "Amount");
        configureMaterialEditor(outputMaterialEditor, "Material", "Amount");
        configureMaterialEditor(constructionMaterialEditor, "Material", "Amount");

        //other inits
        pathToGFXButton.setOutput(pathToGFXTextField);
        okButton.setOnAction(e -> stage.close());
    }

    /**
     * Lightweight mutator method.
     * Always should be called before the editor is displayed to the user.
     *
     * @param stage the stage that is displaying the editor.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Configures a SimpleTableEditor to have two columns. a Name column that will display the name of its template and an amount column that will display its amount.
     * It makes the amount column editable using TextFieldTableCell and sets up a ConversionFetcher that will generate a new material based on
     *
     * @param editor            Material editor to be configured.
     * @param nameColumnTitle   title text to be used for the name column
     * @param amountColumnTitle title text to be used for the amount column
     */
    private void configureMaterialEditor(SimpleTableEditor<Material> editor, String nameColumnTitle, String amountColumnTitle) {
        LogoTableColumn<Material> logoColumn = new LogoTableColumn<>();
        logoColumn.setDefaultSizePolicy();
        logoColumn.setCellValueFactory(e -> {
            VisualMaterialTemplate template = Configurator.getInstance().findMaterialTemplate(e.getValue());
            if (template == null)
                Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "VisualMaterialTemplate expected from Configuration returned null");
            return Objects.requireNonNull(template).pathToGFXProperty();
        });

        TableColumn<Material, String> nameColumn = new TableColumn<>(nameColumnTitle);
        nameColumn.setCellValueFactory(e -> {
            VisualMaterialTemplate template = Configurator.getInstance().findMaterialTemplate(e.getValue());
            if (template == null)
                Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "VisualMaterialTemplate expected from Configuration returned null");
            return Objects.requireNonNull(template).nameProperty();
        });


        TableColumn<Material, Integer> amountColumn = new TableColumn<>(amountColumnTitle);
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        amountColumn.setEditable(true);
        amountColumn.setCellValueFactory(e -> e.getValue().amountProperty().asObject());

        editor.getTableView().getColumns().addAll(logoColumn, nameColumn, amountColumn);

        ConversionFetcher<Material, VisualMaterialTemplate> conversionFetcher = new ConversionMaterialFetcher();
        //TODO: Investigate whether this conversion is a good idea;
        conversionFetcher.initialize(stage, FXCollections.observableList(Configurator.getInstance().getRules().getMaterialTemplates()));
        editor.setFetcher(conversionFetcher);
    }

    /**
     * removes all bindings from a Building Template
     *
     * @param template template to be unbound from the textFields
     */
    private void unbindBuilding(VisualBuildingTemplate template) {
        nameTextField.textProperty().unbindBidirectional(template.nameProperty());
        pathToGFXTextField.textProperty().unbindBidirectional(template.pathToGFXProperty());
        constructionCostField.textProperty().unbindBidirectional(template.defConstructionCostProperty());
        constructionTimeField.textProperty().unbindBidirectional(template.defConstructionTimeProperty());
        operatesImmediatelyCheckBox.selectedProperty().unbindBidirectional(template.operatesImmediatelyProperty());
    }

    /**
     * binds all properties of the building to GUI TextFields and sets the data of all tables to correspond with the selected template.
     *
     * @param template template that was selected by the user to be displayed and edited.
     */
    private void displayBuilding(VisualBuildingTemplate template) {
        nameTextField.textProperty().bindBidirectional(template.nameProperty());
        pathToGFXTextField.textProperty().bindBidirectional(template.pathToGFXProperty());
        constructionCostField.textProperty().bindBidirectional(template.defConstructionCostProperty(), new NumberStringConverter());
        constructionTimeField.textProperty().bindBidirectional(template.defConstructionTimeProperty(), new NumberStringConverter());
        operatesImmediatelyCheckBox.selectedProperty().bindBidirectional(template.operatesImmediatelyProperty());

        inputMaterialEditor.setItems(template.getInputMaterials());
        outputMaterialEditor.setItems(template.getOutputMaterials());
        constructionMaterialEditor.setItems(template.getConstructionMaterials());
    }
}
