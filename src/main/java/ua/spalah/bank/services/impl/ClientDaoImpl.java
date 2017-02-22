package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.Gender;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.services.ClientDao;
import ua.spalah.bank.ui.BankCommander;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10.02.2017.
 */

public class ClientDaoImpl implements ClientDao {

    private Connection connection = BankCommander.con();
    private AccountDao accountDao;

    public ClientDaoImpl(AccountDao accountDao) {
        this.accountDao = accountDao;

    }

    private Client createClient(ResultSet rs) {
        Client client = null;
        try {
            client = new Client(rs.getString(2),
                    rs.getString("GENDER").equals(Gender.MALE.toString()) ? Gender.MALE : Gender.FAMALE,
                    rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getLong(1));

            long activeAccId = rs.getLong("ACTIVEACC_ID");
            List<Account> accounts = accountDao.findByClientId(rs.getLong("CLIENT_ID"));
            client.setListOfAcc(accounts);

            for (Account account : accounts) {
                if (account.getAccId() == activeAccId) {
                    client.setActiveAcc(account);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }


    @Override
    public Client save(Client client) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CLIENT(NAME ,GENDER,EMAIL,PHONE,CITY,ACTIVEACC_ID)" +
                    " VALUES (?,?,?,?,?,?);COMMIT;");
            ps.setString(1, client.getName());
            ps.setString(2, client.getGender().toString());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getCity());
            ps.setLong(6, client.getClientId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client update(Client client) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE CLIENT " +
                    "SET NAME = ?, " +
                    "GENDER = ?, " +
                    "EMAIL = ?, " +
                    "PHONE = ?, " +
                    "CITY = ?, " +
                    "ACTIVEACC_ID = ? " +
                    "WHERE CLIENT_ID = ?");
            ps.setString(1, client.getName());
            ps.setString(2, client.getGender().toString());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getCity());
            ps.setLong(6, client.getActiveAccId());
            ps.setLong(7, client.getClientId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client saveOrUpdate(Client client) {
        boolean flag = false;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CLIENT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getLong(1) == client.getClientId()) {
                    update(client);
                    flag = true;
                }
            }
            if (flag == false) {
                save(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void delete(long clientId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ACCOUNTS WHERE CLIENT_ID = ?");
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM CLIENT WHERE CLIENT_ID = ?");
            ps.setLong(1, clientId);
            ps1.setLong(1, clientId);
            ps.executeUpdate();
            ps1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client find(long id) {
        Client client = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CLIENT WHERE CLIENT_ID = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            client = createClient(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        List<Client> lstClient = new ArrayList<>();
        Client client = null;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CLIENT");

            while (rs.next()) {
                client = createClient(rs);
                lstClient.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lstClient;
    }

    @Override
    public Client findByName(String name) {
        Client client = null;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * " +
                    "FROM CLIENT as t1\n" +
                    "JOIN ACCOUNTS as t2 ON t2.CLIENT_ID = t1.CLIENT_ID\n" +
                    "WHERE NAME = ?");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            rs.next();
            client = createClient(rs);
        } catch (SQLException | NullPointerException e) {
            System.err.println("Client not found");
        }
        return client;
    }
}