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

package downfall;

import downfall.ui.main.DownfallMainController;
import downfall.util.Configurator;
import downfall.util.DownfallUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main application class of Downfall.
 */
public final class DownfallMain extends Application {
    /**
     * Launches the JavaFX application.
     * @param args Program arguments. Ignored.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * start method of the client application
     * @param stage primary stage of the application
     * @throws Exception any uncaught exception.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Configurator configurator = Configurator.getInstance();
        configurator.loadConfiguration();
        configurator.loadAndApplyRules();

        stage.setTitle("Downfall v0.1.1");
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setOnCloseRequest(e -> {
            configurator.saveRules();
            configurator.saveConfiguration();
        });

        stage.setWidth(1260);
        stage.setHeight(700);
        stage.setMinHeight(650);
        stage.setMinWidth(1200);
        FXMLLoader loader = new FXMLLoader(DownfallUtil.getInstance().getURLDownfallMainFXML());
        DownfallMainController controller = new DownfallMainController();
        controller.setStage(stage);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
