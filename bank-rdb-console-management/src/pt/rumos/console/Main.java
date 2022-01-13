package pt.rumos.console;

import java.util.Scanner;
import pt.rumos.service.AccountService;
import pt.rumos.service.AccountServiceImpl;
import pt.rumos.service.AtmService;
import pt.rumos.service.AtmServiceImpl;
import pt.rumos.service.CardService;
import pt.rumos.service.CardServiceImpl;
import pt.rumos.service.ClientService;
import pt.rumos.service.ClientServiceImpl;

public class Main {
	
	public static void main(String[] args){
		ClientService clientService = new ClientServiceImpl();
		AccountService accountService = new AccountServiceImpl();
		CardService cardService = new CardServiceImpl();
		AtmService atmService = new AtmServiceImpl();
		
		Scanner scan = new Scanner(System.in);
		Atm atm = new Atm(clientService, accountService, cardService, atmService);
		Management management = new Management(clientService, accountService, cardService);
		
		int option = 0;
		
		do {
			showMenu();
			option = scan.nextInt();
			
			switch (option) {
				case 1:
					management.run();
					break;
				case 2:
					atm.run();
					break;
				default:
					break;
			}
		}while(option != 0);
		scan.close();
	}

	private static void showMenu() {
		System.out.println("0. Exit");
		System.out.println("1. Management");
		System.out.println("2. ATM");
	}
}
