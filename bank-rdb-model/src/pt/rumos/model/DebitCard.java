package pt.rumos.model;

public class DebitCard extends Card {

    private Double lastWithdrawal;

	public Double getLastWithdrawal() {
		return lastWithdrawal;
    }

    public void setLastWithdrawal(Double dailyWithdrawal) {
    	this.lastWithdrawal = dailyWithdrawal;
    }

	@Override
	public String toString() {
		return "DebitCard [lastWithdrawal=" + lastWithdrawal + ", Id=" + getId() + ", ClientId="
				+ getClientId() + ", Pin=" + getPin() + ", AccountId=" + getAccountId() + "]";
	}
}