package com.isilona.accm.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isilona.accm.web.data.response.AccountDto;
import com.isilona.accm.web.service.AccountServiceWeb;

@RestController
@RequestMapping(value = "/account")
public class AccountControllerWeb {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountControllerWeb.class);

    @Autowired
    private AccountServiceWeb userServiceWeb;

    // CREATE
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto newAccount) {
	LOGGER.info("POST /account/new");
	AccountDto savedAccount = userServiceWeb.saveAccount(newAccount);
	return new ResponseEntity<AccountDto>(savedAccount, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDTO, @PathVariable Long id) {
	LOGGER.info("PUT /account/" + id);
	AccountDto updatedAccount = userServiceWeb.saveAccount(accountDTO);
	return new ResponseEntity<AccountDto>(updatedAccount, HttpStatus.OK);
    }

    // READ
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {
	LOGGER.info("GET /account/" + id);
	AccountDto result = userServiceWeb.getAccountById(id);
	return new ResponseEntity<AccountDto>(result, HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public boolean deleteAccountById(@PathVariable("id") Long id) {
	LOGGER.info("DELETE /account/" + id);
	userServiceWeb.deleteAccountById(id);
	return true;
    }

    // ALL
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<AccountDto>> getAccounts() {
	LOGGER.info("GET /account/list");
	List<AccountDto> entityList = userServiceWeb.getAccounts();
	return new ResponseEntity<List<AccountDto>>(entityList, HttpStatus.OK);
    }

}
