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

import downfall.ui.StageController;
import downfall.util.DownfallUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RealmEditorController implements StageController {

    @FXML
    VBox rootPane;

    Stage stage = new Stage();

    @FXML
    public void initialize() {
        //init css
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DownfallUtil.MAIN_CSS_RESOURCE);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
