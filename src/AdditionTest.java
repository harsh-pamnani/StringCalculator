import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Addition class
class AdditionTest {

    Addition addition;

    @BeforeEach
    void setUp() {
        addition = new Addition();
    }

    @Test
    void testAddEmptyString() throws NegativesNotAllowedException {
        assertEquals(0, addition.add(""));
        assertEquals(0, addition.add("     "));
        assertEquals(0, addition.add(null));
    }

    @Test
    void testAddValidStringWithComma() throws NegativesNotAllowedException {
        assertEquals(8, addition.add("1,2,5"));
        assertEquals(34, addition.add("4,7,23"));
    }

    @Test
    void testAddValidStringWithNewLine() throws NegativesNotAllowedException {
        assertEquals(6, addition.add("1\n,2,3"));
        assertEquals(19, addition.add("3,\n7,9"));
    }

    @Test
    void testAddValidStringWithAnotherDelimiter() throws NegativesNotAllowedException {
        assertEquals(8, addition.add("//;\n1;3;4"));
        assertEquals(6, addition.add("//$\n1$2$3"));
        assertEquals(13, addition.add("//@\n2@3@8"));
    }

    @Test
    void testAddValidStringWithNegativeNumber() {
        try {
            addition.add("//;\n1;-3;-4");
            fail("Expected NegativesNotAllowedException to be thrown");
        } catch (NegativesNotAllowedException e) {
            assertEquals( NegativesNotAllowedException.MESSAGE + "[-3, -4]" , e.getMessage());
        }

        try {
            addition.add("//;\n5;-11;-94;25;-48");
            fail("Expected NegativesNotAllowedException to be thrown");
        } catch (NegativesNotAllowedException e) {
            assertEquals( NegativesNotAllowedException.MESSAGE + "[-11, -94, -48]" , e.getMessage());
        }
    }

    @Test
    void testAddValidStringWithLargeNumbers() throws NegativesNotAllowedException {
        assertEquals( 8 , addition.add("2,1001,6"));
        assertEquals( 17 , addition.add("4,1451,7,6,26252"));
    }

    @Test
    void testAddValidStringWithArbitaryLengthDelimiter() throws NegativesNotAllowedException {
        assertEquals(6, addition.add("//***\n1***2***3"));
        assertEquals(78, addition.add("//#####\n9#####23#####46"));
    }

    @Test
    void testAddValidStringWithMultipleDelimiters() throws NegativesNotAllowedException {
        assertEquals(6, addition.add("//$,@\n1$2@3"));
        assertEquals(101, addition.add("//$$$,##\n3$$$51##47"));
        assertEquals(21, addition.add("//;;;,*****,!!\n1!!2*****3;;;4*****5!!6"));
    }
}