package ua.spalah.bank.models.accounts;

import ua.spalah.bank.exceptions.OverdraftLimitExceededException;

/**
 * Created by Jerald_PC on 08.01.2017.
 */
public class CheckingAccount extends SavingAccount {

    private double overdraft;
    private long accId;
    private long clientId;


    public CheckingAccount(double balance, double overdraft) throws OverdraftLimitExceededException {
        super(balance);
        this.overdraft = overdraft;
    }

    @Override
    public long getClientId() {
        return clientId;
    }

    @Override
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Override
    public AccountType getType() {

        return AccountType.CHECKING;
    }

    public double getOverdraft() {

        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    public void setAccId(long accId) {
        this.accId = accId;
    }

    @Override
    public long getAccId() {
        return accId;
    }

    @Override
    public String toString() {
        return "(id" + accId + " Type: " + getClass().getSimpleName() + " balance=" + getBalance() + " overdraft=" + overdraft + ")";
    }
}