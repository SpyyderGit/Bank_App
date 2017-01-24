package ua.spalah.bank.ui;

/**
 * Created by Oleg on 15.01.2017.
 */
public class GetBankInfoCommand implements Command{
    @Override
    public void execute() {
        System.out.println(BankCommander.currentBank);
    }

    @Override
    public void printCommandInfo() {
        System.out.println("10. GetBankInfoCommand");
    }
}
