package ua.spalah.bank.services;

import ua.spalah.bank.models.Client;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by User on 22.02.2017.
 */
public interface BankReportService {
    int getNumberOfClients(ClientDao clientDao); // общее количество клиентов

    int getNumberOfAccounts(AccountDao accountDao); // общее количество счетов

    double getTotalAccountSum(AccountDao accountDao); // общая сумма по всем счетам

    double getBankCreditSum(AccountDao accountDao); // возвращает сумму отрицательных балансов по всем счетам

    List<Client> getClientsSortedByName(ClientDao clientDao); // Возвращает список клиентов отсортированных по имени

    List<Client> getClientByCity(ClientDao clientDao);
}
