package ua.spalah.bank.services;

import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;

import java.util.List;

/**
 * Created by User on 10.02.2017.
 */
public interface AccountDao {
    Account save(long clientId, Account Account);

    Account update(long accId, Account Account);

    Account saveOrUpdate(long accId, long clientID, Account Account);

    void delete(long id);

    Account find(long id);

    List<Account> findAll();

    List<Account> findByClientId(long clientId);

    Account findActiveAccountByClientName(String clientName);

    int getMaxId();
}
