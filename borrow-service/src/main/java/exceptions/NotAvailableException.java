package exceptions;

public class NotAvailableException extends RuntimeException {
    public NotAvailableException(String msg) {
        super(msg);
    }
}