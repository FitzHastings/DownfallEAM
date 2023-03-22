package downfall;

import downfall.ui.DownfallMainController;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DownfallMain extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Configurator configurator = Configurator.getInstance();
        configurator.loadConfiguration();
        configurator.loadAndApplyRules();

        stage.setTitle("Downfall v0.1");

        stage.setOnCloseRequest(e -> {
            configurator.saveRules();
            configurator.saveConfiguration();
        });

        FXMLLoader loader = new FXMLLoader(DownfallUtil.getInstance().getURLDownfallMainFXML());
        loader.setController(new DownfallMainController());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
