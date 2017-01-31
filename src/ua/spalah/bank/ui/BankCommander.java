package ua.spalah.bank.ui;

import ua.spalah.bank.models.Gender;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountServiceImpl;
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

        Scanner pClient = new Scanner(ClassLoader.getSystemResourceAsStream("ua/spalah/bank/resources/clients.txt"));
        Scanner pAcc = new Scanner(ClassLoader.getSystemResourceAsStream("ua/spalah/bank/resources/accounts.txt"));

        String[] clientData;
        String[] accData;

        while (pClient.hasNext()) {
            clientData = pClient.nextLine().split("::");
            clientService.saveClient(currentBank, new Client(clientData[0], (clientData[1].equals("MALE") ? Gender.MALE : Gender.FAMALE), clientData[2], clientData[3], clientData[4]));
        }

        while (pAcc.hasNext()) {
            accData = pAcc.nextLine().split("::");
            if (currentBank.getAllClients().containsKey(accData[0])) {
                if (accData[1].equals("CHECKING")) {
                    clientService.addAcc(currentBank.getAllClients().get(accData[0]), new CheckingAccount(Double.parseDouble(accData[2]), Double.parseDouble(accData[3])));
                } else if (accData[1].equals("SAVING")) {
                    clientService.addAcc(currentBank.getAllClients().get(accData[0]), new SavingAccount(Double.parseDouble(accData[2])));
                }
            }
        }
        commands = new Command[]

                {
                        new FindClientCommand(clientService),
                        new GetAccountsCommand(),
                        new SelectActiveAccountCommand(),
                        new DepositCommand(accountService),
                        new WithdrawCommand(accountService),
                        new TransferCommand(accountService),
                        new AddClientCommand(clientService),
                        new AddAccountCommand(),
                        new RemoveClientCommand(clientService),
                        new GetBankInfoCommand(),
                        new ExitCommand()
                }

        ;
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