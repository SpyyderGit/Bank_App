package ua.spalah.bank.ui;

import java.io.IOException;

/**
 * Created by User on 01.02.2017.
 */
public class AbstractCommand {
    private final IO io;

    public AbstractCommand(IO io) {
        this.io = io;
    }



    protected String read() throws IOException {
        return io.read();
    }

    protected void write(String s) {

        io.write(s);
    }

}
