package downfall.fx;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX Button that creates a FileChooser on action. the FileChooser is pre-configured to filter for images. Its initial directory is the gfx folder in the installation directory
 */
public class ImageChooserButton extends Button {
    TextInputControl output;

    /**
     *  Default constructor. If you use the default constructor you must set output before the button can be triggered by the user.
     */
    public ImageChooserButton() {
        this(null);
    }

    /**
     * If you use this constructor the text title of the button will be set the default value of to "..."
     * @param output a TextInputControl into which pathname to the chosen image will be written
     */
    public ImageChooserButton(TextInputControl output) {
        super();
        textProperty().set("...");
        this.output = output;
        initBehaviour();
    }

    /**
     *
     * @param output a TextInputControl into which pathname to the chosen image will be written
     * @param title text that is going to be displayed on the button.
     */
    public ImageChooserButton(TextInputControl output,String title) {
        super(title);
        this.output = output;
        initBehaviour();
    }

    /**
     * Lightweight mutator method
     * @param output a TextInputControl into which pathname to the chosen image will be written
     */
    public void setOutput(TextInputControl output) {
        this.output = output;
    }

    /**
     * Initializes the default behaviour of the button.
     */
    private void initBehaviour() {
        setOnAction(e->{
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image","*.png"));
            chooser.setInitialDirectory(new File("gfx"));
            chooser.setTitle("Choose Your Image Wisely");
            File fileChosen = chooser.showOpenDialog(this.getScene().getWindow());
            if(output != null)
                output.setText(fileChosen.getPath());
            else
                Logger.getLogger("Downfall").log(Level.SEVERE, "Attempting to set Text without setting an output first.");
        });
    }
}
