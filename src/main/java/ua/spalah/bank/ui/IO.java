package ua.spalah.bank.ui;

import java.io.IOException;

/**
 * Created by User on 01.02.2017.
 */
public interface IO {
    String read() throws IOException;

    void write(String s);
}
