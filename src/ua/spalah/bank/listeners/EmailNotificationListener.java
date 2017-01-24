package ua.spalah.bank.listeners;

import ua.spalah.bank.models.Client;

/**
 * Created by Jerald_PC on 08.01.2017.
 */
public class EmailNotificationListener implements ClientRegistrationListener {
    @Override
    public void onClientAdded(Client c) {
        System.out.println("Notification email for client " + c.getName() + " has been sent");
    }
}
