package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.ClientService;

import java.util.List;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public class ClientServiceImpl implements ClientService {

    @Override
    public Client findClientByName(Bank bank, String name) throws ClientNotFoundException {
        for (Client c : bank.getAllClients()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        throw new ClientNotFoundException(name);
    }

    @Override
    public List<Client> findAllClients(Bank bank) {
        return bank.getAllClients();
    }

    @Override
    public Client saveClient(Bank bank, Client client) {
        bank.getAllClients().add(client);
        return client;
    }

    @Override
    public void deleteClient(Bank bank, Client client) throws ClientNotFoundException {
        if (bank.getAllClients().contains(client)) {
            bank.getAllClients().remove(client);
        } else {
            throw new ClientNotFoundException(client.getName());
        }
    }
}