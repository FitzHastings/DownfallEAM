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
import downfall.realm.Tag;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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
    private Label stabilityLabel;

    @FXML
    private Label realmNameLabel;

    @FXML
    private Pane stabilityCirclePane;

    @FXML
    private ListView<Tag> realmTagListView;

    @FXML
    private ListView<Tag> nonRealmTagListView;

    @FXML
    private Accordion tagsAccordion;

    @FXML
    private Pane realmPane;

    @FXML
    private Pane dynastyPane;

    @FXML
    private Pane negStabilityPane1;

    @FXML
    private Pane negStabilityPane2;

    @FXML
    private Pane negStabilityPane3;

    @FXML
    private Pane negStabilityPane4;

    @FXML
    private Pane negStabilityPane5;
    
    @FXML
    private Pane posStabilityPane1;

    @FXML
    private Pane posStabilityPane2;

    @FXML
    private Pane posStabilityPane3;

    @FXML
    private Pane posStabilityPane4;

    @FXML
    private Pane posStabilityPane5;

    private final static Double IMAGE_PANE_GAP = 10.0;
    private final static Double IMAGE_PANE_HEIGHT = 200.0;

    private final ObservableList<Tag> realmTags = FXCollections.observableArrayList();
    private final ObservableList<Tag> nonRealmTags = FXCollections.observableArrayList();

    /**
     * Initialize method that is called automatically after the FXML has finished loading. Initializes all UI elements before they are displayed
     */
    @FXML
    public void initialize() {
        realmTagListView.setItems(realmTags);
        nonRealmTagListView.setItems(nonRealmTags);
        tagsAccordion.setExpandedPane(tagsAccordion.getPanes().get(0));

        //Update ImageView Positions.
        realmImageView.setX(10);
        realmImageView.setY(10);
        dynastyImageView.setX(10);
        dynastyImageView.setY(10);

        update();
    }

    /**
     * Updates values on all labels if there was an action or an event that could have changed them.
     * Also updates ImageViews and the tag table.
     */
    public void update() {
        //update user realm
        Realm userRealm = Configurator.getInstance().getUserRealm();

        //update Labels
        realmNameLabel          .setText(userRealm.getName());
        diploRepLabel           .setText(userRealm.getDiplomaticReputation().toString());
        stabilityLabel          .setText(userRealm.getStability().toString());
        legitimacyLabel         .setText(userRealm.getLegitimacy().toString());
        powerProjectionLabel    .setText(userRealm.getPowerProjection().toString());
        infamyLabel             .setText(userRealm.getInfamy().toString());
        prestigeLabel           .setText(userRealm.getPrestige().toString());
        //TODO: Add Gouvernment Rank
        //govRankLabel.setText(userRealm.getGouvernmentRank());

        //update Image views
        updateImageView(realmPane, new Image("file:"+userRealm.getRealmPathToGFX()), realmImageView);
        updateImageView(dynastyPane, new Image("file:"+userRealm.getRulerPathToGFX()), dynastyImageView);

        //update Stability Bar
        updateStabilityBar();

        //update realm tags
        realmTags.clear();
        nonRealmTags.clear();
        userRealm.getTags().forEach(e->{
            if(e.isFactional())
                realmTags.add(e);
            else
                nonRealmTags.add(e);
        });
    }

    /**
     * Updates the given ImageView to contain the given Image, if image provided is not null, forces the width of the container to conform to the ImageView's Image width
     * modified so that the image keeps its aspect ratio, plus some gaps that are defined in IMAGE_PANE_GAP. If the image equals to null it forces the ImageView
     * to conform to IMAGE_PANE_HEIGHT in both Width and Height.
     * @param container Container Pane that contains the ImageView. It will be resized to have a height of IMAGE_PANE_HEIGHT. It's width will depend on the aspect ratio of the Image provided.
     * @param image Image to be set into the ImageView. It's aspect ratio is used to define container's width.
     * @param imageView Image view to be set with a given image. It's best if ImageView's parent is the container argument, but it's not required.
     */
    private void updateImageView(Pane container, Image image, ImageView imageView) {
        imageView.setImage(image);
        if(image != null && !image.isError()) {
            double ratio = imageView.getFitHeight()/image.getHeight();
            forceDimensions(container, IMAGE_PANE_HEIGHT, realmImageView.getImage().getWidth() * ratio + IMAGE_PANE_GAP * 2);
        } else
            forceDimensions(container, IMAGE_PANE_HEIGHT, IMAGE_PANE_HEIGHT);
    }

    /**
     * Forces the dimension of the pane to conform to the parameters specified.
     * @param pane Pane whose width and height you want to fix to particular values.
     * @param height Height to which you want to set the pane's height.
     * @param width Width to which you want to set the pane's width.
     */
    private void forceDimensions(Pane pane, Double height, Double width) {
        pane.setMinWidth(width);
        pane.setMaxWidth(width);
        pane.setPrefWidth(width);

        pane.setMinHeight(height);
        pane.setPrefHeight(height);
        pane.setMaxHeight(height);
    }

    /**
     * Updates the Stability bar to display the current stability in a visual way.
     */
    private void updateStabilityBar() {
        //update user realm
        BackgroundFill negFill = new BackgroundFill(DownfallUtil.HIGHLIGHT_COLOR, null, null);
        BackgroundFill posFill = new BackgroundFill(DownfallUtil.CONFIRM_COLOR, null, null);
        BackgroundFill neutralFill = new BackgroundFill(DownfallUtil.BACKGROUND_COLOR, null, null);

        updateStabilityPane(negStabilityPane5, neutralFill, negFill, -5.0, true);
        updateStabilityPane(negStabilityPane4, neutralFill, negFill, -4.0, true);
        updateStabilityPane(negStabilityPane3, neutralFill, negFill, -3.0, true);
        updateStabilityPane(negStabilityPane2, neutralFill, negFill, -2.0, true);
        updateStabilityPane(negStabilityPane1, neutralFill, negFill, -1.0, true);

        updateStabilityPane(posStabilityPane1, neutralFill, posFill, 1.0, false);
        updateStabilityPane(posStabilityPane2, neutralFill, posFill, 2.0, false);
        updateStabilityPane(posStabilityPane3, neutralFill, posFill, 3.0, false);
        updateStabilityPane(posStabilityPane4, neutralFill, posFill, 4.0, false);
        updateStabilityPane(posStabilityPane5, neutralFill, posFill, 5.0, false);
    }

    /**
     *  Binds the background property to a new Background that is created with one of the two fills depending on wether the Stability of the realm "exceeds" the threshold given.
     *  "exceeds" for the purposes of this method means <= to threshold if lessThanThreshold is true and >= if it is false.
     * @param stabilityPane Pane to whose background needs to be updated.
     * @param neutralFill BackgroundFill to be used if the userRealm's stability does not "exceed" the threshold.
     * @param activeFill BackgroundFill to be used if the userRealm's stability "exceeds" the threshold.
     * @param threshold Value of the threshold that needs to be "exceeded" in order for the active fill to be used instead of neutralFill.
     * @param lessThanThreshold Controls if the threshold is "exceeded" when the UserRealm's stability is <= or >= than the threshold provided.
     */
    private void updateStabilityPane( Pane stabilityPane, BackgroundFill neutralFill, BackgroundFill activeFill, Double threshold,Boolean lessThanThreshold) {
        //TODO:Find a more refined way of doing this.
        if (lessThanThreshold) {
            if (Configurator.getInstance().getUserRealm().getStability() <= threshold)
                stabilityPane.backgroundProperty().bind(new SimpleObjectProperty<>(new Background(activeFill)));
            else
                stabilityPane.backgroundProperty().bind(new SimpleObjectProperty<>(new Background(neutralFill)));
        } else {
            if (Configurator.getInstance().getUserRealm().getStability() >= threshold)
                stabilityPane.backgroundProperty().bind(new SimpleObjectProperty<>(new Background(activeFill)));
            else
                stabilityPane.backgroundProperty().bind(new SimpleObjectProperty<>(new Background(neutralFill)));
        }
    }
}
