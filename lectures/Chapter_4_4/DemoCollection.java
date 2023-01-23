import java.util.*;

/**
 * Created by AlexMan
 */
public class DemoCollection {

    /**
     *  Returns a lower case version of the string with
     *  all characters except letters removed
     * @param s
     * @return
     */
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }


    /**
     * Get a list of all words in the file.
     * @param inputFilename
     * @return
     */
    public static List<String> getWords(String inputFilename) {
        List<String> words = new ArrayList<>();
        In in = new In(inputFilename);
        // 判断是否到了文件的尾部, see proj0
        while(!in.isEmpty()){
            String word = in.readString();
            word = DemoCollection.cleanString(word);
            words.add(word);
        }
        return words;
    }


    /**
     * Returns the count of the number of unique words in words
     * @param words
     * @return
     */
    public static int countUniqueWords(List<String> words) {
        // Construct set from List
//        HashSet wordset = new HashSet(words);
//        return wordset.size();


        // Start from empty set
        HashSet wordset = new HashSet();
        int count = 0;
        for (String word: words) {
            if (! wordset.contains(word)) {
                wordset.add(word);
                count ++ ;
            }
        }
        return count;
    }

    /**
     * Returns a map that tracks the count of all
     * unique word
     * @param wordsList
     * @return
     */
    public static Map<String, Integer> collectWordCount(List<String> wordsList) {
        HashMap<String, Integer> wordsMap = new HashMap<>();
        // Make sure all the words are in the map.
        for (String word: wordsList) {
            wordsMap.put(word, 0);
        }

        // Count the word, creating the wordsCountMap
        for (String word: wordsList) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            }
        }
        return wordsMap;
    }

    public static void main(String[] args) {
        List<String> w = getWords("libraryOfBabylon.txt");
        System.out.println(w);
    }
}
