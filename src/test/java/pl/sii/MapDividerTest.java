package pl.sii;

import org.junit.Test;
import pl.sii.transformation.MapDivider;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapDividerTest {
    private static Map<String, Long> testMap;

    static {
        testMap = new LinkedHashMap<>();
        testMap.put("First", 1L);
        testMap.put("Second", 2L);
        testMap.put("Third", 3L);
    }

    @Test
    public void mapDividerShouldReturnMapOfSizeOne() {
        Map<String, Long> newMap = MapDivider.getSubMap(testMap, 0, 1);
        assertEquals(1, newMap.size());
    }

    @Test
    public void mapDividerShouldReturnMapOfSizeTwo() {
        Map<String, Long> newMap = MapDivider.getSubMap(testMap, 1, 3);
        assertEquals(2, newMap.size());
    }

    @Test
    public void mapDividerShouldReturnEmptyMap() {
        Map<String, Long> newMap = MapDivider.getSubMap(testMap, 0, 0);
        assertTrue(newMap.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapDividerShouldThrowAnIllegalArgumentExceptionCausedByTooSmallStartIndex() {
        MapDivider.getSubMap(testMap, -1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapDividerShouldThrowAnIllegalArgumentExceptionCausedByTooLargeEndIndex() {
        MapDivider.getSubMap(testMap, 0, 4);
    }

    @Test
    public void shouldReturnElementOfValueThreeAfterRemovingFirtsAndSecondElement() {
        Map<String, Long> newMap = MapDivider.getSubMap(testMap, 2, 3);
        long value = newMap.get("Third");
        assertEquals(3L, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapDividerShouldThrowAnIllegalArgumentExceptionCausedByStartIndexGreaterThanTheEndOne() {
        MapDivider.getSubMap(testMap, 3, 1);
    }

    @Test
    public void mapDividerShouldReturnTheSameMapAsTheEntry() {
        Map<String, Long> entryMap = testMap;
        Map<String, Long> finalMap = MapDivider.getSubMap(entryMap, 0, entryMap.size());
        assertEquals(entryMap, finalMap);
    }

}
