package sg.edu.nus.iss.day24_paflecture.exception;

public class AccountBlockedOrDisabledException extends RuntimeException {
    
    public AccountBlockedOrDisabledException(String message) {
        super(message);
    }
}
