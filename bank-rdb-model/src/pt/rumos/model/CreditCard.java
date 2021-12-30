package pt.rumos.model;

public class CreditCard extends Card {

    private Double plafond = 500.00;
    private Integer dailyWithdrawals;

	public Integer getDailyWithdrawals() {
		return dailyWithdrawals;
	}

	public void setDailyWithdrawals(Integer dailyWithdrawals) {
		this.dailyWithdrawals = dailyWithdrawals;
	}

	public Double getPlafond() {
	return plafond;
    }

    public void setPlafond(Double plafond) {
	this.plafond = plafond;
    }

	@Override
	public String toString() {
		return "CreditCard [plafond=" + plafond + ", dailyWithdrawals=" + dailyWithdrawals + ", Id=" + getId()
				+ ", ClientId=" + getClientId() + ", Pin=" + getPin() + ", AccountId=" + getAccountId()
				+ "]";
	}

	
	
}