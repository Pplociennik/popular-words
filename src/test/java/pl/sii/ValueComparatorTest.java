package pl.sii;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ValueComparatorTest {
    private static Map<String, Long> testMap;

    static {
        testMap = new LinkedHashMap<>();
        testMap.put("First", 1L);
        testMap.put("Second", 2L);
        testMap.put("Third", 3L);
    }

    private static final ValueComparator testComparator = new ValueComparator(testMap);

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
}
