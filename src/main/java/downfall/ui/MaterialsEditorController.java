package downfall.ui;

import downfall.fx.LogoTableColumn;
import downfall.fx.SimpleTableEditor;
import downfall.fx.fetcher.Fetcher;
import downfall.fx.fetcher.SimpleMaterialTemplateFetcher;
import downfall.realm.template.VisualMaterialTemplate;
import downfall.util.Configurator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaterialsEditorController implements StageController {
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
    private Button fileChooserButton;

    ObservableList<VisualMaterialTemplate> materials = FXCollections.emptyObservableList();

    Stage stage;

    @FXML
    public void initialize() {
        //retrieving the list of material templates in current rules.
        materials = FXCollections.observableList(Configurator.getInstance().getRules().getMaterialTemplates());

        Fetcher fetcher = new SimpleMaterialTemplateFetcher();
        //fetcher.initialize(stage, materials);
        materialTemplateEditor.setFetcher(fetcher);
        materialTemplateEditor.getTableView().setItems(materials);
        materialTemplateEditor.getTableView().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<VisualMaterialTemplate, String> materialNameColumn = new TableColumn<>();

        //Creating Table Columns for the editor to display
        materialNameColumn.setText("Material");
        materialNameColumn.setCellValueFactory(param -> param.getValue().nameProperty());

        LogoTableColumn<VisualMaterialTemplate> materialImageColumn = new LogoTableColumn<>();
        materialImageColumn.setDefaultSizePolicy();

        materialImageColumn.setCellValueFactory(param -> param.getValue().pathToGFXProperty());

        materialTemplateEditor.getTableView().getColumns().addAll(materialImageColumn, materialNameColumn);

        //listening for changes in selection made by the user in materials table view to update data displayed.
        materialTemplateEditor.getTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue == null || validateMaterial(oldValue)) {
                if(oldValue != null)
                    unbindMaterial(oldValue);
                displayMaterial(newValue);
            }
        });

        disableTradable();

        isExportableCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue)
                enableTradable();
            else
                disableTradable();
        });

        okButton.setOnAction(e-> stage.close());

    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void disableTradable() {
        exportPriceTextField.setDisable(true);
    }

    private void enableTradable() {
        exportPriceTextField.setDisable(false);
    }

    private void unbindMaterial(VisualMaterialTemplate template) {
        nameTextField.textProperty().unbindBidirectional(template.nameProperty());
        isExportableCheckBox.selectedProperty().unbindBidirectional(template.isExportableProperty());
        pathToGFXTextField.textProperty().unbindBidirectional(template.pathToGFXProperty());
        exportPriceTextField.textProperty().unbindBidirectional(template.defExportPriceProperty());
        importPriceTextField.textProperty().unbindBidirectional(template.defImportPriceProperty());
    }

    private void displayMaterial(VisualMaterialTemplate materialTemplate) {
        nameTextField.textProperty().bindBidirectional(materialTemplate.nameProperty());
        isExportableCheckBox.selectedProperty().bindBidirectional(materialTemplate.isExportableProperty());
        pathToGFXTextField.textProperty().bindBidirectional(materialTemplate.pathToGFXProperty());
        exportPriceTextField.textProperty().bindBidirectional(materialTemplate.defExportPriceProperty(), new NumberStringConverter());
        importPriceTextField.textProperty().bindBidirectional(materialTemplate.defImportPriceProperty(), new NumberStringConverter());
    }

    private boolean validateMaterial(VisualMaterialTemplate materialTemplate) {
        File checkFile = new File(pathToGFXTextField.getText());
        if(checkFile.canRead()) {
            return true;
        } else {
            Logger.getAnonymousLogger().log(Level.WARNING, "Could not Find file: "+pathToGFXTextField.getText()+" when trying to check integrity during material template save.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Could not find file: "+pathToGFXTextField.getText());
            alert.setContentText(pathToGFXTextField.getText()+" must be a path to a valid readable file");
            alert.showAndWait();
            return false;
        }
    }

    private void saveChanges() {
        Configurator.getInstance().getRules().getMaterialTemplates().clear();
        Configurator.getInstance().getRules().getMaterialTemplates().addAll(materials);
        Configurator.getInstance().saveRules();
    }
}
