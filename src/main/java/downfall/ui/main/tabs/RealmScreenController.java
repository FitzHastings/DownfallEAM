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

import downfall.fx.css.BorderLabel;
import downfall.fx.css.TitleLabel;
import downfall.realm.Realm;
import downfall.realm.Tag;
import downfall.util.Configurator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * A controller that controls the content of the Realm Tab.
 * It displays user's realm Data allowing the user to see a quick overview of the state of their realm.
 */
public class RealmScreenController {
    @FXML
    private BorderLabel diploRepBorderLabel;

    @FXML
    private ImageView dynastyImageView;

    @FXML
    private BorderLabel govRankBorderLabel;

    @FXML
    private BorderLabel infamyBorderLabel;

    @FXML
    private BorderLabel legitimacyBorderLabel;

    @FXML
    private BorderLabel nationalUnrestBorderLabel;

    @FXML
    private BorderLabel nuicpBorderLabel;

    @FXML
    private BorderLabel powerProjectionBorderLabel;

    @FXML
    private BorderLabel prestigeBorderLabel;

    @FXML
    private ImageView realmImageView;

    @FXML
    private GridPane stabilityNegativeGrid;

    @FXML
    private BorderLabel stabilityPerMonthBorderLabel;

    @FXML
    private GridPane stabilityPositiveGrid;

    @FXML
    private VBox rootPane;

    @FXML
    private TitleLabel stabilityTitleLabel;

    @FXML
    private Pane stabilityCirclePane;

    @FXML
    private ListView<Tag> realmTagListView;

    @FXML
    private ListView<Tag> nonRealmTagListView;

    @FXML
    private Accordion tagsAccordion;

    final ObservableList<Tag> realmTags = FXCollections.observableArrayList();
    final ObservableList<Tag> nonRealmTags = FXCollections.observableArrayList();

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {
        realmTagListView.setItems(realmTags);

        nonRealmTagListView.setItems(nonRealmTags);
        tagsAccordion.setExpandedPane(tagsAccordion.getPanes().get(0));

        update();
    }

    /**
     * Updates values on all labels if there was an action or an event that could have changed them.
     */
    public void update() {
        Realm userRealm = Configurator.getInstance().getUserRealm();
        diploRepBorderLabel.setText(userRealm.getDiplomaticReputation().toString());
        stabilityTitleLabel.setText(userRealm.getStability().toString());
        legitimacyBorderLabel.setText(userRealm.getLegitimacy().toString());
        infamyBorderLabel.setText(userRealm.getInfamy().toString());
        //TODO: Add Gouvernment Rank
        //govRankBorderLabel.setText(userRealm.getGouvernmentRank());
        prestigeBorderLabel.setText(userRealm.getPrestige().toString());

        realmTags.clear();
        nonRealmTags.clear();
        userRealm.getTags().forEach(e->{
            if(e.isFactional())
                realmTags.add(e);
            else
                nonRealmTags.add(e);
        });

    }
}
