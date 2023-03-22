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

package downfall.util;

import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownfallUtil {
    public final static String BUILDINGS_EDITOR_FXML_PATHNAME = "fxml/BuildingsEditor.fxml";
    public final static String MATERIALS_EDITOR_FXML_PATHNAME = "fxml/MaterialsEditor.fxml";
    public final static String DOWNFALL_MAIN_FXML_PATHNAME = "fxml/DownfallMain.fxml";

    public final static String DEFAULT_MATERIAL_GFX_PATHNAME = "gfx/materials/orb.png";
    public final static String DEFAULT_BUILDING_GFX_PATHNAME = "gfx/buildings/houseSmall.png";

    public final static String DEFAULT_CONFIG_PATHNAME = "conf/conf.xml";
    public final static String DEFAULT_RULES_PATHNAME = "rules/default.xml";

    public final static int DEFAULT_MATERIAL_AMOUNT = 10;

    private URL URLMaterialsEditorFXML;
    private URL URLBuildingsEditorFXML;
    private URL URLDownfallMainFXML;

    private final static DownfallUtil  instance = new DownfallUtil();

    public static DownfallUtil getInstance() {
        return instance;
    }

    private DownfallUtil() {
        try {
            URLMaterialsEditorFXML = new URL("file:" + MATERIALS_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            informOfMalformedURL("file:" + MATERIALS_EDITOR_FXML_PATHNAME);
        }

        try {
            URLBuildingsEditorFXML = new URL("file:" + BUILDINGS_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            informOfMalformedURL("file:" + BUILDINGS_EDITOR_FXML_PATHNAME);
        }

        try {
            URLDownfallMainFXML = new URL("file:" + DOWNFALL_MAIN_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            informOfMalformedURL("file:" + DOWNFALL_MAIN_FXML_PATHNAME);
        }
    }

    public URL getURLMaterialsEditorFXML() {
        return URLMaterialsEditorFXML;
    }

    public URL getURLBuildingsEditorFXML() {
        return URLBuildingsEditorFXML;
    }

    public URL getURLDownfallMainFXML() {
        return URLDownfallMainFXML;
    }

    public Image loadImage(String pathname) {
       return new Image("file:" + pathname);
    }

    private void informOfMalformedURL(String URL) {
        Logger.getLogger("Downfall").log(Level.SEVERE, "URL: "+URL+" seems to be malformed.");
    }
}
