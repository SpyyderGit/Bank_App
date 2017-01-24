package ua.spalah.bank;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.BankReportServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jerald_PC on 08.01.2017.
 */


public class MainBank {


    public static void main(String[] args) throws NotEnoughFundsException, ClientNotFoundException {
        MainBank b = new MainBank();

        System.out.println("");
        ////===================== Создаем банк ======================
        Bank prostoBank = new Bank();

//================ Создаем сервисы =======================

        AccountService accountService = new AccountServiceImpl();
        ClientService clientService = new ClientServiceImpl();
        BankReportService bankReportService = new BankReportServiceImpl();

//==================== Создаем клиентов ===================

        Client john = new Client("John", Gender.MALE);
        Client katya = new Client("Katya", Gender.FAMALE);
        Client petya = new Client("Petya", Gender.MALE);
        Client sofia = new Client("Sofia", Gender.FAMALE);
        Client anya = new Client("Anya", Gender.FAMALE);

//================== Регистрируем клиентов в банке ==========

        clientService.saveClient(prostoBank, john);
        clientService.saveClient(prostoBank, katya);
        clientService.saveClient(prostoBank, petya);
        clientService.saveClient(prostoBank, sofia);
        clientService.saveClient(prostoBank, anya);


//=================== Добавляем им счета ====================
//-------------------- для Джона ----------------------------
        Account accDepositJohn = new SavingAccount(100);
        Account accCreditJohn = new CheckingAccount(200, 300);
        john.addAcc(accCreditJohn);
        john.addAcc(accDepositJohn);
        john.setActiveAcc(accDepositJohn);
//-------------------- для Кати -------------------------------
        Account accDepositKatya = new SavingAccount(150);
        Account accCreditKatya = new CheckingAccount(250, 350);
        katya.addAcc(accCreditKatya);
        katya.addAcc(accDepositKatya);
//------------------ для Пети ---------------------------------
        Account accDepositPetya = new SavingAccount(50);
        Account accCreditPetya = new CheckingAccount(50, 30);
        petya.addAcc(accCreditPetya);
        petya.addAcc(accDepositPetya);
//---------------- для Софии ----------------------------------
        Account accDepositSof = new SavingAccount(1050);
        Account accCreditSof = new CheckingAccount(1500, 1300);
        sofia.addAcc(accCreditSof);
        sofia.addAcc(accDepositSof);

//------------------ для Ани ----------------------------------

        Account accDepositAnya = new SavingAccount(3050);
        Account accCreditAnya = new CheckingAccount(3500, 3300);
        anya.addAcc(accCreditAnya);
        anya.addAcc(accDepositAnya);

//---------------- добавляем города -------------------------
        anya.setCity("Dnepr");
        john.setCity("Lviv");
        petya.setCity("Dnepr");
        sofia.setCity("Lviv");
        katya.setCity("Dnepr");

        System.out.println(bankReportService.getClientByCity(prostoBank));
        System.out.println("dfdfdf");




//
////=================== Тестим сервисы ==========================
//
////------------------ Количество клиентов ----------------------
//
//        System.out.println("Count of clients: " + bankReportService.getNumberOfClients(prostoBank));
//
////------------------ Общая сумма по счетам --------------------
//
//        System.out.println("All sum: " + bankReportService.getTotalAccountSum(prostoBank));
//
////----------------- Общая сумма по кредитным счетам -----------
//
//        System.out.println("All credit sum: " + bankReportService.getBankCreditSum(prostoBank));
//
////------------------------ Количество счетов ------------------------
//
//        System.out.println("Count of ACC: " + bankReportService.getNumberOfAccounts(prostoBank) + "\n");
////------------------------ Ищем клиента по имени ---------------------------
//        System.out.println("--------------------------------------------------------");
//        System.out.println(clientService.findClientByName(prostoBank, "John"));
//        System.out.println("--------------------------------------------------------\n\n");
//
////------------------------ Выводим всех клиентов ----------------------------
//        System.out.println(clientService.findAllClients(prostoBank));
//
////------------------------ Удаляем клиента ----------------------------------
//        clientService.deleteClient(prostoBank, sofia);
//
////--------------------- ложим деньги на депозит Джону -----------------
//        accountService.deposit(accDepositJohn, 5000);
//
//// ---------------------  Катя снимает 50 у.е. ------------------------
//
//        accountService.withdraw(accCreditKatya, 50);
//
//// ------------------- Джон пересылает Ане 500 у.е. -----------------
//
//        accountService.transfer(accDepositJohn, accDepositAnya, 1000);
//
////--------------------------- Сортируем по имени -----------------------
//        System.out.println("After correct\n");
//        System.out.println(bankReportService.getClientsSortedByName(prostoBank));
//
//// --------------------------- Тестим исключения -----------------------
//
//        System.out.println("======================== Тестим исключения ============================");
//        clientService.deleteClient(prostoBank, new Client("dd", Gender.FAMALE));
//        System.out.println(clientService.findClientByName(prostoBank, "Kolya"));
    }
}