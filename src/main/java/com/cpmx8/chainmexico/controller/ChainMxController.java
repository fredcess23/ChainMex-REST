package com.cpmx8.chainmexico.controller;


import com.cpmx8.chainmexico.model.Account;
import com.cpmx8.chainmexico.service.AccountBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chainmx")
public class ChainMxController {

    @Autowired
    private AccountBuilder accountBuilder;

    @RequestMapping(value = "account", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Account account) {

        boolean status = accountBuilder.createAccount(account.getAlias());
        if (status)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
