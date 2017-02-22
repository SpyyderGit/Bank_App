import org.junit.Before;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.ClientDao;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountDaoImpl;
import ua.spalah.bank.services.impl.ClientDaoImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerald on 22.02.2017.
 */
public class TestClientsDao {

    private AccountDao accountDao = new AccountDaoImpl();
    private ClientDao clientDao = new ClientDaoImpl(accountDao);
    private ClientService clientService = new ClientServiceImpl(clientDao, accountDao);
    private List<Client> clientList = new ArrayList<>();

    @Before
    void init() {
        for (Client c : clientService.findAllClients()) {
            clientList.add(c);
        }
        System.out.println(clientList);
    }


}
