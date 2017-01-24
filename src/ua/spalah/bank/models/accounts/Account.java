package ua.spalah.bank.models.accounts;

/**
 * Created by User on 13.01.2017.
 */
public interface Account {
    AccountType getType();

    double getBalance();

    void setBalance(double balance);
}
