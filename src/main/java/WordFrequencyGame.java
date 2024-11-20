import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
// useless if else
// magic string  : highlight the regex Ctrl Alt C to make the REGEX magic string
// stream insteaed of for loop
// extract method
// temp field? inputList = list;
public class WordFrequencyGame {


    public static final String REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";



    public String getWordFrequency(String sentence) {

        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";

        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> inputList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency input = new WordFrequency(word, 1);
                    inputList.add(input);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(inputList);

                List<WordFrequency> list = new ArrayList<>();

                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencies.entrySet()) {
                    WordFrequency input = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }

                inputList = list;
                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner("\n");

                for (WordFrequency wordFrequency : inputList) {
                    String wordWithFrequency = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(wordWithFrequency);
                }

                return joiner.toString();

            } catch (Exception e) {

                // exception can be more specific
                return "Calculate Error";
            }
        }
    }

    private List<WordFrequency> splitInputString(String sentence) {
        return Arrays.stream(sentence.split(REGEX))
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.toList());
    }


    private Map<String, List<WordFrequency>> getMap(List<WordFrequency> inputList){
        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(inputList);

        List<WordFrequency> list = new ArrayList<>();

        for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencies.entrySet()) {
            WordFrequency input = new WordFrequency(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return wordToWordFrequencies;
    }


    // - naming of Map
    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        Map<String, List<WordFrequency>> wordToWordFrequencies = new HashMap<>();
        for (WordFrequency input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordToWordFrequencies.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                wordToWordFrequencies.put(input.getWord(), arr);
            } else {
                wordToWordFrequencies.get(input.getWord()).add(input);
            }
        }
        return wordToWordFrequencies;
    }
}
