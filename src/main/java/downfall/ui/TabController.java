package downfall.ui;

/**
 * Interface for all controllers that are controlling a tab in a larger Scene.
 * @param <T> Type that is displayed in the tab as an item. Usually is the same as what the parent is meant to display or edit.
 */
public interface TabController<T> {
    /**
     * sets the item to be displayed by the controller. Should be called before the scene is shown.
     * @param item item to be displayed or edited.
     */
    void setItem(T item);
}
