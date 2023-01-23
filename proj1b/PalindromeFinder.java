/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        // Original Ones ../library-sp18/data/words.txt
        In in = new In("G:\\A-Computer-Science\\UCB_CS61B\\cs61b\\library-sp18\\data\\words.txt");
        Palindrome palindrome = new Palindrome();

        // Character Comparator
        CharacterComparator obo = new OffByOne();
        CharacterComparator obn = new OffByN(1);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, obn)) {
                System.out.println(word);
            }
        }
    }



}
