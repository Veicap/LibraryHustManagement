package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Account {
    private Integer accountId;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String role;

    public Account(Integer accountId, String name, String emailAddress, String phoneNumber, String password,
            String role) {
        this.accountId = accountId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public Account(Integer accountId, String name, String emailAddress, String phoneNumber, String password) {
        this.accountId = accountId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Account() {
    }

    public Integer GetAccountId() {
        return accountId;
    }

    public void SetAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetEmailAddress() {
        return emailAddress;
    }

    public void SetEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String GetPhoneNumber() {
        return phoneNumber;
    }

    public void SetPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String GetPassword() {
        return password;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public String GetRole() {
        return role;
    }

    public void SetRole(String role) {
        this.role = role;
    }

    public static Boolean validateAccountId(String accountId) {
        Pattern pattern = Pattern.compile("^(201[6-9]|202[0-4])\\d{4}$");
        Matcher matcher = pattern.matcher(accountId);
        return matcher.matches();
    }

    public static Boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^0(3[0-9]|5[0-9]|7[0-9]|8[0-9]|9[0-9])[0-9]{7}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static Boolean validateEmailAddress(String emailAddress) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@(sis\\.)?hust\\.edu\\.vn$");
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    public static Boolean validatePasswordHash(String passwordHash) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{6,15}$");
        Matcher matcher = pattern.matcher(passwordHash);
        return matcher.matches();
    }
}