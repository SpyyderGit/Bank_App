package ua.spalah.bank.ui;

/**
 * Created by Oleg on 15.01.2017.
 */
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public void printCommandInfo() {
        System.out.println("11. ExitCommand");
    }
}
