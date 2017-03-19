package ua.spalah.bank.models;

import ua.spalah.bank.models.accounts.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerald_PC on 08.01.2017.
 */


public class Client {
    private String name;
    private Account activeAcc;
    private Gender gender;
    private String email;
    private String phone;
    private String city;
    private long clientId;
    private long activeAccId;

    private List<Account> listOfAcc = new ArrayList<>();

    public Client(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public Client(String name, Gender gender, String email, String phone, String city) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }


    public Client(String name, Gender gender, String email, String phone, String city, long clientId) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.clientId = clientId;
    }


    public Client(String name, Gender gender, String email, String phone, String city, long clientId, long activeAccId) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.clientId = clientId;
        this.activeAccId = activeAccId;
    }



    public String getName() {
        return name;
    }


    public void setActiveAcc(Account activeAcc) {

        this.activeAcc = activeAcc;
    }

    public void setListOfAcc(List<Account> listOfAcc) {
        this.listOfAcc = listOfAcc;
    }

    public Account getActiveAcc() {
        return activeAcc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Account> getListOfAcc() {

        return listOfAcc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getActiveAccId() {
        return activeAccId;
    }

    public void setActiveAccId(long activeAccId) {
        this.activeAccId = activeAccId;
    }

    @Override
    public String toString() {
        return "Client{" + "\nclientId=" + clientId +
                "\nname='" + name + '\'' +
                "\nactiveAcc='" + activeAcc + '\'' +
                "\ngender=" + gender +
                "\nlistOfAcc=" + listOfAcc +
                "\nphone=" + phone +
                "\nemail=" + email +
                "\ncity=" + city
                + "\n==========================================================\n";
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (activeAcc != null ? !activeAcc.equals(client.activeAcc) : client.activeAcc != null) return false;
        if (gender != client.gender) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (city != null ? !city.equals(client.city) : client.city != null) return false;
        return listOfAcc != null ? listOfAcc.equals(client.listOfAcc) : client.listOfAcc == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (activeAcc != null ? activeAcc.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (listOfAcc != null ? listOfAcc.hashCode() : 0);
        return result;
    }
}