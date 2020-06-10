import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Addition {
    private static final int LARGEST_NUMBER_ALLOWED = 1000;
    private static final String PREFIX_FOR_CUSTOM_DELIMITER = "//";
    private static final String NEW_LINE = "\n";
    private static final String COMMA_DELIMITER = ",";
    private static final String PIPE = "|";

    public int add(String numbers) throws NegativesNotAllowedException {
        // Handling the empty string cases
        if(numbers == null || numbers.isBlank()) {
            return 0;
        }

        String delimiter = COMMA_DELIMITER;
        String [] multipleDelimitersArray = null;

        // Checking whether custom delimiter is present or not
        if(numbers.startsWith(PREFIX_FOR_CUSTOM_DELIMITER)) {
            delimiter = numbers.substring(PREFIX_FOR_CUSTOM_DELIMITER.length(), numbers.indexOf(NEW_LINE));

            // In case of multiple custom delimiters
            if(delimiter.contains(COMMA_DELIMITER)) {
                multipleDelimitersArray = delimiter.split(COMMA_DELIMITER);
            }

            // Removing all the new line characters and the \\ present at the beginning of string.
            numbers = numbers.replaceAll(NEW_LINE, "").substring(2 + delimiter.length());
        }

        List<String> numbersList = getNumbersList(numbers, multipleDelimitersArray, delimiter);
        return findSumFromNumberList(numbersList);

    }

    /**
     * Method to create the list of numbers from given single delimiter and multiple delimiters.
     *
     * @param numbers Numbers in String with delimiters
     * @param multipleDelimitersArray Array holding multiple delimiters
     * @param delimiter Delimiter in case of single delimiter
     * @return Returns the list of numbers extracted from given inputs.
     */
    private List<String> getNumbersList(String numbers, String [] multipleDelimitersArray, String delimiter) {
        // Creating an empty list to hold numbers.
        List<String> numbersList = new ArrayList<String>();

        // Handling Multiple delimiters case
        if(multipleDelimitersArray != null) {
            String multiDelimiterString = "";

            // Creating a generic delimiter string combining all the delimiters in case of multiple delimiters.
            for(String element : multipleDelimitersArray) {
                multiDelimiterString += (Pattern.quote(element) + PIPE);
            }
            // Removing the extra pipe at the end.
            multiDelimiterString = multiDelimiterString.substring(0, multiDelimiterString.length() - 1);

            // In this case, split function will work for multiple delimiters since we have PIPE between all the delimiters.
            numbersList = Arrays.asList(numbers.split(multiDelimiterString));
        } else {
            // Handling Single delimiter case
            numbersList = Arrays.asList(numbers.split(Pattern.quote(delimiter)));
        }

        return numbersList;
    }

    /**
     * Method to find out the sum of given numbers in the list.
     *
     * @param numbersList Input list containing all the numbers
     * @return Return the sum of numbers.
     * @throws NegativesNotAllowedException Thrown in case of negatives numbers are found in the list.
     */
    private int findSumFromNumberList(List<String> numbersList) throws NegativesNotAllowedException {
        int sum = 0;
        List<Integer> negativeNumbersList = new ArrayList<Integer>();
        // Looping over all the numbers found from above processing
        for(String number : numbersList) {
            // Removing all the new line characters and parsing the integer.
            number = number.replaceAll(NEW_LINE, "");
            int ans = Integer.parseInt(number);

            // Adding the number to negative numbers list, if it is less than 0.
            if(ans < 0) {
                negativeNumbersList.add(ans);
                continue;
            }

            // Skipping the number larger than the LARGEST_NUMBER allowed.
            if(ans > LARGEST_NUMBER_ALLOWED) {
                continue;
            }
            sum += ans;
        }

        // Throwing an exception is any negative numbers were found.
        if(negativeNumbersList.size() != 0) {
            throw new NegativesNotAllowedException(negativeNumbersList.toString());
        }
        return sum;
    }
}
