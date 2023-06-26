package sg.edu.nus.iss.day24_paflecture.exception;

public class BankAccountNotFoundException extends RuntimeException {
// **it's not necessary to have all 4 methods, depending on what we need

    // default method
    public BankAccountNotFoundException() {
        super();
    }

    public BankAccountNotFoundException(String message) {
        super(message);
    }

    public BankAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankAccountNotFoundException(Throwable cause) {
        super(cause);
    } 
    
}
