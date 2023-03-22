package downfall.fx.fetcher;

/**
 * A simple interface for any class that returns an instance of T on request
 * @param <T> class of an instance to be returned by the retrieve method
 */
public interface Fetcher<T> {
    /**
     * This method is used to return an instance of T on request. It is not necessarily lightweight, don't use it as a get method.
     * @return an instance of T that can be selected from a pre generated list or newly created
     */
    T retrieve();
}
