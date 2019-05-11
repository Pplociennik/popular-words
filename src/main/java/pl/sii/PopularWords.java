package pl.sii;

import org.apache.commons.lang3.NotImplementedException;
import pl.sii.transformation.MapDivider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        getWords(words);

        return MapDivider.getSubMap(words, 0, 1000);
    }
}
