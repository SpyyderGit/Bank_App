package ua.spalah.bank.ui;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.ClientService;

import java.io.IOException;

/**
 * Created by Oleg on 15.01.2017.
 */
public class RemoveClientCommand extends AbstractCommand implements Command {

    private ClientService clientService;

    public RemoveClientCommand(ClientService clientService, IO io) {
        super(io);
        this.clientService = clientService;
    }

    @Override
    public void execute() throws IOException {
        String delClient = read();
        Client client = null;
        try {
            client = clientService.findClientByName(delClient);
            clientService.deleteClient(client);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }

        write(client.getName() + " deleted");
    }

    @Override
    public void printCommandInfo() {
        write("9. RemoveClientCommand");
    }
}