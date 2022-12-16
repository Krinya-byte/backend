package com.novin.accountmanager.controller;

import com.novin.accountmanager.domain.Account;
import com.novin.accountmanager.domain.User;
import com.novin.accountmanager.repositories.IAccountRepository;
import com.novin.accountmanager.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IAccountRepository accountRepository;

    @PostMapping
    public HttpStatus addAccount(@RequestBody Account account,
                                 @RequestParam(name = "password", required = true) String password) {
        User owner = userRepository.findByUserName(account.getUserName());
        HttpStatus badRequest = validateOwner(account, owner, password);
        if (badRequest != null) return badRequest;
        Account newAccount = new Account(account, owner);
        accountRepository.saveAndFlush(newAccount);
        return HttpStatus.OK;
    }
@GetMapping("{ownerName}")
@ResponseBody
public ResponseEntity<Account> getAccount(@PathVariable String ownerName){
        Account acc = accountRepository.findByCustomerName(ownerName);
        return  new ResponseEntity<Account>(acc, HttpStatus.OK);
}
    private HttpStatus validateOwner(Account account, User owner, String password) {
        if (validateAccount(account)) {
            return HttpStatus.BAD_REQUEST;
        }

        if (owner == null) {
            return HttpStatus.NOT_FOUND;
        }
        if(!password.equals(owner.getPassword())){
            return HttpStatus.UNAUTHORIZED;
        }
        return null;
    }

    private boolean validateAccount(Account account) {
        return account == null
                || (account.getCustomerName() == null || account.getCustomerName().isEmpty())
                || (account.getDeadLine() == null)
                || (account.getItemName() == null || account.getItemName().isEmpty())
                || (account.getComment() == null || account.getComment().isEmpty())
                || (account.getPrice() == null);
    }
}
