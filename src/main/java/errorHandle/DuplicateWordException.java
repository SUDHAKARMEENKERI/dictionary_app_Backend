package errorHandle;

public class DuplicateWordException extends RuntimeException {
    public DuplicateWordException(String message) {
        super(message);
    }
}
