package ua.spalah.bank.services.impl;

import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientDao;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.ui.ConsoleIO;
import ua.spalah.bank.ui.IO;

import java.io.IOException;
import java.util.*;

/**
 * Created by User on 22.02.2017.
 */
public class BankReportServiceImpl implements BankReportService {

    @Override
    public int getNumberOfClients(ClientDao clientDao) {
        return clientDao.findAll().size();
    }

    @Override
    public int getNumberOfAccounts(AccountDao accountDao) {

        return accountDao.findAll().size();
    }

    @Override
    public double getTotalAccountSum(AccountDao accountDao) {
        double sum = 0;

        for (Account a : accountDao.findAll()) {
            sum += a.getBalance();
        }

        return sum;
    }

    @Override
    public double getBankCreditSum(AccountDao accountDao) {
        double sum = 0;

        for (Account a : accountDao.findAll()) {
            if (a.getType().toString().equals("CHECKING")) {
                sum += a.getBalance();
            }
        }
        return sum;
    }

    @Override
    public List<Client> getClientsSortedByName(ClientDao clientDao) {
        List<Client> clients = clientDao.findAll();

        Collections.sort(clients, new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return clients;
    }

    @Override
    public List<Client> getClientByCity(ClientDao clientDao) {
        IO read = new ConsoleIO();
        List<Client> clients = new ArrayList<>();

        try {
            System.out.println("Input name of city: ");
            String city = read.read();
            for (Client c : clientDao.findAll()) {
                if (city.equals(c.getCity())) {
                    clients.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
