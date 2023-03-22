package downfall.ui;

import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownfallMainController implements StageController {
    @FXML
    private MenuItem buildingsEditItem;

    @FXML
    private MenuItem materialsEditItem;

    @FXML
    private MenuItem importRulesItem;

    @FXML
    private MenuItem exportRulesItem;

    private Stage stage;

    @FXML
    public void initialize() {
        materialsEditItem.setOnAction(e -> openEditor(DownfallUtil.getInstance().getURLMaterialsEditorFXML(), new MaterialsEditorController(), "Materials Editor"));

        buildingsEditItem.setOnAction(e -> openEditor(DownfallUtil.getInstance().getURLBuildingsEditorFXML(), new BuildingsEditorController(), "Buildings Editor"));

        importRulesItem.setOnAction(e -> importRules());

        exportRulesItem.setOnAction(e -> exportRules());
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void openEditor(URL editorFXMLURL, StageController controller, String title) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(editorFXMLURL);
            loader.setController(controller);
            controller.setStage(stage);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportRules() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Rules");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml rules file","*.xml"));
        fileChooser.setInitialDirectory(new File("rules"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null)
            Configurator.getInstance().saveRules(selectedFile.getPath());
    }

    private void importRules() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Rules");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml rules file","*.xml"));
        fileChooser.setInitialDirectory(new File("rules"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null)
            Configurator.getInstance().loadAndApplyRules(selectedFile.getPath());
    }
}
