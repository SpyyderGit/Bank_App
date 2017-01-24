package ua.spalah.bank.services;

import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.models.accounts.Account;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public interface AccountService {
    void deposit(Account account, double amount);

    void withdraw(Account account, double amount) throws NotEnoughFundsException;

    void transfer(Account fromAccount, Account toAccount, double amount) throws NotEnoughFundsException;
}
