package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class AddAccountCommand implements Command {

    @Override
    public void execute() throws OverdraftLimitExceededException {
        System.out.println("1 - Checking account\n2 - Saving account");
        Scanner bal = new Scanner(System.in);
        int n = bal.nextInt();
        double setBal = bal.nextDouble();
        Account acc = null;

        switch (n) {
            case 1: {
                double setOverdraft = bal.nextDouble();
                acc = new CheckingAccount(setBal, setOverdraft);
                break;
            }
            case 2: {
                acc = new SavingAccount(setBal);
                break;
            }
        }

        BankCommander.currentClient.addAcc(acc);
        System.out.println("Do you wont activate this acc? y/n");

        System.out.println("Operation is compalted");
    }

    @Override
    public void printCommandInfo() {
        System.out.println("8. AddAccountCommand ");
    }
}
