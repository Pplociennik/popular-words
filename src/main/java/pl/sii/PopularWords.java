package pl.sii;

import pl.sii.transformation.MapDivider;
import pl.sii.transformation.ValueDescComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static pl.sii.FilesPaths.ENTRY_FILE_PATH;

public class PopularWords {

    public static void main(String[] args) throws FileNotFoundException {
        PopularWords popularWords = new PopularWords();
        Map<String, Long> result = popularWords.findOneThousandMostPopularWords();
        result.entrySet().forEach(System.out::println);
    }

    private static void getFullWordsToMap(Map<String, Long> words, String filePath) throws FileNotFoundException {
        Scanner file = new Scanner(new File(filePath));
        while (file.hasNext()) {
            String primaryWord = file.next().toLowerCase();
            Long count = words.get(primaryWord);
            if (count != null)
                count++;
            else
                count = 1L;

            words.put(primaryWord, count);
        }
        file.close();
    }

    Map<String, Long> findOneThousandMostPopularWords() throws FileNotFoundException {
        Map<String, Long> words = new HashMap<>();
        Map<String, Long> result = new TreeMap<>(new ValueDescComparator(words));

        getFullWordsToMap(words, ENTRY_FILE_PATH);
        result.putAll(words);

        return MapDivider.getSubMap(result, 0, 1000);
    }
}
