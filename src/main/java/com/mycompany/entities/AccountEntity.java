/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.libraryhustmanagerment.DuplicateEntryException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;

/**
 *
 * @author Admin
 */

public class AccountEntity extends BaseEntity {
    public static void InsertAccount(Account newAccount) throws DuplicateEntryException {
        open();

        try {
            String countSql = "SELECT COUNT(*) FROM Account";
            statement = conn.prepareStatement(countSql);
            ResultSet countResultSet = statement.executeQuery();
            countResultSet.next();
            int accountCount = countResultSet.getInt(1);

            String role = (accountCount == 0) ? "Librarian" : "Student";

            String checkSql = "SELECT COUNT(*) FROM Account WHERE emailAddress = ? OR phoneNumber = ?";
            statement = conn.prepareStatement(checkSql);
            statement.setString(1, newAccount.GetEmailAddress());
            statement.setString(2, newAccount.GetPhoneNumber());
            ResultSet checkResultSet = statement.executeQuery();
            checkResultSet.next();
            int existingCount = checkResultSet.getInt(1);

            if (existingCount > 0) {
                Logger.getLogger(AccountEntity.class.getName()).log(Level.SEVERE,
                        "Email or phone number already exists.");
                throw new DuplicateEntryException("Email or phone number already exists.");
            }

            String sql = "INSERT INTO Account(accountId, name, emailAddress, phoneNumber, password, role) VALUES (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, newAccount.GetAccountId());
            statement.setString(2, newAccount.GetName());
            statement.setString(3, newAccount.GetEmailAddress());
            statement.setString(4, newAccount.GetPhoneNumber());
            statement.setString(5, newAccount.GetPassword());
            statement.setString(6, role);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AccountEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void UpdateAccount(Account updateAccount) throws SQLException {
        open();

        String sql = "UPDATE Account SET name = ?, emailAddress = ?, phoneNumber = ?, password = ? WHERE accountId = ?";

        try {
            statement = conn.prepareStatement(sql);

            statement.setString(1, updateAccount.GetName());
            statement.setString(2, updateAccount.GetEmailAddress());
            statement.setString(3, updateAccount.GetPhoneNumber());
            statement.setString(4, updateAccount.GetPassword());
            statement.setInt(5, updateAccount.GetAccountId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                Logger.getLogger(AccountEntity.class.getName()).log(Level.WARNING,
                        "No account found with id: " + updateAccount.GetAccountId());
            }
        } finally {
            close();
        }
    }

    public static void DeleteAccount(Integer accountId) throws SQLException {
        open();

        String sql = "DELETE FROM Account WHERE accountId = ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, accountId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                Logger.getLogger(AccountEntity.class.getName()).log(Level.WARNING,
                        "No account found with id: " + accountId);
            }
        } finally {
            close();
        }
    }

    public static Account GetDataAccountById(Integer accountId) {
        Account account = null;
        open();

        String sql = "SELECT accountId, name, emailAddress, phoneNumber, password, role FROM Account WHERE accountId = ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, accountId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                account = new Account(
                        resultSet.getInt("accountId"),
                        resultSet.getString("name"),
                        resultSet.getString("emailAddress"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return account;
    }

    public static List<Account> ShowAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        open();

        String sql = "SELECT * FROM Account";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer accountId = rs.getInt("accountId");
                String name = rs.getString("name");
                String emailAddress = rs.getString("emailAddress");
                String phoneNumber = rs.getString("phoneNumber");
                String password = rs.getString("password");
                String role = rs.getString("role");

                Account account = new Account(accountId, name, emailAddress, phoneNumber, password, role);
                accounts.add(account);
            }

        } catch (SQLException e) {
            Logger.getLogger(AccountEntity.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            close();
        }

        return accounts;
    }

    // Fetch all accounts
    public static ObservableList<Account> GetAllAccounts() throws SQLException {
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        open();
        try {
            String sql = "SELECT * FROM Account";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("accountId"),
                        resultSet.getString("name"),
                        resultSet.getString("emailAddress"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                accounts.add(account);
            }
        } finally {
            close();
        }
        return accounts;
    }
}