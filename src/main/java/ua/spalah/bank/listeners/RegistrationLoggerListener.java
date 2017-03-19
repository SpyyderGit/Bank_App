package ua.spalah.bank.listeners;

import ua.spalah.bank.models.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jerald_PC on 08.01.2017.
 */
public class RegistrationLoggerListener implements ClientRegistrationListener{

    @Override
    public void onClientAdded(Client c) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        System.out.println("Client" + c.getName() + " added on " + dateFormat.format(date));
    }
}
