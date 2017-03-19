package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientDao;

import java.util.List;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public class AccountServiceImpl implements AccountService {

    AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;

    }

    @Override
    public void deposit(Account account, double amount) {
        switch (account.getType()) {
            case SAVING: {
                double balance = account.getBalance();
                account.setBalance(balance + amount);
                SavingAccount sAcc = null;
                try {
                    sAcc = new SavingAccount(account.getBalance());
                } catch (OverdraftLimitExceededException e) {
                    e.printStackTrace();
                }
                accountDao.update(account.getAccId(), sAcc);
                break;
            }
            case CHECKING: {
                double available = account.getBalance();
                account.setBalance(available + amount);
                CheckingAccount accountTmp = (CheckingAccount) account;
                CheckingAccount cAcc = null;
                try {
                    cAcc = new CheckingAccount(account.getBalance(), accountTmp.getOverdraft());
                } catch (OverdraftLimitExceededException e) {
                    e.printStackTrace();
                }
                accountDao.update(account.getAccId(), cAcc);
            }
            break;
            default:
                throw new IllegalArgumentException("type is not found");
        }
    }

    @Override
    public void withdraw(Account account, double amount) throws NotEnoughFundsException {
        switch (account.getType()) {
            case SAVING: {
                double balance = account.getBalance();
                if (balance >= amount) {
                    account.setBalance(balance - amount);
                    SavingAccount sAcc = new SavingAccount(account.getBalance());
                    accountDao.update(account.getAccId(), sAcc);
                } else {
                    throw new NotEnoughFundsException(balance);
                }
                break;
            }
            case CHECKING: {
                double available = account.getBalance();
                if (available >= amount && (available - amount) >= ((CheckingAccount) account).getOverdraft()) {
                    account.setBalance(available - amount);
                    CheckingAccount accountTmp = (CheckingAccount) account;
                    CheckingAccount cAcc = new CheckingAccount(account.getBalance(), accountTmp.getOverdraft());
                    accountDao.update(account.getAccId(), cAcc);
                } else {
                    throw new OverdraftLimitExceededException(available);
                }
                break;
            }
            default:
                throw new IllegalArgumentException("type is not found");
        }
    }

    @Override
    public void transfer(Account fromAccount, Account toAccount, double amount) throws NotEnoughFundsException {
        if (amount < 0)
            throw new IllegalArgumentException("Amount can't be negative.");
        if (fromAccount.getBalance() >= amount) {
            withdraw(fromAccount, amount);
            deposit(toAccount, amount);
        } else {
            throw new NotEnoughFundsException(amount);
        }
    }
}