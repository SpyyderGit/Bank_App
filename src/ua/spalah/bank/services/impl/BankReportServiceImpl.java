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
        for (Client c : bank.getAllClients()) {
            for (Account a : c.getListOfAcc()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public double getTotalAccountSum(Bank bank) {
        int count = 0;
        for (Client c : bank.getAllClients()) {
            for (Account a : c.getListOfAcc()) {
                count += a.getBalance();
            }
        }
        return count;
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        int count = 0;
        for (Client c : bank.getAllClients()) {
            for (Account a : c.getListOfAcc()) {
                if (a.getClass().getSimpleName().equals("CheckingAccount")) {
                    count += a.getBalance();
                }
            }
        }
        return count;
    }

    @Override
    public List<Client> getClientsSortedByName(Bank bank) {
        Collections.sort(bank.getAllClients(), new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return bank.getAllClients();
    }

    @Override
    public Map<String, List<Client>> getClientByCity(Bank bank) {
           Map<String, List<Client>> clientMap = new HashMap<>();
           List<Client> clients = new ArrayList<>();
           Scanner city = new Scanner(System.in);
           String search = city.nextLine();

        for (Client c : bank.getAllClients()) {
            if (c.getCity().equals(search)) {
                clients.add(c);
                clientMap.put(c.getCity(), clients);
            }
        }
        return clientMap;
    }
}