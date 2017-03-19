package ua.spalah.bank.ui;

import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientDao;
import ua.spalah.bank.services.ClientService;

import java.io.IOException;

/**
 * Created by Oleg on 15.01.2017.
 */
public class GetBankInfoCommand extends AbstractCommand implements Command {
    BankReportService bankReportService;
    ClientDao clientDao;
    AccountDao accountDao;


    public GetBankInfoCommand(BankReportService bankReportService, ClientDao clientDao, AccountDao accountDao, IO io) {
        super(io);
        this.bankReportService = bankReportService;
        this.clientDao = clientDao;
        this.accountDao = accountDao;
    }

    @Override
    public void execute() {

        IO read = new ConsoleIO();

        write("1. getNumberOfClients\n" +
                "2. getNumberOfAccounts\n" +
                "3. getTotalAccountSum\n" +
                "4. getBankCreditSum\n" +
                "5. getClientsSortedByName\n" +
                "6. getClientByCity\n" +
                "7. return to main menu");

        while (read != null) {
            try {
                switch (read()) {
                    case "1": {
                        System.out.println("Number of clients: " + bankReportService.getNumberOfClients(clientDao));
                        break;
                    }
                    case "2": {
                        System.out.println("Number of accounts " + bankReportService.getNumberOfAccounts(accountDao));
                        break;
                    }
                    case "3": {
                        System.out.println("Total balance: " + bankReportService.getTotalAccountSum(accountDao));
                        break;
                    }
                    case "4": {
                        System.out.println("Total credit sum: " + bankReportService.getBankCreditSum(accountDao));
                        break;
                    }
                    case "5": {
                        System.out.println("Sorted by name:\n" + bankReportService.getClientsSortedByName(clientDao));
                        break;
                    }
                    case "6": {
                        System.out.println("By city:\n" + bankReportService.getClientByCity(clientDao));
                        break;
                    }
                    case "7": {
                        read = null;
                        System.err.println("You are in main menu");
                        break;
                    }
                    default: {
                        write("Item of menu is not found");

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void printCommandInfo() {
        write("10. GetBankInfoCommand");
    }
}
