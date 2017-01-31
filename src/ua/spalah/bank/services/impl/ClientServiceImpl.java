package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.services.ClientService;

import java.util.List;
import java.util.Map;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public class ClientServiceImpl implements ClientService {

    @Override
    public Client findClientByName(Bank bank, String name) throws ClientNotFoundException {
        if (name.equals(""))
            throw new IllegalArgumentException();
        if (name == null)
            throw new NullPointerException();


        for (Client c : bank.getAllClients().values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        throw new ClientNotFoundException(name);
    }

    @Override
    public Map<String, Client> findAllClients(Bank bank) {
        if (bank.getAllClients().isEmpty())
            throw new NullPointerException();
        return bank.getAllClients();
    }

    @Override
    public Client saveClient(Bank bank, Client client) {
        bank.getAllClients().put(client.getName(), client);
        return client;
    }

    @Override
    public void addAcc(Client client, Account acc) {
        if (client.getActiveAcc() == null) {
            client.setActiveAcc(acc);
        }
        client.getListOfAcc().add(acc);
    }

    @Override
    public void deleteClient(Bank bank, Client client) throws ClientNotFoundException {
        System.out.print("Enter name for delete: ");
        if (bank.getAllClients().containsValue(client)) {
            bank.getAllClients().remove(client.getName());
        } else {
            throw new ClientNotFoundException(client.getName());
        }
    }
}