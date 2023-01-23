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
        String palin5 = "&s&";
        String palin6 = "$2s2$";
        String palin7 = "1";
        String palin8 = "haha";
        String palin9 = "Asdsa";
        assertTrue(p.isPalindrome(palin1));
        assertTrue(p.isPalindrome(palin2));
        assertFalse(p.isPalindrome(palin3));
        assertTrue(p.isPalindrome(palin4));
        assertTrue(p.isPalindrome(palin5));
        assertTrue(p.isPalindrome(palin6));
        assertTrue(p.isPalindrome(palin7));
        assertFalse(p.isPalindrome(palin8));
        assertFalse(p.isPalindrome(palin9));
    }

    @Test
    public void testPalindromeByOne() {
        Palindrome p = new Palindrome();
        CharacterComparator obo = new OffByOne();
        String palin1 = "unhot";  // true cases
        String palin2 = "stour";
        String palin21 = "rtous"; // Bidirectional
        String palin22 = "Rtous"; // Case sensitive
        String palin3 = "a"; // length 1
        String palin31 = "$";
        String palin32 = "2";
        String palin4 = "";  // length 0
        String palin5 = "abs";  // false
        String palin6 = "&%";
        String palin61 = "20";
        String palin7 = "&s^";
        assertTrue(p.isPalindrome(palin1, obo));
        assertTrue(p.isPalindrome(palin2, obo));
        assertTrue(p.isPalindrome(palin21, obo));
        assertFalse(p.isPalindrome(palin22, obo));
        assertTrue(p.isPalindrome(palin3, obo));
        assertTrue(p.isPalindrome(palin31, obo));
        assertTrue(p.isPalindrome(palin32, obo));
        assertTrue(p.isPalindrome(palin4, obo));
        assertFalse(p.isPalindrome(palin5, obo));
        assertTrue(p.isPalindrome(palin6, obo));
        assertFalse(p.isPalindrome(palin61, obo));
        assertFalse(p.isPalindrome(palin7, obo));
        assertFalse(palindrome.isPalindrome("fuckyou", obo));
        assertFalse(palindrome.isPalindrome("sonofbitch", obo));
        assertFalse(palindrome.isPalindrome("youfuckingidiot", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertFalse(palindrome.isPalindrome("ababssba", obo));
        assertTrue(palindrome.isPalindrome("", obo));
    }

}
