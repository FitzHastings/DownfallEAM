package downfall.fx.fetcher;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * An interface for a visual fetcher that uses JavaFX to display a selection of instances of T before returning an instance of T on request
 * @param <T> class of an instance to be returned by the retrieve method
 */
public interface VisualFetcher<T> extends  Fetcher<T>{
    /**
     * A method that initializes the visual elements of the VisualFetcher. It should always be run before its retrieve method
     * @param parent a parent stage that will be set as an owner of the stage displayed by the VisualFetcher
     * @param items a list of items to be displayed by the VisualFetcher
     */
    void initialize(Stage parent, ObservableList<T> items);

}
