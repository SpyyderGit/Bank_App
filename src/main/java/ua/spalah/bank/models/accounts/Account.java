package ua.spalah.bank.models.accounts;

/**
 * Created by User on 13.01.2017.
 */
public interface Account {
    AccountType getType();


    void setAccId(long id);

    long getAccId();



    void setClientId(long id);

    long getClientId();

    double getBalance();

    void setBalance(double balance);
}
