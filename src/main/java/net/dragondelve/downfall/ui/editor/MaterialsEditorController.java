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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import net.dragondelve.downfall.realm.template.VisualMaterialTemplate;
import net.dragondelve.downfall.ui.StageController;
import net.dragondelve.downfall.util.Configurator;
import net.dragondelve.downfall.util.DownfallUtil;
import net.dragondelve.mabel.button.ImageChooserButton;
import net.dragondelve.mabel.button.LogoTableColumn;
import net.dragondelve.mabel.button.SimpleTableEditor;
import net.dragondelve.mabel.fetcher.SimpleMaterialTemplateFetcher;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class for the Materials Editor. It is responsible for the creation and editing of VisualMaterialTemplates in the current ruleset.
 * Controls /fxml/editors/MaterialsEditor.fxml and is annotated with @FXML where it references that FXML file.
 */
public final class MaterialsEditorController implements StageController {
    ObservableList<VisualMaterialTemplate> materials = FXCollections.emptyObservableList();
    Stage stage;
    @FXML
    private TextField exportPriceTextField;
    @FXML
    private TextField importPriceTextField;
    @FXML
    private SimpleTableEditor<VisualMaterialTemplate> materialTemplateEditor;
    @FXML
    private CheckBox isExportableCheckBox;
    @FXML
    private AnchorPane leftAnchor;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button okButton;
    @FXML
    private TextField pathToGFXTextField;
    @FXML
    private ImageChooserButton fileChooserButton;
    @FXML
    private SplitPane rootPane;

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {
        //init css
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);

        //retrieving the list of material templates in current rules.
        materials = FXCollections.observableList(Configurator.getInstance().getRules().getMaterialTemplates());

        //Configuring Template Editor
        materialTemplateEditor.setFetcher(new SimpleMaterialTemplateFetcher());

        //Configuring Table View
        materialTemplateEditor.getTableView().setItems(materials);
        materialTemplateEditor.getTableView().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Creating Table Columns for the editor to display
        TableColumn<VisualMaterialTemplate, String> materialNameColumn = new TableColumn<>();
        materialNameColumn.setText("Material");
        materialNameColumn.setCellValueFactory(param -> param.getValue().nameProperty());

        LogoTableColumn<VisualMaterialTemplate> materialImageColumn = new LogoTableColumn<>();
        materialImageColumn.setDefaultSizePolicy();
        materialImageColumn.setCellValueFactory(param -> param.getValue().pathToGFXProperty());

        materialTemplateEditor.getTableView().getColumns().addAll(materialImageColumn, materialNameColumn);

        //listening for changes in selection made by the user in materials table view to update data displayed.
        materialTemplateEditor.getTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                if (!validateMaterial(oldValue))
                    Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "Material Editor Controller saving an invalid tag");
                else
                    unbindMaterial(oldValue);
            }

            displayMaterial(newValue);
        });

        //other inits
        disableTradable();

        isExportableCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                enableTradable();
            else
                disableTradable();
        });

        fileChooserButton.setOutput(pathToGFXTextField);
        okButton.setOnAction(e -> stage.close());
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

    /**
     * Disables the exportPriceTextField as you should not set a price for non-tradeable materials.
     */
    private void disableTradable() {
        exportPriceTextField.setDisable(true);
    }

    /**
     * Enables the exportPriceTextField as you should be abel to set a price for tradeable materials.
     */
    private void enableTradable() {
        exportPriceTextField.setDisable(false);
    }

    /**
     * Unbinds the properties of a given materials from all TextFields and CheckBoxes.
     *
     * @param template template to be unbound.
     */
    private void unbindMaterial(VisualMaterialTemplate template) {
        nameTextField.textProperty().unbindBidirectional(template.nameProperty());
        isExportableCheckBox.selectedProperty().unbindBidirectional(template.isExportableProperty());
        pathToGFXTextField.textProperty().unbindBidirectional(template.pathToGFXProperty());
        exportPriceTextField.textProperty().unbindBidirectional(template.defExportPriceProperty());
        importPriceTextField.textProperty().unbindBidirectional(template.defImportPriceProperty());
    }

    /**
     * Binds the properties of a given material to all TextFields and CheckBoxes.
     *
     * @param materialTemplate template to be displayed
     */
    private void displayMaterial(VisualMaterialTemplate materialTemplate) {
        nameTextField.textProperty().bindBidirectional(materialTemplate.nameProperty());
        isExportableCheckBox.selectedProperty().bindBidirectional(materialTemplate.isExportableProperty());
        pathToGFXTextField.textProperty().bindBidirectional(materialTemplate.pathToGFXProperty());
        exportPriceTextField.textProperty().bindBidirectional(materialTemplate.defExportPriceProperty(), new NumberStringConverter());
        importPriceTextField.textProperty().bindBidirectional(materialTemplate.defImportPriceProperty(), new NumberStringConverter());
    }

    /**
     * Checks that it can read a file that is set as a pathToGFX.
     *
     * @param materialTemplate VisualMaterialTemplate to be validated.
     * @return true if file can be read. False if it cannot be read.
     */
    private boolean validateMaterial(VisualMaterialTemplate materialTemplate) {
        File checkFile = new File(pathToGFXTextField.getText());
        if (checkFile.canRead()) {
            return true;
        } else {
            Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "Could not Find file: " + pathToGFXTextField.getText() + " when trying to check integrity during material template save.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Could not find file: " + pathToGFXTextField.getText());
            alert.setContentText(pathToGFXTextField.getText() + " must be a path to a valid readable file");
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Removes the old Material Templates from the current Ruleset, adds all materialTemplates in materials field, and then force saves the rules at a lastLoadedRules location.
     */
    @Deprecated
    private void saveChanges() {
        Configurator.getInstance().getRules().getMaterialTemplates().clear();
        Configurator.getInstance().getRules().getMaterialTemplates().addAll(materials);
        Configurator.getInstance().saveRules();
    }
}
