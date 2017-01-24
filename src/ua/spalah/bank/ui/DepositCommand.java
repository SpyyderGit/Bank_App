package ua.spalah.bank.ui;

import ua.spalah.bank.services.AccountService;

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class DepositCommand implements Command {
    AccountService accountService;

    public DepositCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        Scanner bal = new Scanner(System.in);
        System.out.print("Create sum: ");
        double sum = bal.nextDouble();
        accountService.deposit(BankCommander.currentClient.getActiveAcc(), sum);
        System.out.println("+" + sum + "\nOperation is complated...");
    }

    @Override
    public void printCommandInfo() {
        System.out.println("4. Deposit command");
    }
}
