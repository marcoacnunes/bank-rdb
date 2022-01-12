package pt.rumos.model;

public class Card {

    private Integer id;
    private Client client;
    private String pin;
    private Account account;
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "Card [id=" + id + ", client=" + client + ", pin=" + pin + ", account=" + account + "]";
	}
	
}
