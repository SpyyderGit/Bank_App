package ua.spalah.bank.services;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;

import java.util.List;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public interface ClientService {
    Client findClientByName(String name) throws ClientNotFoundException;

    List<Client> findAllClients();

    Client saveClient(Client client);

    void addAcc(Client client, Account acc);

    void deleteClient(Client client) throws ClientNotFoundException;

    void setActiveAccService(Account activeAcc, Client client);
}
