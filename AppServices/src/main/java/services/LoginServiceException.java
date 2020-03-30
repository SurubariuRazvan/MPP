package services;


public class LoginServiceException extends Exception {
    public LoginServiceException() {
    }

    public LoginServiceException(String message) {
        super(message);
    }

    public LoginServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
