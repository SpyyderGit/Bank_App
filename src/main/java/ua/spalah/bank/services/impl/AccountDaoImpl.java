package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.models.Client;
import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.AccountType;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.AccountDao;
import ua.spalah.bank.ui.BankCommander;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 10.02.2017.
 */
public class AccountDaoImpl implements AccountDao {

    private Connection connection = BankCommander.con();


    private List<Account> createAccount(ResultSet resultSet) {
        List<Account> lstAcc = new ArrayList<>();
        Account account = null;
        try {
            while (resultSet.next()) {
                if (resultSet.getString("ACCOUNT_TYPE").equals("SAVING")) {
                    account = new SavingAccount(resultSet.getDouble("BALANCE"));
                    lstAcc.add(account);
                    account.setAccId(resultSet.getLong(1));
                } else {
                    account = new CheckingAccount(resultSet.getDouble("BALANCE"), resultSet.getDouble("OVERDRAFT"));
                    lstAcc.add(account);
                    account.setAccId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (OverdraftLimitExceededException e) {
            e.printStackTrace();
        }
        return lstAcc;
    }

    @Override
    public Account save(long clientId, Account account) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ACCOUNTS(BALANCE,OVERDRAFT,ACCOUNT_TYPE,CLIENT_ID)" +
                    "VALUES (?,?,?,?);COMMIT;");

            ps.setDouble(1, account.getBalance());
            if (account.getType().equals(AccountType.CHECKING)) {
                CheckingAccount ca = (CheckingAccount) account;
                ps.setDouble(2, ca.getOverdraft());
            } else {
                ps.setDouble(2, 0);
            }
            ps.setString(3, account.getType().toString());
            ps.setLong(4, clientId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account update(long accId, Account account) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ACCOUNTS " +
                    "SET BALANCE = ?, " +
                    "OVERDRAFT = ?," +
                    "ACCOUNT_TYPE = ? " +
                    "WHERE ACCOUNT_ID = ?");
            ps.setDouble(1, account.getBalance());
            if (account.getType().equals(AccountType.CHECKING)) {
                CheckingAccount ca = (CheckingAccount) account;
                ps.setDouble(2, ca.getOverdraft());
            } else {
                ps.setDouble(2, 0);
            }
            ps.setString(3, account.getType().toString());
            ps.setLong(4, accId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }


    @Override
    public Account saveOrUpdate(long accId, long clientId, Account account) {
        boolean flag = false;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ACCOUNTS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getLong(1) == accId) {
                    update(accId, account);
                    flag = true;
                }
            }
            if (flag == false) {
                save(clientId, account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ACCOUNTS WHERE ACCOUNT_ID = ?");
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account find(long id) {
        Account acc = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("ACCOUNT_TYPE").equals("SAVING")) {
                    acc = new SavingAccount(rs.getDouble("BALANCE"));
                    acc.setAccId(rs.getLong(1));
                } else {
                    acc = new CheckingAccount(rs.getDouble("BALANCE"), rs.getDouble("OVERDRAFT"));
                    acc.setAccId(rs.getLong(1));
                }
            }
        } catch (SQLException | OverdraftLimitExceededException e) {
            e.printStackTrace();
        }
        return acc;
    }

    @Override
    public List<Account> findAll() {
        ResultSet rs = null;

        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM ACCOUNTS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createAccount(rs);
    }

    @Override
    public List<Account> findByClientId(long clientId) {
        ResultSet rs = null;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE CLIENT_ID = ?");
            st.setLong(1, clientId);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createAccount(rs);
    }

    @Override
    public Account findActiveAccountByClientName(String clientName) {

        return null;
    }

    public int getMaxId() {
        ResultSet rs = null;
        int max = 0;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(ACCOUNT_ID)FROM ACCOUNTS");
            rs = st.executeQuery();

            while (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max;
    }

}