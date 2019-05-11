package pl.sii;

import org.apache.commons.lang3.NotImplementedException;
import pl.sii.transformation.MapDivider;
import pl.sii.transformation.ValueComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PopularWords {

    public static void main(String[] args) throws FileNotFoundException {
        PopularWords popularWords = new PopularWords();
        Map<String, Long> result = popularWords.findOneThousandMostPopularWords();
        result.entrySet().forEach(System.out::println);
    }

    private static void getWords(Map<String, Long> words) throws FileNotFoundException {
        Scanner file = new Scanner(new File(".\\src\\main\\resources\\3esl.txt"));
        while (file.hasNext()) {
            String word = file.next().toLowerCase();
            Long count = words.get(word);
            if (count != null)
                count++;
            else
                count = 1L;

            words.put(word, count);
        }
        file.close();
    }

    Map<String, Long> findOneThousandMostPopularWords() throws FileNotFoundException {
        Map<String, Long> words = new HashMap<>();
        Map<String, Long> result = new TreeMap<>(new ValueComparator(words));
        getWords(words);
        result.putAll(words);

        return MapDivider.getSubMap(result, 0, 1000);
    }
}
