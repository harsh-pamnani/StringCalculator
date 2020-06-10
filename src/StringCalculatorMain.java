import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StringCalculatorMain {
    public static void main(String[] args) throws NegativesNotAllowedException {
        // The list of complete test cases are covered in AdditionTest class with JUnit test.
        String numbers = "//$$$,##\n3$$$51##47";
        System.out.println("Input String is : ." + numbers);

        Addition addition = new Addition();
        System.out.println("Answer is : " + addition.add(numbers));
        // The list of complete test cases are covered in AdditionTest class with JUnit test.
    }
}
