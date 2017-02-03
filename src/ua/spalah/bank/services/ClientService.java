package ua.spalah.bank.services;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;

import java.util.List;
import java.util.Map;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public interface ClientService {
    Client findClientByName(Bank bank, String name) throws ClientNotFoundException;

    Map<String, Client> findAllClients(Bank bank);

    Client saveClient(Bank bank, Client client);

    void addAcc(Client client, Account acc);

    void deleteClient(Bank bank, Client client) throws ClientNotFoundException;

}
