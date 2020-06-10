
public class NegativesNotAllowedException extends Exception {
    public static final String MESSAGE = "Negatives not allowed. Following is the list of negatives numbers : ";

    public NegativesNotAllowedException(String negativeNumbers) {
        super(MESSAGE + negativeNumbers);
    }
}
