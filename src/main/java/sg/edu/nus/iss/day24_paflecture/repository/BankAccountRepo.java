package sg.edu.nus.iss.day24_paflecture.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.day24_paflecture.exception.BankAccountNotFoundException;
import sg.edu.nus.iss.day24_paflecture.exception.BankAccountWithdrawException;
import sg.edu.nus.iss.day24_paflecture.model.BankAccount;

@Repository
public class BankAccountRepo {

    @Autowired
    private JdbcTemplate template;

    private final String getSQL = "select * from bank_account where id = ?";
    private final String withdrawSQL = "update bank_account set balance = balance - ? where id = ?";
    private final String depositSQL = "update bank_account set balance = balance + ? where id = ?";
    private final String createSql = "insert into bank_account values (null, ?, false, true, ?, ?)";

    public BankAccount getAccount(Integer id) {

        List<BankAccount> bankAccounts = template.query(getSQL, BeanPropertyRowMapper.newInstance(BankAccount.class), id);
        // if we use queryForObject, it will throw an exception inside the method and return null, which is not what we want with custom exception handlers

        if (bankAccounts.isEmpty()) {
            throw new BankAccountNotFoundException("Bank account ID " + id + " does not exist");
        }

        return bankAccounts.get(0);
    }

    public Boolean withdrawAccount(float amount, Integer id) {
        float existingAmount = template.query("Select * from bank_account where id = ?",
                BeanPropertyRowMapper.newInstance(BankAccount.class), id).get(0).getBalance();

        if (existingAmount < amount) {
            throw new BankAccountWithdrawException("The balance in bank account ID " + id + " is " + existingAmount + " which is lower than the amount which you are trying to withdraw");
        }

        return template.update(withdrawSQL, amount, id) > 0 ? true : false;
    }

    public Boolean depositAccount(float amount, Integer id) {
        return template.update(depositSQL, amount, id) > 0 ? true : false;
    }

    public Boolean createccount(BankAccount bankAccount) {
        return template.update(createSql, bankAccount.getFullName(), bankAccount.getAccountType(),
                bankAccount.getBalance()) > 0 ? true : false;
    }

}
