import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    // Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        Palindrome p = new Palindrome();
        String palin1 = "abcba";
        String palin2 = "a";
        String palin3 = "Aba";
        String palin4 = "";
        boolean res1 = p.isPalindromeRecursive(palin1);
        boolean res2 = p.isPalindromeRecursive(palin2);
        boolean res3 = p.isPalindromeRecursive(palin3);
        boolean res4 = p.isPalindromeRecursive(palin4);
        assertEquals(res1, true);
        assertEquals(res2, true);
        assertEquals(res3, false);
        assertEquals(res4, true);
    }
}
