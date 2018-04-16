package br.com.rnaufal.unmodifiable.collectors.java8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

import static br.com.rnaufal.unmodifiable.collectors.java8.UnmodifiableCollectors.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by rnaufal on 16/04/2018.
 */
public class UnmodifiableCollectorsTest {

    @Test
    @DisplayName("Should throw exception when adding to unmodifiable list")
    public void throwsExceptionWhenAddingToUnmodifiableList() {
        final List<String> namesSizeGreaterThan5 = Stream.of("Smith", "Thompson", "Thompson", "Susan", "Steven")
                .filter(name -> name.length() > 5)
                .collect(toUnmodifiableList());

        assertThrows(UnsupportedOperationException.class, () -> namesSizeGreaterThan5.add("Paul"));
    }

    @Test
    @DisplayName("Should throw exception when adding to unmodifiable set")
    public void throwsExceptionWhenAddingToUnmodifiableSet() {
        final Set<String> fruitsStartingWithG = Stream.of("apple", "orange", "lemon", "grape")
                .filter(fruit -> fruit.startsWith("g"))
                .collect(toUnmodifiableSet());

        assertThrows(UnsupportedOperationException.class, () -> fruitsStartingWithG.add("pineapple"));
    }

    @Test
    @DisplayName("Should throw exception when adding to unmodifiable collection")
    public void throwsExceptionWhenAddingToUnmodifiableCollection() {
        final Collection<String> carsStartingWithC = Stream.of("Corvette", "Cadillac", "Mustang")
                .filter(fruit -> fruit.startsWith("c"))
                .collect(toUnmodifiableCollection(HashSet::new));

        assertThrows(UnsupportedOperationException.class, () -> carsStartingWithC.add("chrysler"));
    }

    @Test
    @DisplayName("Should throw exception when adding to unmodifiable map")
    public void throwsExceptionWhenAddingToUnmodifiableMap() {
        final Map<String, String> values = Stream.of(entry("a", "1"), entry("b", "2"), entry("c", "3"))
                .collect(toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));

        assertThrows(UnsupportedOperationException.class, () -> values.putIfAbsent("d", "4"));
    }

    @Test
    @DisplayName("Should throw exception when adding to unmodifiable map with merge function")
    public void throwsExceptionWhenAddingToUnmodifiableMapWithMergeFunction() {
        final Map<String, String> values = Stream.of(entry("a", "1"), entry("b", "2"), entry("c", "3"))
                .collect(toUnmodifiableMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (s1, s2) -> s1 + ", " + s2));

        assertThrows(UnsupportedOperationException.class, () -> values.putIfAbsent("d", "4"));
    }

    @Test
    @DisplayName("Should throw exception when adding to unmodifiable map with merge function and supplier")
    public void throwsExceptionWhenAddingToUnmodifiableMapWithMergeAndSupplierFunctions() {
        final Map<String, String> values = Stream.of(entry("a", "1"), entry("b", "2"), entry("c", "3"))
                .collect(toUnmodifiableMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (s1, s2) -> s1 + ", " + s2,
                        TreeMap::new));

        assertThrows(UnsupportedOperationException.class, () -> values.putIfAbsent("d", "4"));
    }

    private static <K, V> Map.Entry<K, V> entry(final K key, final V value) {
        return new SimpleEntry<>(key, value);
    }
}