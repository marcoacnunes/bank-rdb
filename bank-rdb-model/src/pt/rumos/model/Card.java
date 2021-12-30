package pt.rumos.model;

public class Card {

    private Integer id;
    private Integer clientId;
    private String pin;
    private Integer accountId;
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getClientId() {
		return clientId;
	}
	
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String toString() {
		return "Card [id=" + id + ", clientId=" + clientId + ", pin=" + pin + ", accountId=" + accountId + "]";
	}
	
	
    

}
