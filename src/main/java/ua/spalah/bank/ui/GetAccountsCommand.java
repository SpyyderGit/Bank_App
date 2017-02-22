package ua.spalah.bank.ui;

/**
 * Created by Oleg on 15.01.2017.
 */
public class GetAccountsCommand extends AbstractCommand implements Command {
    public GetAccountsCommand(IO io) {
        super(io);
    }

    @Override
    public void execute() {
        write(BankCommander.currentClient.getListOfAcc().toString());
        write("Active account: " + BankCommander.currentClient.getActiveAcc());
    }

    @Override
    public void printCommandInfo() {

        write("2. GetAccountsCommand");
    }
}
