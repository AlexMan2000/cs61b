import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlexMan
 */
public class DemoCollectionTest {

    @Test
    public void testGetWords() {
        DemoCollection dc = new DemoCollection();
        String filename = "libraryOfBabylon.txt";
        System.out.println(dc.getWords(filename));
    }

    @Test
    public void testUniqueWords() {
        DemoCollection dc = new DemoCollection();
        String filename = "libraryOfBabylon.txt";
        List<String> words = dc.getWords(filename);
        int uniqueCount = dc.countUniqueWords(words);
        System.out.println(uniqueCount);
    }

    @Test
    public void tesdWordsCountMap() {
        DemoCollection dc = new DemoCollection();
        String filename = "libraryOfBabylon.txt";
        List<String> words = dc.getWords(filename);
        Map<String, Integer> wordsCountMap = dc.collectWordCount(words);
        System.out.println(wordsCountMap);
    }
}
