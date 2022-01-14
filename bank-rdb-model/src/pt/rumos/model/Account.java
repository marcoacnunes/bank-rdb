package pt.rumos.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private Integer id;
    private String nib;
    private Client primaryOwner = new Client();
    private List<Client> secondaryOwners = new ArrayList<Client>();
    private Double balance = 0.00D;
    private List<String> transactions = new ArrayList<String>();

	public List<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<String> transactions) {
		this.transactions = transactions;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNib() {
		return nib;
	}
	
	public void setNib(String nib) {
		this.nib = nib;
	}

	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Client getPrimaryOwner() {
		return primaryOwner;
	}

	public void setPrimaryOwner(Client primaryOwner) {
		this.primaryOwner = primaryOwner;
	}

	public List<Client> getSecondaryOwners() {
		return secondaryOwners;
	}

	public void setSecondaryOwners(List<Client> secondaryOwners) {
		this.secondaryOwners = secondaryOwners;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", nib=" + nib + ", primaryOwner=" + primaryOwner + ", secondaryOwners="
				+ secondaryOwners + ", balance=" + balance + ", transactions=" + transactions + "]";
	}

	
}