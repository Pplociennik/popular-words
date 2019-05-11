package pl.sii;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PopularWordsTest {
    private static final PopularWords testee = new PopularWords();

    @Test
    public void shouldReturnOneThousandMostPopularWords() throws FileNotFoundException {
        //given
        Map<String, Long> wordsFrequencyListCreatedByAdamKilgarriff = getWordsFrequencyListCreatedByAdamKilgarriff();

        //when
        Map<String, Long> result = testee.findOneThousandMostPopularWords();

        //then
        assertFalse(result.isEmpty());
        assertEquals(1000, result.size());
        compareWordListsFrequency(wordsFrequencyListCreatedByAdamKilgarriff, result);
    }

    private void compareWordListsFrequency(Map<String, Long> wordsFrequencyListCreatedByAdamKilgarriff, Map<String, Long> result) {
        long totalFrequencyByKilgarriff = wordsFrequencyListCreatedByAdamKilgarriff.values().stream().reduce(0L, Long::sum);
        long totalFrequencyInAResult = result.values().stream().reduce(0L, Long::sum);
        System.out.println("totalFrequencyByKilgarriff = " + totalFrequencyByKilgarriff);
        System.out.println("totalFrequencyInAResult = " + totalFrequencyInAResult);

        result.forEach((key, value) -> {
            BigDecimal valueUsagePercentage = calculatePercentage(value, totalFrequencyInAResult);
            BigDecimal kilgarriffUsagePercentage = wordsFrequencyListCreatedByAdamKilgarriff.get(key) == null ? BigDecimal.ZERO : calculatePercentage(wordsFrequencyListCreatedByAdamKilgarriff.get(key), totalFrequencyByKilgarriff);
            BigDecimal diff = kilgarriffUsagePercentage.subtract(valueUsagePercentage);
            System.out.println(key + "," + valueUsagePercentage + "%," + kilgarriffUsagePercentage + "%," + (new BigDecimal(0.5).compareTo(diff.abs()) > 0) + " " + diff);
        });
    }

    private BigDecimal calculatePercentage(double obtained, double total) {
        return new BigDecimal(obtained * 100 / total).setScale(4, RoundingMode.HALF_UP);
    }

    private Map<String, Long> getWordsFrequencyListCreatedByAdamKilgarriff() throws FileNotFoundException {
        Map<String, Long> kilgarriffsList = new LinkedHashMap<>();
        Scanner file = new Scanner(new File(".\\src\\test\\resources\\all.num"));
        while (file.hasNext()) {
            String word = file.nextLine();
            String[] parts = word.split(" ");
            kilgarriffsList.put(parts[1], Long.valueOf(parts[0]));
        }
        //throw new NotImplementedException("TODO implementation");
        return kilgarriffsList;
    }
}
