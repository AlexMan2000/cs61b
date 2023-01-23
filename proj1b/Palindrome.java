/**
 * Created by AlexMan
 */
public class Palindrome {
    /**
     * Insert the characters in a string in the original
     * order into the deque
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wDeque = new LinkedListDeque<>();
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            Character c = word.charAt(i);
            wDeque.addLast(c);
        }
        return wDeque;
    }

    /**
     * Test whether a given word is a palindrome
     * using deque data structure.
     * The method is Case-sensitive
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque<Character> wDeque = wordToDeque(word);
        while (wDeque.size() > 1) {
            Character lastWord = wDeque.removeLast();
            Character firstWord = wDeque.removeFirst();
            if (!lastWord.equals(firstWord)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Recursive implementation of isPalindrome
     * without the use of Deque data structure
     * @param word
     * @return
     */
    private boolean isPalindromeRecursive(String word) {
        return isPalindromeRecursiveHelper(word, 0, word.length() - 1);
    }


    /**
     * Helper method for testPalindromeRecursive
     * @param word
     * @param i
     * @param j
     * @return
     */
    private boolean isPalindromeRecursiveHelper(String word, int i, int j) {
        if (i >= j) {
            return true;
        }
        char firstWord = word.charAt(i);
        char lastWord = word.charAt(j);
        if (firstWord != lastWord) {
            return false;
        }
        return isPalindromeRecursiveHelper(word, i + 1, j - 1);
    }

    /**
     *
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wDeque = wordToDeque(word);
        while (wDeque.size() > 1) {
            char lastWord = wDeque.removeLast();
            char firstWord = wDeque.removeFirst();
            if (!cc.equalChars(firstWord, lastWord)) {
                return false;
            }
        }
        return true;
    }



}
