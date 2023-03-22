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

/**
 * Utility Class of the application. It defines a lot of static parameters that should not change.
 * This class is not instatiable if you want to get its only instance use getInstance() method.
 */
public class DownfallUtil {
    public final static String BUILDINGS_EDITOR_FXML_PATHNAME = "fxml/BuildingsEditor.fxml";
    public final static String MATERIALS_EDITOR_FXML_PATHNAME = "fxml/MaterialsEditor.fxml";
    public final static String DOWNFALL_MAIN_FXML_PATHNAME = "fxml/DownfallMain.fxml";

    public final static String DEFAULT_MATERIAL_GFX_PATHNAME = "gfx/materials/orb.png";
    public final static String DEFAULT_BUILDING_GFX_PATHNAME = "gfx/buildings/houseSmall.png";

    public final static String DEFAULT_CONFIG_PATHNAME = "conf/conf.xml";
    public final static String DEFAULT_RULES_PATHNAME = "rules/default.xml";

    public final static int DEFAULT_MATERIAL_AMOUNT = 10;
    public final static String DEFAULT_LOGGER = "DownfallEAM";

    private URL URLMaterialsEditorFXML;
    private URL URLBuildingsEditorFXML;
    private URL URLDownfallMainFXML;

    private final static DownfallUtil  instance = new DownfallUtil();

    public static DownfallUtil getInstance() {
        return instance;
    }

    /**
     * Default Constructor method. Constructs all necessary URLs to all fxml files
     */
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

    /**
     * Lightweight accessor method.
     * @return URL to an FXML file that contains Materials Editor.
     */
    public URL getURLMaterialsEditorFXML() {
        return URLMaterialsEditorFXML;
    }

    /**
     * Lightweight accessor method.
     * @return URL to an FXML file that contains Buildings Editor.
     */
    public URL getURLBuildingsEditorFXML() {
        return URLBuildingsEditorFXML;
    }

    /**
     * Lightweight Accessor method.
     * @return URL to an FXML file that contains Downfall Main UI.
     */
    public URL getURLDownfallMainFXML() {
        return URLDownfallMainFXML;
    }

    /**
     *  Loads an image from a given pathname.
     *  Lightweight method. Quickly constructs a URL by adding "file:" to a given pathname and returns a new image made from that URL
     * @param pathname pathname to an image file
     * @return Image loaded from the pathname.
     */
    public Image loadImage(String pathname) {
       return new Image("file:" + pathname);
    }

    /**
     * Informs the logger that a given URL seems to be malformed.
     * @param URL URL that returned a MalformedURException upon being constructed
     */
    private void informOfMalformedURL(String URL) {
        Logger.getLogger(DEFAULT_LOGGER).log(Level.SEVERE, "URL: "+URL+" seems to be malformed.");
    }
}
