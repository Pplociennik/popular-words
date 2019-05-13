package pl.sii.transformation;

import java.util.Comparator;
import java.util.Map;

public class ValueDescComparator implements Comparator<String> {

    private Map<String, Long> baseMap;

    public ValueDescComparator(Map<String, Long> baseMap) {
        this.baseMap = baseMap;
    }

    public int compare(String o1, String o2) {
        return (baseMap.get(o1) <= baseMap.get(o2) ? 1 : -1);
    }
}
