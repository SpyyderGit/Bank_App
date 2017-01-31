package ua.spalah.bank.services.impl;

import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.services.BankReportService;

import java.util.*;

/**
 * Created by Jerald_PC on 09.01.2017.
 */
public class BankReportServiceImpl implements BankReportService {
    @Override
    public int getNumberOfClients(Bank bank) {

        return bank.getAllClients().size();
    }

    @Override
    public int getNumberOfAccounts(Bank bank) {
        int count = 0;
        for (Client c : bank.getAllClients().values()) {
            for (Account a : c.getListOfAcc()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public double getTotalAccountSum(Bank bank) {
        int count = 0;
        for (Client c : bank.getAllClients().values()) {
            for (Account a : c.getListOfAcc()) {
                count += a.getBalance();
            }
        }
        return count;
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        int count = 0;
        for (Client c : bank.getAllClients().values()) {
            for (Account a : c.getListOfAcc()) {
                if (a.getClass().getSimpleName().equals("CheckingAccount")) {
                    count += a.getBalance();
                }
            }
        }
        return count;
    }

    @Override
    public Map<String, Client> getClientsSortedByName(Bank bank) {
        Map<String, Client> sort = new TreeMap<>();
        sort.putAll(bank.getAllClients());
        return sort;
    }

    @Override
    public Map<String, List<Client>> getClientByCity(Bank bank) {
        Map<String, List<Client>> clientMap = new HashMap<>();
        List<Client> clients = new ArrayList<>();
        Scanner city = new Scanner(System.in);
        String search = city.nextLine();

        for (Client c : bank.getAllClients().values()) {
            if (c.getCity().equals(search)) {
                clients.add(c);
                clientMap.put(c.getCity(), clients);
            }
        }
        return clientMap;
    }
}