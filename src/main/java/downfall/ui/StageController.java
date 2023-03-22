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

package downfall.ui;

import javafx.stage.Stage;

/**
 * Interface for every JavaFX FXML controller that controls an entire Stage.
 * If you create a controller that controls the entire stage you should pass them the stage on which
 * they are displayed for convenience.
 */
public interface StageController {
    //TODO:Consider this becoming a class instead of an interface (?)
    /**
     * Should be a Lightweight mutator method. Always pass the stage before the UI content becomes visible for the user.
     * @param stage Stage on which this controller is displayed.
     */
    void setStage(Stage stage);
}
