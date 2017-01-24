package ua.spalah.bank.ui;

import ua.spalah.bank.Gender;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
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

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class BankCommander {
    // хранит в себе банк с кототорым мы работаем
    public static Bank currentBank;

    // хранит в себе клиента с которым мы работаем в данный момент
    public static Client currentClient;

    // Список команд которые мы можем выполнять
    private Command[] commands;

    public BankCommander() throws OverdraftLimitExceededException {
        init();
    }

    private void init() throws OverdraftLimitExceededException {
        // здесь проводим инициализацию банка начальными данными
        // а также создаем все необходимые объекты команд

        Bank prostoBank = new Bank();
        currentBank = prostoBank;

        AccountService accountService = new AccountServiceImpl();
        ClientService clientService = new ClientServiceImpl();
        BankReportService bankReportService = new BankReportServiceImpl();

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
        Account accDepositJohn = new SavingAccount(1000);
        Account accCreditJohn = new CheckingAccount(2000, 300);
        john.addAcc(accCreditJohn);
        john.addAcc(accDepositJohn);

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

        anya.setCity("Dnepr");
        john.setCity("Lviv");
        petya.setCity("Dnepr");
        sofia.setCity("Lviv");
        katya.setCity("Dnepr");



        commands = new Command[]{new FindClientCommand(clientService),
                new GetAccountsCommand(),
                new SelectActiveAccountCommand(),
                new DepositCommand(accountService),
                new WithdrawCommand(accountService),
                new TransferCommand(accountService),
                new AddClientCommand(clientService),
                new AddAccountCommand(),
                new RemoveClientCommand(clientService),
                new GetBankInfoCommand(),
                new ExitCommand()};
    }




    public void run() throws ClientNotFoundException, OverdraftLimitExceededException {
        // запускаем наше приложение
        // выводим в цикле доступные команды
        // ждем от пользователя выбора
        // после выбора команды передаем управление ей
        // вызываем ее метод execute

        for (Command com : commands) {
            com.printCommandInfo();
        }

        Scanner select = new Scanner(System.in);

        while (select.hasNext()) {
            int item = select.nextInt();
            if (item > commands.length) {
                throw new ArrayIndexOutOfBoundsException("Item of menu not found");
            }
            commands[item - 1].execute();
        }
    }

    // запуск нашего приложения
    public static void main(String[] args) throws ClientNotFoundException, OverdraftLimitExceededException {
        BankCommander bankCommander = new BankCommander();
        bankCommander.run();
    }
}