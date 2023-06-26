package sg.edu.nus.iss.day24_paflecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24_paflecture.exception.AccountBlockedOrDisabledException;
import sg.edu.nus.iss.day24_paflecture.exception.AmountNotSufficientException;
import sg.edu.nus.iss.day24_paflecture.exception.BankAccountNotFoundException;
import sg.edu.nus.iss.day24_paflecture.model.BankAccount;
import sg.edu.nus.iss.day24_paflecture.repository.BankAccountRepo;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepo repository;

    public BankAccount getAccount(Integer id) {
        return repository.getAccount(id);
    }

    public Boolean withdrawAccount(float amount, Integer id) {
        return repository.withdrawAccount(amount, id);
    }

    public Boolean depositAccount(float amount, Integer id) {
        return repository.depositAccount(amount, id);
    }

    public Boolean createAccount(BankAccount bankAccount) {
        return repository.createccount(bankAccount);
    }

    // encompasses all methods in a single unit of work
    // writing of records to nmore than one tables or update more than one records
    // in a table
    @Transactional
    public Boolean transferMoney(Integer transfererId, Integer receiverId, float amount) {

        BankAccount transfererAccount = getAccount(transfererId);
        BankAccount receiverAccount = getAccount(receiverId);

        // 1. check if transferer exists
        if (transfererAccount == null)
            throw new BankAccountNotFoundException("Transfering account ID " + transfererId + " does not exist");

        // 2. check if transferer is active
        if (transfererAccount.getIsActive() == false)
            throw new AccountBlockedOrDisabledException("Transfering account ID " + transfererId + " is inactive");

        // 3. check if transferer is blocked
        if (transfererAccount.getIsBlocked() == true)
            throw new AccountBlockedOrDisabledException("Transfering account ID " + transfererId + " is blocked");

        // 4. check if receiver exists
        if (receiverAccount == null)
            throw new BankAccountNotFoundException("Receiving account ID " + receiverId + " does not exist");

        // 5. check if receiver is active
        if (receiverAccount.getIsActive() == false)
            throw new AccountBlockedOrDisabledException("Receiving account ID " + receiverId + " is inactive");

        // 6. check if receiver is blocked
        if (receiverAccount.getIsBlocked() == true)
            throw new AccountBlockedOrDisabledException("Receiving account ID " + receiverId + " is blocked");

        // 7. check if transferer has enough money to transfer to receiver
        if (transfererAccount.getBalance() < amount)
            throw new AmountNotSufficientException(
                    "Transfering account ID " + transfererId + " does not have enough balance for the transaction");

        // 8. withdraw money from transferer
        withdrawAccount(amount, transfererId);

        // 9. deposit money to withdrawer
        depositAccount(amount, receiverId);

        return true;
    }

}
