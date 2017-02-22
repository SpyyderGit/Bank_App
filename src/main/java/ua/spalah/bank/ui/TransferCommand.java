package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class TransferCommand extends AbstractCommand implements Command {
    private AccountService accountService;
    private ClientService clientService;

    public TransferCommand(ClientService clientService, AccountService accountService, IO io) {
        super(io);
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @Override
    public void execute() throws ClientNotFoundException, IOException {

        write("Select client: ");
        String toClient = read();
        Account toAccount = null;
        write("Create amount: ");
        double amount = Double.parseDouble(read());

        for (Client c : clientService.findAllClients()) {
            if (c.getName().equals(toClient))
                toAccount = c.getActiveAcc();
        }


        try {
            accountService.transfer(BankCommander.currentClient.getActiveAcc(), toAccount, amount);
        } catch (NotEnoughFundsException e) {
            e.printStackTrace();
        }
        write("transaction on sum " + amount + " from " + BankCommander.currentClient.getActiveAcc() + " to " + toAccount
                + "\nOperation is complated...");

    }

    @Override
    public void printCommandInfo() {
        write("6. TransferCommand");
    }
}
