package downfall.fx.fetcher;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * An interface for a visual fetcher that uses JavaFX to display a selection of instances of C before returning an instance of T on request.
 * @param <T> an instance of this class will be returned by the retrieve method.
 * @param <C> a list of this class will be displayed to the user
 */
public interface ConversionFetcher<T, C> extends Fetcher<T> {
    /**
     * A method that initializes the visual elements of the VisualFetcher. It should always be run before its retrieve method
     * @param parent a parent stage that will be set as an owner of the stage displayed by the VisualFetcher
     * @param items a list of items to be displayed by the VisualFetcher
     */
    void initialize(Stage parent, ObservableList<C> items);
}
