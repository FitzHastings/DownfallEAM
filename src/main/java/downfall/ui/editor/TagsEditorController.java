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

package downfall.ui.editor;

import downfall.fx.SimpleTableEditor;
import downfall.fx.fetcher.SimpleTagFetcher;
import downfall.realm.Tag;
import downfall.ui.StageController;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public final class TagsEditorController implements StageController {
    @FXML
    CheckBox isFactionalCheckBox;

    @FXML
    Button okButton;

    @FXML
    TextField tagTextField;

    @FXML
    SimpleTableEditor<Tag> tagTableEditor;

    @FXML
    SplitPane rootPane;

    ObservableList<Tag> tags = FXCollections.emptyObservableList();

    Stage stage;

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed.
     */
    @FXML
    public void initialize() {
        //init css
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);

        //retrieving full list of tags in current rules.
        tags = FXCollections.observableList(Configurator.getInstance().getRules().getActorTags());

        //configuring Table Editor
        tagTableEditor.setFetcher(new SimpleTagFetcher());

        //Configuring Table View
        tagTableEditor.getTableView().setItems(tags);
        tagTableEditor.getTableView().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Configuring Columns
        TableColumn<Tag, String> tagColumn = new TableColumn<>();
        tagColumn.setText("Tag");
        tagColumn.setCellValueFactory(param -> param.getValue().tagProperty());

        tagTableEditor.getTableView().getColumns().add(tagColumn);

        //Listening for changes in selection made by the user in tag table view to update data displayed.
        tagTableEditor.getTableView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != null)
                unbindTag(oldValue);
            displayTag(newValue);
        });

        //other inits
        okButton.setOnAction(e-> this.stage.close());
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Binds the properties of a given tag to all TextFields and CheckBoxes.
     * @param tag Tag to be unbound.
     */
    private void unbindTag(Tag tag) {
        tagTextField.textProperty()             .unbindBidirectional(tag.tagProperty());
        isFactionalCheckBox.selectedProperty()  .unbindBidirectional(tag.isFactionalProperty());
    }

    /**
     * Unbinds the properties of a given tag from all TextFields and CheckBoxes.
     * @param tag Tag to be displayed.
     */
    private void displayTag(Tag tag) {
        tagTextField.textProperty()             .bindBidirectional(tag.tagProperty());
        isFactionalCheckBox.selectedProperty()  .bindBidirectional(tag.isFactionalProperty());
    }
}
