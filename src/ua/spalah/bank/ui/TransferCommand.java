package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.services.AccountService;

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class TransferCommand implements Command {
    private AccountService accountService;

    public TransferCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() throws ClientNotFoundException {

        Scanner client = new Scanner(System.in);
        System.out.print("Select client: ");
        String toClient = client.nextLine();
        Account toAccount = null;
        System.out.print("Create amount: ");
        double amount = client.nextInt();

        for (Client c : BankCommander.currentBank.getAllClients()) {
            if (c.getName().equals(toClient))
                toAccount = c.getActiveAcc();
        }


        try {
            accountService.transfer(BankCommander.currentClient.getActiveAcc(), toAccount, amount);
        } catch (NotEnoughFundsException e) {
            e.printStackTrace();
        }
        System.out.println("transaction on sum " + amount + " from " + BankCommander.currentClient.getActiveAcc() + " to " + toAccount
                          + "\nOperation is complated...");

    }

    @Override
    public void printCommandInfo() {
        System.out.println("6. TransferCommand");
    }
}
