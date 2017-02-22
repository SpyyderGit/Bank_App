package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.services.AccountService;

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class WithdrawCommand extends AbstractCommand implements Command {
    AccountService accountService;

    public WithdrawCommand(AccountService accountService, IO io) {
        super(io);
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        Scanner draw = new Scanner(System.in);
        System.out.print("Create sum: ");
        double amount = draw.nextDouble();
        try {
            accountService.withdraw(BankCommander.currentClient.getActiveAcc(), amount);
        } catch (NotEnoughFundsException e) {
            e.printStackTrace();
        }
        System.out.println("-" + amount + " Operation is complated...");
    }

    @Override
    public void printCommandInfo() {
        write("5. WithdrawCommand");
    }
}
