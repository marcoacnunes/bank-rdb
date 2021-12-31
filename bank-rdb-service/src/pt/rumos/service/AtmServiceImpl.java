package pt.rumos.service;

import java.util.Scanner;
import pt.rumos.model.Account;
import pt.rumos.model.Card;
import pt.rumos.model.Client;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;

public class AtmServiceImpl implements AtmService {
    
	private Scanner scan = new Scanner(System.in);
	private CardService cardService;
    
	public String checkCardPin(Card card) {
		
		System.out.println("Insert PIN code: ");
		String pin = scan.next();

		if (card.getPin().equals(pin)) {
			return "PIN code Match!";
		} else {
			return "Invalid PIN!";
		}
	}

	@Override
	public Card displayCreditCards(Client client) {
		
		for (Card card : cardService.getAll()) {
			
			if (card.getClass().equals(CreditCard.class) &&  card.getClientId().equals(client.getId())) {
				   
				System.out.println("ID: " + card.getId() + " - CreditCard Available");
				return card;
			}
		}
		throw new RuntimeException("No Credit Cards found!");
	}
	
	@Override
	public Card displayDebitCards(Client client) {
		
		for (Card card : cardService.getAll()) {
			
			if (card.getClass().equals(DebitCard.class) &&  card.getClientId().equals(client.getId())) {
				   
				System.out.println();
				System.out.println("ID: " + card.getId() + " - DebitCard Available");
				return card;
			}
		}
		throw new RuntimeException("No Debit Cards found!");
	}
	
	@Override
	public void transfer(Account account, Account receivingAccount) {
		
		System.out.println("\nType value that you wish to transfer to Account ID: " + receivingAccount.getId());
		Double valueInput = scan.nextDouble();
		
		if(valueInput <= 0.00D) {
			throw new RuntimeException("Invalid amount!");
		}
		
		if (account.getBalance() <= 0) {
			throw new RuntimeException("Sir you have insufficient funds");
		}
		
		if (valueInput < account.getBalance()) {
			throw new RuntimeException("You have " + account.getBalance() + ". Cannot transfer specified value");
		} 

		account.setBalance(account.getBalance() - valueInput);
		receivingAccount.setBalance(receivingAccount.getBalance() + valueInput);

		System.out.println("Transfer Completed! This Account balance is now: " + account.getBalance() + " And Account with ID: "
							+ receivingAccount.getId() + " balance is now: " + receivingAccount.getBalance());

		account.getTransactions().add("- " + valueInput + " to Account ID: " + receivingAccount.getId());
	}
	
    @Override
    public void withdraw(Account account, Card card){
    	
    	if (card.getClass().equals(CreditCard.class)) {

			System.out.println("Do you wish to withdraw from CreditCard Plafond? (Cash-Advance) Y/N: ");
			String choice = scan.next();

			if (choice.equalsIgnoreCase("y")) {
				withdrawCreditCardPlafond(account, card);
				
			}else if(choice.equalsIgnoreCase("n")) {
				normalWithdrawal(account, card);
			}
		}else if(card.getClass().equals(DebitCard.class)){
			normalWithdrawal(account, card);
		}
    }
    
    private void normalWithdrawal(Account account, Card card) {
			
		DebitCard debitCard = (DebitCard) card;
		
		System.out.println("Type the value that you want to withdrawal: ");
		double valueInput = scan.nextDouble();
		
		if (account.getBalance() <= 0) {
			throw new RuntimeException("Sir you have insufficient funds");

		} else if (account.getBalance() < valueInput) {
			throw new RuntimeException("You have " + account.getBalance() + ". Cannot transfer specified value");

		} else {
			account.setBalance(account.getBalance() - valueInput);
			debitCard.setLastWithdrawal(valueInput);
			System.out.println("Withdrawal made! Your account balance is now:" + account.getBalance());
		}
	}
	
	private void withdrawCreditCardPlafond(Account account, Card card) {
		
		CreditCard creditCard = (CreditCard) card;
		
		System.out.println("\nType the value that you want to withdraw: ");
		double valueInput = scan.nextDouble();

		if (valueInput > creditCard.getPlafond()) {
			throw new RuntimeException("Input value is greater than current Credit Card Plafond");
			
		} else {
			creditCard.setPlafond(creditCard.getPlafond() - valueInput);
			creditCard.setDailyWithdrawals(creditCard.getDailyWithdrawals() + 1);
			System.out.println("\nWithdrawal made! Your account balance is now:" + account.getBalance());
		}
	}

	@Override
    public void deposit(Account account) {
    	
    	System.out.println("Type the value that you want to deposit: ");
		double value = scan.nextDouble();
		
		if(value <= 0.00D) {
			throw new RuntimeException("Invalid amount!");
		}

		account.setBalance(account.getBalance() + value);
		System.out.println("Deposit made! Your account balance is now: " + account.getBalance());
    }

	@Override
	public void payPlafond(Account account, CreditCard creditCard, Double valueInputPlafond) {
		
		if(account.getBalance() <= 0) {
			throw new RuntimeException("Insufficient account balance");
		}
	
		if (valueInputPlafond > creditCard.getPlafond()) {
			throw new RuntimeException("Value input exceeds amount needed to pay Plafond");
		}
		
		plafondPayment(creditCard, account, valueInputPlafond);
		
	}
	
	private void plafondPayment(CreditCard creditCard, Account account, Double valueInputPlafond) {
		
		Double balanceTest = account.getBalance() - valueInputPlafond;
		Double plafondTest = creditCard.getPlafond() + valueInputPlafond;
	
		if (balanceTest >= 0) {
			
			if (plafondTest <= 1000.00) {
				account.setBalance(account.getBalance() - valueInputPlafond);
				creditCard.setPlafond(creditCard.getPlafond() + valueInputPlafond);
				System.out.println("Payment Done!");

			} else {
				throw new RuntimeException("Value input exceeds amount needed to pay Plafond");
			}
			
		} else {
			throw new RuntimeException("Cannot do this payment as it would set Account balance to negative");
		}
	}
}
