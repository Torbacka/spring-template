package io.klira.lendify.model.mapping;

public interface Adapter<T,U> {
    U transform(T event);
}
