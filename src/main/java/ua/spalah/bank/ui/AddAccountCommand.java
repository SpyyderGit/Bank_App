package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class AddAccountCommand extends AbstractCommand implements Command {


    ClientService clientService;

    public AddAccountCommand(ClientService clientService, IO io) {
        super(io);
        this.clientService = clientService;
    }

    @Override
    public void execute() throws OverdraftLimitExceededException, IOException {
        write("1 - Checking account\n2 - Saving account");

        int n = Integer.parseInt(read());
        double setBal = Double.parseDouble(read());
        Account acc = null;

        switch (n) {
            case 1: {
                double setOverdraft = Double.parseDouble(read());
                acc = new CheckingAccount(setBal, setOverdraft);
                break;
            }
            case 2: {
                acc = new SavingAccount(setBal);
                break;
            }
        }
        clientService.addAcc(BankCommander.currentClient, acc);
        write("Do you wont activate this acc? y/n");
        if (read().equals("y")) {
            clientService.setActiveAccService(acc, BankCommander.currentClient);
        }
        write("Operation is compalted");
    }

    @Override
    public void printCommandInfo() {
        write("8. AddAccountCommand ");
    }
}
