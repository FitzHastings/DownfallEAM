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
import javafx.scene.paint.Color;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility Class of the application. It defines a lot of static parameters that should not change.
 * This class is not instatiable if you want to get its only instance use getInstance() method.
 */
public final class DownfallUtil {
    public final static String BUILDINGS_EDITOR_FXML_PATHNAME = "fxml/editors/BuildingsEditor.fxml";
    public final static String MATERIALS_EDITOR_FXML_PATHNAME = "fxml/editors/MaterialsEditor.fxml";
    public final static String TAGS_EDITOR_FXML_PATHNAME = "fxml/editors/TagsEditor.fxml";
    public final static String REALM_EDITOR_FXML_PATHNAME = "fxml/editors/RealmEditor.fxml";
    public final static String DOWNFALL_MAIN_FXML_PATHNAME = "fxml/main/DownfallMain.fxml";
    public final static String REALM_SCREEN_FXML_PATHNAME = "fxml/main/tabs/RealmScreen.fxml";


    public final static String DEFAULT_SAVEGAME_PATHNAME = "save/testsave.xml";
    public final static String DEFAULT_MATERIAL_GFX_PATHNAME = "gfx/materials/orb.png";
    public final static String DEFAULT_BUILDING_GFX_PATHNAME = "gfx/buildings/houseSmall.png";

    public final static String DEFAULT_CONFIG_PATHNAME = "conf/conf.xml";
    public final static String DEFAULT_RULES_PATHNAME = "rules/default.xml";

    public final static String MAIN_CSS_RESOURCE = "css/Eraconstas.css";

    public final static Integer DEFAULT_MATERIAL_AMOUNT = 10;
    public final static String DEFAULT_LOGGER = "DownfallEAM";

    public final static Color BACKGROUND_COLOR = Color.web("#181e21");
    public final static Color HIGHLIGHT_COLOR = Color.web("#a34097");
    public final static Color CONFIRM_COLOR = Color.web("#40A34C");


    private URL URLRealmScreenFXML;
    private URL URLMaterialsEditorFXML;
    private URL URLRealmEditorFXML;
    private URL URLBuildingsEditorFXML;
    private URL URLTagsEditorFXML;
    private URL URLDownfallMainFXML;

    private final static DownfallUtil instance = new DownfallUtil();

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
            URLTagsEditorFXML = new URL("file:" + TAGS_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            informOfMalformedURL("file:" + TAGS_EDITOR_FXML_PATHNAME);
        }

        try {
            URLDownfallMainFXML = new URL("file:" + DOWNFALL_MAIN_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            informOfMalformedURL("file:" + DOWNFALL_MAIN_FXML_PATHNAME);
        }

        try {
            URLRealmScreenFXML = new URL("file:" + REALM_SCREEN_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            informOfMalformedURL("file:" + REALM_SCREEN_FXML_PATHNAME);
        }

        try {
            URLRealmEditorFXML = new URL("file:" + REALM_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            informOfMalformedURL("file:" + REALM_EDITOR_FXML_PATHNAME);
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
     * Lightweight accessor method.
     * @return URL to an FXML file that contains Tags Editor.
     */
    public URL getURLTagsEditorFXML() {
        return URLTagsEditorFXML;
    }

    /**
     * Lightweight accessor method.
     * @return  URL to an FXML file that contains the Realm Editor.
     */
    public URL getURLRealmEditorFXML() { return URLRealmEditorFXML; }

    /**
     * Lightweight accessor method.
     * @return URL to an FXML file that contains Downfall Main UI.
     */
    public URL getURLDownfallMainFXML() {
        return URLDownfallMainFXML;
    }

    /**
     * Lightweight accessor method.
     * @return URL to an FXML file that contains Realm Screen.
     */
    public URL getURLRealmScreenFXML() {
        return URLRealmScreenFXML;
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
