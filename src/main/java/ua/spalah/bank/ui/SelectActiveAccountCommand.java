package ua.spalah.bank.ui;

/**
 * Created by Oleg on 15.01.2017.
 */
public class SelectActiveAccountCommand extends AbstractCommand implements Command {
    public SelectActiveAccountCommand(IO io) {
        super(io);
    }

    @Override
    public void execute() {
        write(BankCommander.currentClient.getName() + "'s Active acc: " + BankCommander.currentClient.getActiveAcc());
    }

    @Override
    public void printCommandInfo() {
        write("3. SelectActiveAccountCommand");
    }
}