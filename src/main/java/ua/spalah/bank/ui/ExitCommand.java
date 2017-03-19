package ua.spalah.bank.ui;

/**
 * Created by Oleg on 15.01.2017.
 */
public class ExitCommand extends AbstractCommand implements Command {
    public ExitCommand(IO io) {
        super(io);
    }

    @Override
    public void execute() {

        System.exit(0);
    }

    @Override
    public void printCommandInfo() {
        write("11. ExitCommand");
    }
}
