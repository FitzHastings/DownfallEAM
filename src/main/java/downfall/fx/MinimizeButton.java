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

package downfall.fx;

import downfall.util.DownfallUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Button that minimizes the stage that is provided to it on action.
 */
public class MinimizeButton extends StageControlButton{
    @Override
    protected void initBehaviour() {
        this.setOnAction(e -> {
        if(stage != null)
            stage.setIconified(true);
        else
            Logger.getLogger(DownfallUtil.DEFAULT_LOGGER).log(Level.WARNING, "MinimizeButton attempted to minimize a stage that was equal to null.");
        });
    }
}