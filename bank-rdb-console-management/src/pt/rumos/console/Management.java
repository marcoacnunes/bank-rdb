package pt.rumos.console;

import java.time.LocalDate;
import java.util.Scanner;
import pt.rumos.model.Account;
import pt.rumos.model.Card;
import pt.rumos.model.Client;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;
import pt.rumos.service.AccountService;
import pt.rumos.service.CardService;
import pt.rumos.service.ClientService;

public class Management {

	private Scanner scan = new Scanner(System.in);
	private ClientService clientService;
	private AccountService accountService;
	private CardService cardService;

	public Management(ClientService clientService, AccountService accountService, CardService cardService) {
		this.clientService = clientService;
		this.accountService = accountService;
		this.cardService = cardService;
	}

	public void run() {
		int choice = 0;
		
		do {
			System.out.println("0. Back to Main Menu");
			System.out.println("1. Account Management");
			System.out.println("2. Client Management");
			System.out.println("3. Cards Management");
			choice = scan.nextInt();
	
			switch (choice) {
				case 1:
					accountManagement();
					break;
				case 2:
					clientManagement();
					break;
				case 3:
					cardManagement();
					break;
				default:
					break;
			}
		}while(choice != 0);
	}

	private void accountManagement() { 
		int choice = 0;
		
		do {
			System.out.println("0. Go back");
			System.out.println("1. Create Account");
			System.out.println("2. List all Accounts");
			System.out.println("3. Delete Account");
			System.out.println("4. Create Account with existing Client");
			choice = scan.nextInt();
	
			switch (choice) {
				case 1:
					createAccount();
					break;
				case 2:
					listAllAccounts();
					break;
				case 3:
					deleteAccount();
					break;
				case 4:
					createAccountWithExistingClient();
				default:
					break;
			}
		}while(choice != 0);
	}
	
	private void createAccount() {
		Client client = askClientInfo();
		client = clientService.save(client);
		
		Account account = new Account();
		account.setPrimaryOwner(client);
		account = accountService.save(account);
		
		System.out.println("Account and Client created!");
		System.out.println("Account ID: " + account.getId());
		System.out.println("Account NIB: " + account.getNib());
		System.out.println("Client ID: " + client.getId());
	}
	
	private void createAccountWithExistingClient() {
		System.out.println("Type Client NIF: ");
		String nif = scan.next();
		Client client = clientService.getByNif(nif);
		
		Account account = new Account();
		account.setPrimaryOwner(client);
		accountService.save(account);
		System.out.println("Account Created!");
	}
	
	private void listAllAccounts() {
		accountService.getAll().forEach(System.out::println);
	}
		
	private void deleteAccount() {
		System.out.println("Type Account ID to delete: ");
		int id = scan.nextInt();
		accountService.deleteById(id);
	}
	
	private void clientManagement() {
		int choice = 0;
		
		do {
			System.out.println("0. Go back");
			System.out.println("1. List All Clients");
			System.out.println("2. Find client by nif");
			System.out.println("3. Update client by nif");
			System.out.println("4. Delete client by nif");
			System.out.println("5. Create Secondary Client of an Account");
			System.out.println("6. List all Owners of an account");
			choice = scan.nextInt();
			
			switch (choice) {
				case 1:
					listAllClients();
					break;
				case 2:
					findClientByNif();
					break;
				case 3:
					updateClient();
					break;
				case 4:
					deleteClient();
					break;
				case 5:
					createSecondaryOwner();
					break;
				case 6:
					listAccountClients();
					break;
				default:
					break;
			}
		}while(choice != 0);
	}
	
	private void listAllClients() {
		clientService.getAll().forEach(System.out::println);
	}
	
	private void findClientByNif() {
		System.out.println("Type NIF to find client: ");
		String nif = scan.next();
		System.out.println(clientService.getByNif(nif).toString());
	}
	
	private void updateClient() {
		System.out.print("Enter client nif to update: ");
		String nif = scan.next();
		Client client = clientService.getByNif(nif);
		
		System.out.println("1. Update name ");
		System.out.println("2. Update password");
		System.out.println("3. Update date of birth (yyyy-MM-dd)");
		System.out.println("4. Update phone");
		System.out.println("5. Update mobile");
		System.out.println("6. Update email");
		System.out.println("7. Update occupation");
		System.out.print("Enter option: ");
		int option = scan.nextInt();
		scan.nextLine();

		switch (option) {
			case 1:
				System.out.print("Enter new name: ");
				String name = scan.nextLine();
				client.setName(name);
				break;
			case 2:
				System.out.print("Enter new password: ");
				String password = scan.nextLine();
				client.setPassword(password);
				break;
			case 3:
				System.out.print("Enter new date of birth (yyyy-MM-dd): ");
				LocalDate dob = LocalDate.parse(scan.nextLine());
				client.setDateOfBirth(dob);
				break;
			case 4:
				System.out.print("Enter new phone: ");
				String phone = scan.nextLine();
				client.setPhone(phone);
				break;
			case 5:
				System.out.print("Enter new mobile: ");
				String mobile = scan.nextLine();
				client.setMobile(mobile);
				break;
			case 6:
				System.out.print("Enter new email: ");
				String email = scan.nextLine();
				client.setEmail(email);
				break;
			case 7:
				System.out.print("Enter new occupation: ");
				String occupation = scan.nextLine();
				client.setOccupation(occupation);
				break;
		}
		clientService.save(client);
	}
	
	private void deleteClient() {
		System.out.print("Enter client nif to delete: ");
		String nif = scan.next();
		
		Client client = clientService.getByNif(nif);
		clientService.deleteByNif(client.getNif());
	}
	
	private void createSecondaryOwner() {
		System.out.println("Type Account ID: ");
		int id = scan.nextInt();
		Account account = accountService.getById(id);

		Client client = askClientInfo();
		client = clientService.save(client);
		
		account.getSecondaryOwners().add(client);
		accountService.save(account);
		System.out.println("Secondary Owner created!");
		System.out.println("Client ID: " + client.getId());
	}
	
	private void listAccountClients() {
		System.out.println("Type Account ID: ");
		int input = scan.nextInt();
		Account account = accountService.getById(input);
		accountService.getSecondaryClients(account).forEach(System.out::println);
	}

	private void cardManagement() {
		int choice = 0;
		
		do {
			System.out.println("0. Go back");
			System.out.println("1. Create Credit Card");
			System.out.println("2. Create Debit Card");
			System.out.println("3. Cancel CreditCard");
			System.out.println("4. Cancel DebitCard");
			System.out.println("5. List all Cards");
			choice = scan.nextInt();
			
			switch (choice) {
				case 1:
					createCreditCard();
					break;
				case 2:
					createDebitCard();
					break;
				case 3:
					cancelCreditCard();
					break;
				case 4:
					cancelDebitCard();
					break;
				case 5:
					listAllCards();
				default:
				break;
			}
		}while(choice != 0);
	}
	
	private void createCreditCard() {
		System.out.println("Type Account ID to create Credit Card: ");
		int id = scan.nextInt();
		Account account = accountService.getById(id);
		
		System.out.println("Type Client Nif to create Credit Card: ");
		String nif = scan.next();
		Client client = clientService.getByNif(nif);
		
		CreditCard creditCard = new CreditCard();
		creditCard.setAccount(account);
		creditCard.setClient(client);
		cardService.save(creditCard);
	}
	
	private void createDebitCard() {
		System.out.println("Type Account ID to create Debit Card: ");
		int id = scan.nextInt();
		Account account = accountService.getById(id);
		
		System.out.println("Type Client Nif to create Debit Card: ");
		String nif = scan.next();
		Client client = clientService.getByNif(nif);
		
		DebitCard debitCard = new DebitCard();
		debitCard.setAccount(account);
		debitCard.setClient(client);
		cardService.save(debitCard);
	}
	
	private void cancelCreditCard() {
		System.out.println("Type Client NIF to delete Credit Card: ");
		String nif = scan.next();
		Client client = clientService.getByNif(nif);
		
		Card card = cardService.getByClientId(client.getId());
		cardService.deleteById(card.getId());
	}
	
	private void cancelDebitCard() {
		System.out.println("Type Client NIF to delete Debit Card: ");
		String nif = scan.next();
		Client client = clientService.getByNif(nif);
		
		Card card = cardService.getByClientId(client.getId());
		cardService.deleteById(card.getId());
	}
	
	private void listAllCards() {
		cardService.getAll().forEach(System.out::println);
	}

	private Client askClientInfo() {
		Client client = new Client();
		System.out.println("Enter required client info.");
		System.out.println("Name: ");
		String name = scan.next();
		client.setName(name);

		System.out.println("Password: ");
		String password = scan.next();
		client.setPassword(password);

	 	System.out.print("Date of birth (yyyy-MM-dd): ");
		try {
			LocalDate dob = LocalDate.parse(scan.next());
			client.setDateOfBirth(dob);
		}catch (Exception e) {
			System.out.println("Wrong input. Try again");
			askClientInfo();
		}
		
		System.out.println("NIF: ");
		String nif = scan.next();
		client.setNif(nif);
		
		System.out.println("Telephone number: ");
		String telephone = scan.next();
		client.setPhone(telephone);
		
		System.out.println("Mobile number: ");
		String mobile = scan.next();
		client.setMobile(mobile);
		
		System.out.println("Email: ");
		String email = scan.next();
		client.setEmail(email);
		
		System.out.println("Occupation");	
		String occupation = scan.next();
		client.setOccupation(occupation);
		
		return client;
	}	
}
