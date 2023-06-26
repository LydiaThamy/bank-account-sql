package sg.edu.nus.iss.day24_paflecture.exception;

public class BankAccountWithdrawException extends RuntimeException {
    public BankAccountWithdrawException(String message) {
        super(message);
    }
}
