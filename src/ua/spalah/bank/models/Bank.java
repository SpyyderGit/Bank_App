package ua.spalah.bank.models;

import ua.spalah.bank.listeners.ClientRegistrationListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerald_PC on 08.01.2017.
 */
public class Bank {

    private Map<String, Client> clients = new HashMap<>();
    private List<ClientRegistrationListener> listeners = new ArrayList<>();

    public void addListener(ClientRegistrationListener listener) {

        listeners.add(listener);
    }

    public Map<String, Client> getAllClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "clients=" + clients + "}";
    }
}