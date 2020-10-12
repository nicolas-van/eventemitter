package eventemitter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.NonNull;

/**
 * A very simple implementation of an event emitter, made to use Java's lambda expressions
 * extensively.
 *
 * <p>Is thread safe.
 *
 * <p>Is also exception safe as all exception occurring in consumers are redirected to an uncaught
 * exception handler.
 */
public class EventEmitter<E> {

    final List<Consumer<E>> lst = new ArrayList<>();

    /**
     * The uncaught exception handler. It is used whenever an exception is thrown in one of the
     * consumers.
     *
     * <p>Note that the uncaught exception handler should never throw exceptions. If that happens
     * anyway the exception will be ignored.
     */
    @Getter final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * Constructs an event emitter with an exception handler redirecting to
     * `Thread.getDefaultUncaughtExceptionHandler()` if it is defined. If is is not defined the
     * exceptions will be ignored.
     */
    public EventEmitter() {
        this(
                (t, e) -> {
                    Thread.UncaughtExceptionHandler tmp =
                            Thread.getDefaultUncaughtExceptionHandler();
                    if (tmp != null) {
                        tmp.uncaughtException(t, e);
                    }
                });
    }

    /**
     * Constructs an event emitter by specifying the exception handler.
     *
     * @param ueh_ The exception handler
     */
    public EventEmitter(@NonNull Thread.UncaughtExceptionHandler ueh_) {
        uncaughtExceptionHandler = ueh_;
    }

    /**
     * Adds a consumer to the list of consumers.
     *
     * @param c The consumer
     */
    public synchronized void addConsumer(Consumer<E> c) {
        lst.add(c);
    }

    /**
     * Removes a consumer from the list consumers. If the consumer is not found in the list this
     * operation has no effect.
     *
     * @param c The consumer
     */
    public synchronized void removeConsumer(Consumer<E> c) {
        lst.remove(c);
    }

    /**
     * Triggers all consumers with the specified argument.
     *
     * <p>Any exception occurring in a consumer will be redirected to the uncaught exception
     * handler.
     *
     * <p>A copy of the consumers list is created before invoking them. This allows the list to be
     * mutated during event triggering.
     *
     * @param arg The argument
     */
    public void trigger(E arg) {
        List<Consumer<E>> nlst;
        synchronized (this) {
            nlst = new ArrayList<>(lst);
        }
        for (Consumer<E> c : nlst) {
            try {
                c.accept(arg);
            } catch (Throwable th) {
                try {
                    uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
                } catch (Throwable th2) {
                    // do nothing
                }
            }
        }
    }
}
