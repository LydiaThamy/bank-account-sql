package sg.edu.nus.iss.day24_paflecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day24_paflecture.model.BankAccount;
import sg.edu.nus.iss.day24_paflecture.service.BankAccountService;

@RestController
@RequestMapping("/api/bank-account")
public class BankAccountRestController {
    
    @Autowired
    private BankAccountService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable Integer id) {
        BankAccount bankAccount = service.getAccount(id);
        // return bankAccount != null ? ResponseEntity.ok().body(bankAccount) : ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(bankAccount);
    }

    @GetMapping("/{id}/withdraw")
    public ResponseEntity<String> withdrawAccount(@PathVariable Integer id, @RequestParam float amount) {
        service.getAccount(id);
        return service.withdrawAccount(amount, id) == true ? ResponseEntity.ok().body("Withdraw successful") : ResponseEntity.badRequest().body("Withdraw unsuccessful");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody BankAccount bankAccount) {
        return service.createAccount(bankAccount) ? ResponseEntity.ok().body("Bank account successfully created") : ResponseEntity.internalServerError().body("Bank account was not created");
    
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> createAccount(@RequestParam("transferer-ID") Integer transfererId, @RequestParam("receiver-ID") Integer receiverId, @RequestParam("transfer-amount") float amount) {
        service.transferMoney(transfererId, receiverId, amount);
        return ResponseEntity.ok().body("Transfer successful");
    }

}
