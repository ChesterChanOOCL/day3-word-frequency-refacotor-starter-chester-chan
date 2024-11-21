import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR_MSG = "Calculate Error";
    public static final String SPACE = " ";

    public String getWordFrequency(String sentence) {
            try {
                List<WordFrequency> wordFrequencies = splitInputString(sentence);
                Map<String, Long> wordCountMap = getWordCountMap(wordFrequencies);
                List<WordFrequency> sortedWordFrequencies = sortWordFrequencies(wordCountMap);
                return formatWordFrequencies(sortedWordFrequencies);
            } catch (Exception e) {
                return CALCULATE_ERROR_MSG;
            }
    }

    private List<WordFrequency> splitInputString(String sentence) {
        return Arrays.stream(sentence.split(REGEX))
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.toList());
    }
    private Map<String, Long> getWordCountMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord, Collectors.counting()));
    }

    private List<WordFrequency> sortWordFrequencies(Map<String, Long> wordCountMap) {
        return wordCountMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                .sorted((currentWord, nextWord) -> nextWord.getCount() - currentWord.getCount())
                .collect(Collectors.toList());
    }
    private String formatWordFrequencies(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getCount())
                .collect(Collectors.joining(LINE_BREAK));
    }
}
