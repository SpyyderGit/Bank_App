package ua.spalah.bank.ui;

import ua.spalah.bank.models.Bank;
import ua.spalah.bank.models.accounts.Account;

import java.util.Scanner;

/**
 * Created by Oleg on 15.01.2017.
 */
public class SelectActiveAccountCommand implements Command {
    @Override
    public void execute() {
        System.out.println(BankCommander.currentClient.getName() + "'s Active acc: " + BankCommander.currentClient.getActiveAcc());
    }

    @Override
    public void printCommandInfo() {
        System.out.println("3. SelectActiveAccountCommand");
    }
}