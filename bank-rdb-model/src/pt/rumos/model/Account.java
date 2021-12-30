package pt.rumos.model;

import java.util.ArrayList;

public class Account {

    private Integer id;
    private String nib;
    private Integer primaryOwnerId;
    private ArrayList<Integer> secondaryOwnersId = new ArrayList<Integer>();
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
	
	public Integer getPrimaryOwnerId() {
		return primaryOwnerId;
	}
	
	public void setPrimaryOwnerId(Integer primaryOwnerId) {
		this.primaryOwnerId = primaryOwnerId;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public ArrayList<Integer> getSecondaryOwnersId() {
		return secondaryOwnersId;
	}

	public void setSecondaryOwnersId(ArrayList<Integer> secondaryOwnersId) {
		this.secondaryOwnersId = secondaryOwnersId;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", nib=" + nib + ", primaryOwnerId=" + primaryOwnerId + ", secondaryOwnersId="
				+ secondaryOwnersId + ", balance=" + balance + "]";
	}

}