package pl.sii;

import org.junit.Test;
import pl.sii.transformation.ValueDescComparator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class ValueDescComparatorTest {
    private static Map<String, Long> testMap;

    static {
        testMap = new LinkedHashMap<>();
        testMap.put("First", 1L);
        testMap.put("Second", 2L);
        testMap.put("Third", 3L);
    }

    private static final ValueDescComparator testComparator = new ValueDescComparator(testMap);

    @Test
    public void shouldReturnOneWhenElementsHaveDifferentValuesAndFirstValueIsLesser() {
        int result = testComparator.compare("First", "Third");
        assertEquals(1, result);
    }

    @Test
    public void shouldReturnOneWhenElementsHaveTheSameValues() {
        int result = testComparator.compare("Second", "Second");
        assertEquals(1, result);
    }

    @Test
    public void shouldReturnNegativeOneWhenElementsHaveDifferentValuesAndFirstValueIsGreater() {
        int result = testComparator.compare("Second", "First");
        assertEquals(-1, result);
    }

    @Test
    public void shouldReturnMapInDescendingOrder() {

        //given
        Map<String, Long> givenMap = new LinkedHashMap<>();
        givenMap.put("Third", 3L);
        givenMap.put("Second", 2L);
        givenMap.put("First", 1L);

        //should sort map descending
        Map<String, Long> toCompareMap = new TreeMap<>(new ValueDescComparator(testMap));
        toCompareMap.putAll(testMap);

        assertEquals(givenMap.entrySet(), toCompareMap.entrySet());
    }
}
