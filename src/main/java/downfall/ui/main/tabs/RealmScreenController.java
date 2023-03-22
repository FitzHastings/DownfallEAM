package downfall.ui.main.tabs;

import downfall.realm.Realm;
import downfall.ui.TabController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class RealmScreenController implements TabController<Realm> {
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

    Realm item = new Realm();

    @FXML
    public void initialize() {

    }

    @Override
    public void setItem(Realm item) {
        this.item = item;
    }
}
