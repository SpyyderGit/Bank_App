package ua.spalah.bank.ui;

import ua.spalah.bank.services.AccountService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class DepositCommand extends AbstractCommand implements Command {
    AccountService accountService;

    public DepositCommand(AccountService accountService, IO io) {
        super(io);
        this.accountService = accountService;
    }

    @Override
    public void execute() throws IOException {

        write("Create sum: ");
        double sum = Double.parseDouble(read());


        accountService.deposit(BankCommander.currentClient.getActiveAcc(), sum);
        write("+" + sum + "\nOperation is complated...");
    }

    @Override
    public void printCommandInfo() {
        write("4. Deposit command");
    }
}
