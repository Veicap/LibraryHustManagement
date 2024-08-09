package com.mycompany.libraryhustmanagerment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.entities.AccountEntity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Account;

/**
 * FXML Document Controller
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane signIn_form;

    @FXML
    private Button signin_minimize;

    @FXML
    private Button signin_close;

    @FXML
    private TextField signin_accountId;

    @FXML
    private PasswordField signin_password;

    @FXML
    private Button signinbtn;

    @FXML
    private Hyperlink signin_hyperlink;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_minimize;

    @FXML
    private Button signup_close;

    @FXML
    private TextField signup_accountId;

    @FXML
    private TextField signup_name;

    @FXML
    private TextField signup_phoneNumber;

    @FXML
    private TextField signup_emailAddress;

    @FXML
    private PasswordField signup_password;

    @FXML
    private Button signup_btn;

    @FXML
    private Hyperlink signup_hyperlink;

    @FXML
    private Label title;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setText("SIGN UP");
        signIn_form.setVisible(true);
        signup_form.setVisible(false);
        setupWindowDragging();
    }

    @FXML
    private void switchForm(ActionEvent event) throws IOException {
        if (event.getSource() == signin_hyperlink) {
            signIn_form.setVisible(false);
            signup_form.setVisible(true);
            clearSignUpField();
        } else if (event.getSource() == signup_hyperlink) {
            signIn_form.setVisible(true);
            signup_form.setVisible(false);
            clearSignInField();
        }
    }

    @FXML
    private void signIn_close() {
        System.exit(0);
    }

    @FXML
    private void signIn_minimize() {
        Stage stage = (Stage) signIn_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void signUp_close() {
        System.exit(0);
    }

    @FXML
    private void signUp_minimize() {
        Stage stage = (Stage) signup_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private double x = 0;
    private double y = 0;

    private void setupWindowDragging() {
        rootPane.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        rootPane.setOnMouseDragged((MouseEvent event) -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    private void switchToDashBoard(Account userLogin) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = loader.load();

        FXMLDashBoardController dashboardController = loader.getController();

        dashboardController.DisplayAccountIdRoleAndName(userLogin);
        dashboardController.initializeDataByStudent();
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            // stage.setOpacity(0.8);
        });

        // root.setOnMouseReleased((MouseEvent event) -> {
        // stage.setOpacity(1);
        // });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        signinbtn.getScene().getWindow().hide();
        
    }

    private void errorLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Cannot Login!");
        alert.setContentText("Account ID and password are incorrect, please check again!");
        alert.showAndWait();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Account userLogin = null;
    private static Account accountLogin = null;
    public static void SetAccountLogin(Account userLogin) {
        accountLogin = userLogin;
    }
    public static Account GetAccountLogin() {
        return accountLogin;
    }
    @FXML
    private void signIn_and_signUp() throws IOException {
        try {
            if (signIn_form.isVisible()) {
                handleSignIn();
            } else if (signup_form.isVisible()) {
                handleSignUp();
            }
        } catch (NumberFormatException ex) {
            showAlert("Error!", "Cannot Register!",
                    "Account ID must be HUST ID, please try again!");
        }
    }
    
    private void handleSignIn() throws IOException {
        String accountIdText = signin_accountId.getText();
        String passwordText = signin_password.getText();

        if (accountIdText.isEmpty() || passwordText.isEmpty()) {
            errorLogin();
            return;
        }

        Integer accountId = Integer.valueOf(accountIdText);
        Account userLogin = AccountEntity.GetDataAccountById(accountId);

        if (userLogin != null && userLogin.GetPassword().equals(passwordText)) {
            showAlert("Successfully!", "Log In Successfully!",
                    "Account " + userLogin.GetAccountId() + " has been logged in!");
            SetAccountLogin(userLogin);
            switchToDashBoard(userLogin);
            
        } else {
            errorLogin();
        }
        
    }
    
    private void handleSignUp() {
        String name = signup_name.getText();
        String phoneNumber = signup_phoneNumber.getText();
        String emailAddress = signup_emailAddress.getText();
        String accountIdText = signup_accountId.getText();
        String password = signup_password.getText();

        if (accountIdText.isEmpty() || !accountIdText.matches("\\d+")) {
            showAlert("Error!", "Invalid Account ID!", "Account ID must be HUST ID, please try again!");
            return;
        }

        Integer accountId = Integer.valueOf(accountIdText);

        if (!Account.validateAccountId(accountIdText)) {
            showAlert("Error!", "Invalid Account ID!",
                    "Account ID must be an 8-digit number starting from 2016 to 2024, please try again!");
            return;
        }

        if (!Account.validatePhoneNumber(phoneNumber)) {
            showAlert("Error!", "Invalid Phone Number!",
                    "Phone number must be 10 digits long, starting with 0 and followed by 9 digits (e.g., 0912345678), please try again!");
            return;
        }

        if (!Account.validateEmailAddress(emailAddress)) {
            showAlert("Error!", "Invalid Email Address!",
                    "Invalid email address. Please ensure the email address ends with @sis.hust.edu.vn or @hust.edu.vn.");
            return;
        }

        if (AccountEntity.GetDataAccountById(accountId) != null) {
            showAlert("Error!", "Cannot Register!", "Account ID already exists, please choose another one!");
            return;
        }

        if (!Account.validatePasswordHash(password)) {
            showAlert("Error!", "Weak Password!",
                    "Password must contain digits, lowercase, uppercase, special symbols (@#$%), and be 6 to 15 characters long. Please try again!");
            return;
        }

        Account account = new Account(accountId, name, emailAddress, phoneNumber, password, "Student");

        try {
            AccountEntity.InsertAccount(account);
            signIn_form.setVisible(true);
            signup_form.setVisible(false);
            showAlert("Successfully!", "Registration Successful!",
                    "Account " + account.GetAccountId() + " has been registered!");
            clearSignUpField();
        } catch (DuplicateEntryException e) {
            showAlert("Error!", "Cannot Register!", e.getMessage());
        }
    }

    private void clearSignInField() {
        signin_accountId.clear();
        signin_password.clear();
    }

    private void clearSignUpField() {
        signup_accountId.clear();
        signup_name.clear();
        signup_phoneNumber.clear();
        signup_emailAddress.clear();
        signup_password.clear();
    }
}