package ua.spalah.bank.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by User on 14.02.2017.
 */
public class Connect {
    private static Connect ourInstance = new Connect();

    public static Connect getInstance() {
        return ourInstance;
    }

    private Connect() {

    }
}
