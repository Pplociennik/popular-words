package pl.sii.transformation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static pl.sii.interruptions.InterruptionsConsts.*;

public class MapDivider {

    public static Map<String, Long> getSubMap(Map<String, Long> baseMap, Integer startIndex, Integer endIndex) {
        List<Map.Entry<String, Long>> elements = new ArrayList<>(0);
        Map<String, Long> result = new LinkedHashMap<>();

        if (startIndex < 0) {
            throw new IllegalArgumentException(TOO_SMALL_ARGUMENT);
        }
        if (endIndex > baseMap.size()) {
            throw new IllegalArgumentException(TOO_LARGE_ARGUMENT);
        }
        if (startIndex > endIndex) {
            throw new IllegalArgumentException(BAD_ARGUMENTS_VALUES);
        }

        elements.addAll(baseMap.entrySet());
        for (int i = startIndex; i < endIndex; i++) {
            result.put(elements.get(i).getKey(), elements.get(i).getValue());
        }
        return result;
    }
}
