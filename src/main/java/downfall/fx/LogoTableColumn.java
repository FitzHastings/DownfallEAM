package downfall.fx;

import downfall.util.DownfallUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;

/**
 * a JavaFX TableColumn that displays an image for each row that corresponds to pathname of this cell's String value
 * @param <S> Class of an object that is displayed in the TableView
 */
public class LogoTableColumn<S> extends TableColumn<S, String> {
    public static final double DEFAULT_IMAGE_HEIGHT = 24;
    public static final double DEFAULT_IMAGE_WIDTH = 24;
    public static final double DEFAULT_COLUMN_WIDTH = 32;

    private final DoubleProperty imageHeightProperty = new SimpleDoubleProperty(DEFAULT_IMAGE_HEIGHT);
    private final DoubleProperty imageWidthProperty = new SimpleDoubleProperty(DEFAULT_IMAGE_WIDTH);

    /**
     * Lightweight accessor method
     * @return image height property
     */
    DoubleProperty ImageHeightProperty() {
        return imageHeightProperty;
    }

    /**
     * Lightweight accessor method
     * @return image width property
     */
    DoubleProperty ImageWidthProperty() {
        return imageWidthProperty;
    }

    /**
     * Lightweight accessor method
     * @return numeric value of imageHeightProperty
     */
    public Double getImageHeight() {
        return imageHeightProperty.get();
    }

    /**
     * Lightweight accessor method
     * @return numeric value of imageWidthProperty
     */
    public Double getImageWidth() {
        return imageWidthProperty.get();
    }

    /**
     * Lightweight mutator method
     * @param height desired height of the image inside the column
     */
    public void setImageHeight(Double height) {
        imageHeightProperty.setValue(height);
    }

    /**
     * Lightweight mutator method
     * @param width desired width of the image inside the column
     */
    public void setImageWidth(Double width) {
        imageWidthProperty.setValue(width);
    }

    /**
     * Default constructor. does not initialize the column title.
     */
    public LogoTableColumn() {
        super();
        initializeCells();
    }

    /**
     * Constructor that initializes the column title
     * @param name title text of the column.
     */
    public LogoTableColumn(String name) {
        super(name);
        initializeCells();
    }

    /**
     * Sets default resize policy for the column. It is recommended to call this method before the column is displayed.
     */
    public void setDefaultSizePolicy() {
        this.minWidthProperty().setValue(DEFAULT_COLUMN_WIDTH);
        this.prefWidthProperty().setValue(DEFAULT_COLUMN_WIDTH);
        this.maxWidthProperty().setValue(DEFAULT_COLUMN_WIDTH);
    }

    /**
     * Initializes the cell factory for this column to display an image that is loaded from pathname of the String value of the column
     */
    private void initializeCells() {
        setCellFactory(param -> {
            ImageView view = new ImageView();
            view.fitHeightProperty().bind(imageHeightProperty);
            view.fitWidthProperty().bind(imageWidthProperty);
            TableCell<S, String> cell = new TableCell<>() {
                public void updateItem(String item, boolean empty) {
                    if (item != null) {
                        view.setImage(DownfallUtil.getInstance().loadImage(item));
                    } else
                        view.setImage(null);
                }
            };
            cell.setGraphic(view);
            return cell;
        });
    }
}
