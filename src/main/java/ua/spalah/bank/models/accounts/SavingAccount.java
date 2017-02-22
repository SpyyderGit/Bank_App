package ua.spalah.bank.models.accounts;

import ua.spalah.bank.exceptions.OverdraftLimitExceededException;

/**
 * Created by Jerald_PC on 08.01.2017.
 */
public class SavingAccount implements Account {

    private double balance;
    private long accId;
    private long clientId;

    public SavingAccount(double balance) throws OverdraftLimitExceededException {
        if (balance < 0) {
            throw new OverdraftLimitExceededException(balance);
        } else {
            this.balance = balance;
        }
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
        return AccountType.SAVING;
    }


    @Override
    public double getBalance() {

        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
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
        return "SavingAccount{" + "id: " + accId +
                " balance=" + balance +
                '}';
    }


}
