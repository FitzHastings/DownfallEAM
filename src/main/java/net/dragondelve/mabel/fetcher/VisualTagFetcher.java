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

package net.dragondelve.mabel.fetcher;

import net.dragondelve.downfall.realm.Tag;
import net.dragondelve.downfall.util.Configurator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Implementation of a VisualFetcher that returns a Tag selected in the Table
 * it uses TableView to display items provided to it in its initialize method. It does not support multiple selections being made.
 */
public class VisualTagFetcher extends TableFetcherBase<Tag> implements VisualFetcher<Tag> {

    /**
     * Default Constructor
     */
    public VisualTagFetcher() {
        TableColumn<Tag, String> tagColumn = new TableColumn<>();
        tagColumn.setCellValueFactory(e-> e.getValue().tagProperty());

        tableView.getColumns().add(tagColumn);
    }

    /**
     * Creates a new stage that will be owned by the parent Stage. sets the provided list of items to the Table View.  It should always be run before its retrieve method.
     * @param parent a parent stage that will be set as an owner of the stage displayed by the VisualTagFetcher
     * @param items a list of items to be displayed by the VisualTagFetcher
     */
    @Override
    public void initialize(Stage parent, ObservableList<Tag> items) {
        stage = new Stage();
        if(parent != null)
            stage.initOwner(parent);

        Scene scene = new Scene(this);
        stage.setScene(scene);
        tableView.setItems(Objects.requireNonNullElseGet(items, () -> FXCollections.observableList(Configurator.getInstance().getRules().getActorTags())));
    }

    /**
     * This method will show a new stage with showAndWait(). after a selection is made by the user this function will return a Tag selected.
     * @return a tag selected from a TableView if a selection is made in the table, if there is no selection made returns null instead.
     */
    @Override
    public Tag retrieve() {
        stage.showAndWait();

        if(selectionIsMade)
            return tableView.getSelectionModel().getSelectedItem();
        return null;
    }
}
