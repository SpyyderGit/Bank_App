package ua.spalah.bank.listeners;

import ua.spalah.bank.models.Client;

/**
 * Created by Jerald_PC on 08.01.2017.
 */
public class PrintClientListener implements ClientRegistrationListener {
    @Override
    public void onClientAdded(Client c) {
        System.out.println("Client info: " + c);
    }
}
