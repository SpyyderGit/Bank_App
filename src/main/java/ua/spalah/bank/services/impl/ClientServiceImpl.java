package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientDao;
import ua.spalah.bank.services.ClientService;

import java.util.List;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public class ClientServiceImpl implements ClientService {
    ClientDao clientDao;
    AccountDao accountDao;

    public ClientServiceImpl(ClientDao clientDao, AccountDao accountDao) {
        this.clientDao = clientDao;
        this.accountDao = accountDao;
    }

    @Override
    public Client findClientByName(String name) {
        if (name.equals(""))
            throw new IllegalArgumentException();
        if (name.isEmpty())
            throw new IllegalArgumentException();
        return clientDao.findByName(name);
    }

    @Override
    public List<Client> findAllClients() {

        return clientDao.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        clientDao.save(client);
        return client;
    }

    @Override
    public void addAcc(Client client, Account acc) {
        accountDao.save(client.getClientId(), acc);
        client.getListOfAcc().add(acc);
    }


    @Override
    public void deleteClient(Client client) throws ClientNotFoundException {
        clientDao.delete(client.getClientId());
    }

    @Override
    public void setActiveAccService(Account activeAcc, Client client) {

        activeAcc.setAccId(accountDao.getMaxId());
        System.out.println(activeAcc.getAccId());
        Client clientTmp = new Client(client.getName(), client.getGender(), client.getEmail(), client.getPhone(),
                client.getCity(), client.getClientId(), activeAcc.getAccId());
        clientTmp.setActiveAcc(activeAcc);
        clientDao.update(clientTmp);
        System.out.println(clientTmp.getClientId() + "\nActive account is changed, new ACC: " + clientTmp.getActiveAcc());
    }
}