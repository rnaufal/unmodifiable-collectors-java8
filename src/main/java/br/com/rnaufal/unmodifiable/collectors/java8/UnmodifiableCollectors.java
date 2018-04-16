package br.com.rnaufal.unmodifiable.collectors.java8;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

/**
 * Created by rnaufal on 16/04/2018.
 */
public interface UnmodifiableCollectors {

    static <T> Collector<T, ?, Collection<T>> toUnmodifiableCollection(final Supplier<? extends Collection<T>> factory) {
        return collectingAndThen(Collectors.toCollection(factory), Collections::unmodifiableCollection);
    }

    static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
        return collectingAndThen(Collectors.toList(), Collections::unmodifiableList);
    }

    static <T> Collector<T, ?, Set<T>> toUnmodifiableSet() {
        return collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet);
    }

    static <T, K, V> Collector<T, ?, Map<K, V>> toUnmodifiableMap(final Function<? super T, ? extends K> keyMapper,
                                                                  final Function<? super T, ? extends V> valueMapper) {
        return collectingAndThen(toMap(keyMapper, valueMapper), Collections::unmodifiableMap);
    }

    static <T, K, V> Collector<T, ?, Map<K, V>> toUnmodifiableMap(final Function<? super T, ? extends K> keyMapper,
                                                                  final Function<? super T, ? extends V> valueMapper,
                                                                  final BinaryOperator<V> mergeFunction) {
        return collectingAndThen(toMap(keyMapper, valueMapper, mergeFunction), Collections::unmodifiableMap);
    }

    static <T, K, V> Collector<T, ?, Map<K, V>> toUnmodifiableMap(final Function<? super T, ? extends K> keyMapper,
                                                                  final Function<? super T, ? extends V> valueMapper,
                                                                  final BinaryOperator<V> mergeFunction,
                                                                  final Supplier<? extends Map<K, V>> factory) {
        return collectingAndThen(toMap(keyMapper, valueMapper, mergeFunction, factory), Collections::unmodifiableMap);
    }
}
