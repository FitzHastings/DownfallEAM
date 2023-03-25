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

package downfall.ui.main.tabs;

import downfall.realm.Realm;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * A controller that controls the content of the Realm Tab.
 * It displays user's realm Data allowing the user to see a quick overview of the state of their realm.
 */
public class RealmScreenController {
    @FXML
    private Label diploRepLabel;

    @FXML
    private ImageView dynastyImageView;

    @FXML
    private Label govRankLabel;

    @FXML
    private Label infamyLabel;

    @FXML
    private Label legitimacyLabel;

    @FXML
    private Label nationalUnrestLabel;

    @FXML
    private Label nuicpLabel;

    @FXML
    private Label powerProjectionLabel;

    @FXML
    private Label prestigeLabel;

    @FXML
    private ImageView realmImageView;

    @FXML
    private GridPane stabilityNegativeGrid;

    @FXML
    private Label stabilityPerMonthLabel;

    @FXML
    private GridPane stabilityPositiveGrid;

    @FXML
    private VBox rootPane;

    @FXML
    private Circle stabilityCircle;

    @FXML
    private Label stabilityLabel;

    @FXML
    private Pane stabilityCirclePane;

    Realm item = new Realm();

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add("/css/RealmScreen.css");

        //putting the circle and the label on top
    }
}
