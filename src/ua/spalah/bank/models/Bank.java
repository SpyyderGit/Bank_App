package ua.spalah.bank.models;

import ua.spalah.bank.listeners.ClientRegistrationListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerald_PC on 08.01.2017.
 */
public class Bank {

    private ArrayList<Client> clients = new ArrayList<>();
    private List<ClientRegistrationListener> listeners = new ArrayList<>();

    public void addListener(ClientRegistrationListener listener) {

        listeners.add(listener);
    }

    public ArrayList<Client> getAllClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "clients=" + clients + "}";
    }
}