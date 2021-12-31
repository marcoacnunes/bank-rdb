package pt.rumos.console;

import java.util.Scanner;
import pt.rumos.model.Account;
import pt.rumos.model.Card;
import pt.rumos.model.Client;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;
import pt.rumos.service.AccountService;
import pt.rumos.service.AtmService;
import pt.rumos.service.CardService;
import pt.rumos.service.ClientService;

public class Atm {

	Scanner scan = new Scanner(System.in);
	
	private ClientService clientService;
	private AccountService accountService;
	private AtmService atmService;
	
	public Atm(ClientService clientService, AccountService accountService, CardService cardService, AtmService atmService) {
		this.clientService = clientService;
		this.accountService = accountService;
		this.atmService = atmService;
	}

	public void run() {
		
		System.out.println("Type Client NIF: ");
		String nif = scan.next();
		
		Client client = clientService.getByNif(nif);
		System.out.println("Client Found!");
		
		int cardIdInput = 0;
		
		do {
			System.out.println("0. Go back to Main Menu");
			
			CreditCard creditCard = null;
			DebitCard debitCard = null;
				
			creditCard = (CreditCard) atmService.displayCreditCards(client);
			System.out.println(creditCard);
			
			debitCard = (DebitCard) atmService.displayDebitCards(client);
			System.out.println(debitCard);
			
			System.out.println("\nChoose Card Available option by ID or type 0 to go back: ");
			cardIdInput = scan.nextInt();
	
			if(creditCard != null && cardIdInput == creditCard.getId()) {
				
				atmService.checkCardPin(creditCard);
				showAtmMenu(creditCard, client);
					
			}else if(debitCard != null && cardIdInput == debitCard.getId()) {
			
				atmService.checkCardPin(debitCard);
				showAtmMenu(debitCard, client);
			}
		}while(cardIdInput != 0);
	}

	private void showAtmMenu(Card card, Client client) {
		
		int choice = 0;
		
		do {
			System.out.println("\n0. Go Back");
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. Check Balance");
			System.out.println("4. Transfers");
			System.out.println("5. Check Transaction History");
			
			CreditCard creditCard = null;
			
			if(card.getClass().equals(CreditCard.class)) {		//needs better solution
				creditCard = (CreditCard) card;
				System.out.println("6. Pay Plafond");
				System.out.println("7. Check Plafond Available");
			}
			System.out.println("Select an option: ");
	
			choice = scan.nextInt();
	
			Account account = accountService.getById(card.getAccountId());
			
			switch (choice) {
				case 1:
					atmService.deposit(account);
					break;
				case 2:
					atmService.withdraw(account, card);
					break;
				case 3:
					System.out.println("Balance: " + account.getBalance());
					break;
				case 4:
					transfers(account, client);
					break;
				case 5:
					transactionsHistory(account);
					break;
				case 6:
					plafond(creditCard, account, client);
					break;
				case 7:
					System.out.println("Plafond Amount Available: " + creditCard.getPlafond());
					break;
				default:
					break;
				}
		}while(choice != 0);
	}
	
	public void transfers(Account account, Client client){
		
		int choice = 0;
		
		do {
			System.out.println("0. Go Back");
			System.out.println("1. Transfers between your Accounts");
			System.out.println("2. Transfers to a diferent Account");
			
			choice = scan.nextInt();
			
			switch (choice) {
			case 1:
				transferBetweenAccounts(account, client);
				break;
			case 2:
				transferToDiferentAccount(account, client);
				break;
			default:
				break;
			}
		}while(choice != 0);
	}

	private void transactionsHistory(Account account) {
		
		account.getTransactions().forEach(System.out::println);
	}
	
	private void plafond(CreditCard creditCard, Account account, Client client) {
		
		System.out.println("Type value to pay Plafond: ");
		Double valueInputPlafond = scan.nextDouble();
		atmService.payPlafond(account, creditCard, valueInputPlafond);
	}
	
	public void transferBetweenAccounts(Account account, Client client) {
		
		for (Account thisAccount : accountService.getAll()) {
			
			if(thisAccount.getPrimaryOwnerId().equals(client.getId())){
				System.out.println("ID: " + thisAccount.getId() + " NIB: " + thisAccount.getNib());
			}
		}
		
		System.out.println("\nType the Account ID that you wish to transfer: ");
		int id = scan.nextInt();
		
		Account receivingAccount = accountService.getById(id);
		atmService.transfer(account, receivingAccount);
	}
	
	public void transferToDiferentAccount(Account account, Client client){

		System.out.println("Type Account NIB number to transfer value");
		String nib = scan.next();

		Account receivingAccount = accountService.getByNib(nib);
		atmService.transfer(account, receivingAccount);
	}
}
