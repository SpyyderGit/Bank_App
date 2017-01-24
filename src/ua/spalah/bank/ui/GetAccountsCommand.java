package ua.spalah.bank.ui;

/**
 * Created by Oleg on 15.01.2017.
 */
public class GetAccountsCommand implements Command {
    @Override
    public void execute() {
        System.out.println(BankCommander.currentClient.getListOfAcc());
        System.out.println("Active account: " + BankCommander.currentClient.getActiveAcc());
    }

    @Override
    public void printCommandInfo() {
        System.out.println("2. GetAccountsCommand");
    }
}
