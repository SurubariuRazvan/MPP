package services;


public class AppServiceException extends Exception {
    public AppServiceException() {
    }

    public AppServiceException(String message) {
        super(message);
    }

    public AppServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
