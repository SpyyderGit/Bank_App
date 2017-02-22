package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.*;
import ua.spalah.bank.services.impl.*;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Oleg on 15.01.2017.
 */
public class BankCommander {

    // хранит в себе клиента с которым мы работаем в данный момент
    public static Client currentClient;


    public static Connection con() {
        Connection con = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:~/test";
            con = DriverManager.getConnection(url, "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // Список команд которые мы можем выполнять
    private Command[] commands;

    private ConsoleIO read = new ConsoleIO();

    public BankCommander() throws OverdraftLimitExceededException {
        init();
    }

    private void init() throws OverdraftLimitExceededException {
        // здесь проводим инициализацию банка начальными данными
        // а также создаем все необходимые объекты команд


//===========================================================

        AccountDao accountDao = new AccountDaoImpl();
        ClientDao clientDao = new ClientDaoImpl(accountDao);
        AccountService accountService = new AccountServiceImpl(accountDao);
        ClientService clientService = new ClientServiceImpl(clientDao, accountDao);
        BankReportService bankReportService = new BankReportServiceImpl();

        ConsoleIO io = new ConsoleIO();

        commands = new Command[]{
                new FindClientCommand(clientService, io),
                new GetAccountsCommand(io),
                new SelectActiveAccountCommand(io),
                new DepositCommand(accountService, io),
                new WithdrawCommand(accountService, io),
                new TransferCommand(clientService, accountService, io),
                new AddClientCommand(clientService, io),
                new AddAccountCommand(clientService, io),
                new RemoveClientCommand(clientService, io),
                new GetBankInfoCommand(bankReportService, clientDao, accountDao, io),
                new ExitCommand(io)
        };
    }

    public void run() throws ClientNotFoundException, OverdraftLimitExceededException, IOException {
        // запускаем наше приложение
        // выводим в цикле доступные команды
        // ждем от пользователя выбора
        // после выбора команды передаем управление ей
        // вызываем ее метод execute

        for (Command com : commands) {
            com.printCommandInfo();
        }

        while (read != null) {
            int item = Integer.parseInt(read.read());
            if (item > commands.length) {
                throw new ArrayIndexOutOfBoundsException("Item of menu not found");
            }
            commands[item - 1].execute();
        }
    }

    // запуск нашего приложения
    public static void main(String[] args) throws ClientNotFoundException, OverdraftLimitExceededException, IOException {
        BankCommander bankCommander = new BankCommander();
        bankCommander.run();
    }
}