package pt.rumos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pt.rumos.model.Account;
import pt.rumos.model.Client;
import pt.rumos.service.AccountService;
import pt.rumos.service.AccountServiceImpl;

@RestController
public class AccountController {

	private AccountService accountService = new AccountServiceImpl();
	
	@RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAll() throws Exception {
		return accountService.getAll();
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getById(@PathVariable Integer id) throws Exception {
		return accountService.getById(id);
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createAccount(@RequestBody Account account) throws Exception {
		return accountService.save(account);
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable Integer id) throws Exception {
		accountService.deleteById(id);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
