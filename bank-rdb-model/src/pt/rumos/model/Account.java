package pt.rumos.model;

import java.util.ArrayList;

public class Account {

    private Integer id;
    private String nib;
    private Client primaryOwner;
    private ArrayList<Client> secondaryOwners = new ArrayList<Client>();
    private Double balance = 0.00D;
    private ArrayList<String> transactions = new ArrayList<String>();

	public ArrayList<String> getTransactions() {
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

	public ArrayList<Client> getSecondaryOwners() {
		return secondaryOwners;
	}

	public void setSecondaryOwners(ArrayList<Client> secondaryOwners) {
		this.secondaryOwners = secondaryOwners;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", nib=" + nib + ", primaryOwner=" + primaryOwner + ", secondaryOwners="
				+ secondaryOwners + ", balance=" + balance + ", transactions=" + transactions + "]";
	}

	
}