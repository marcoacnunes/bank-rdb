package pt.rumos.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.rumos.model.Card;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;
import pt.rumos.service.CardService;
import pt.rumos.service.CardServiceImpl;

@RestController
public class CardController {

	private CardService cardService = new CardServiceImpl();

	@RequestMapping(value = "/card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Card> getAll() throws Exception {
		return cardService.getAll();
	}

	@RequestMapping(value = "/card/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Card getById(@PathVariable Integer id) throws Exception {
		return cardService.getById(id);
	}

	@RequestMapping(value = "/creditCard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Card createCreditCard(@RequestBody CreditCard creditCard) throws Exception {
		return cardService.save(creditCard);
	}

	@RequestMapping(value = "/debitCard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Card createDebitCard(@RequestBody DebitCard debitCard) throws Exception {
		return cardService.save(debitCard);
	}

	@RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE)
	public void deleteCard(@PathVariable Integer id) throws Exception {
		cardService.deleteById(id);
	}
}
