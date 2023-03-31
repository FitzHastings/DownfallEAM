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

package downfall.fx.fetcher;

import downfall.fx.LogoTableColumn;
import downfall.fx.css.DarkerHBox;
import downfall.realm.Material;
import downfall.realm.template.VisualMaterialTemplate;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Implementation of a ConversionFetcher that generates a new instance Material based on a user selected VisualMaterialTemplate
 * it uses TableView to display items provided to it in its initialize method. It does not support multiple selections being made
 */
public class ConversionMaterialFetcher extends VBox implements ConversionFetcher<Material, VisualMaterialTemplate>{
    private final TableView<VisualMaterialTemplate> tableView = new TableView<>();
    private Stage stage;
    private Boolean selectionIsMade = false;

    /**
     * Constructs all the visual elements of the ConversionMaterialFetcher
     */
    public ConversionMaterialFetcher() {
        //init css
        this.getStylesheets().clear();
        this.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);

        //sets the alignment of  the buttons at the bottom of the scene
        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");
        DarkerHBox buttonHBox = new DarkerHBox();
        buttonHBox.getChildren().addAll(okButton, cancelButton);
        buttonHBox.setPadding(new Insets(10));
        buttonHBox.setSpacing(10.0);
        buttonHBox.setAlignment(Pos.CENTER_RIGHT);

        tableView.setMaxHeight(Double.MAX_VALUE);
        getChildren().addAll(tableView, buttonHBox);
        VBox.setVgrow(tableView, Priority.ALWAYS);

        LogoTableColumn<VisualMaterialTemplate> logoColumn = new LogoTableColumn<>();
        logoColumn.setDefaultSizePolicy();
        logoColumn.setCellValueFactory(e-> e.getValue().pathToGFXProperty());

        TableColumn<VisualMaterialTemplate, String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(e-> e.getValue().nameProperty());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(logoColumn, nameColumn);

        okButton.setOnAction(e -> {
            if(tableView.getSelectionModel().getSelectedItem() != null)
                selectionIsMade = true;
            stage.close();
        });

        cancelButton.setOnAction(e -> stage.close());
    }

    /**
     * Creates a new stage that will be owned by the parent Stage. sets the provided list of items to  It should always be run before its retrieve method
     * @param parent a parent stage that will be set as an owner of the stage displayed by the ConversionMaterialFetcher if a null is provided it will not set any owners for the stage.
     * @param items a list of items to be displayed in the TableView if a null is provided it will load a list in the current ruleset
     */
    @Override
    public void initialize(Stage parent, ObservableList<VisualMaterialTemplate> items) {
        stage = new Stage();
        if(parent != null)
            stage.initOwner(parent);

        Scene scene = new Scene(this);
        stage.setScene(scene);
        tableView.setItems(Objects.requireNonNullElseGet(items, () -> FXCollections.observableList(Configurator.getInstance().getRules().getMaterialTemplates())));
    }

    /**
     * This method will show a new stage with showAndWait(). after a selection is made by the user this function will return a new Material
     * @return a new instance of Material created from a VisualMaterialTemplate if a selection is made in the table, if it wasn't made returns null instead.
     */
    @Override
    public Material retrieve() {
        stage.showAndWait();
        if(selectionIsMade)
            return new Material(tableView.getSelectionModel().getSelectedItem(), DownfallUtil.DEFAULT_MATERIAL_AMOUNT);
        return null;
    }
}
