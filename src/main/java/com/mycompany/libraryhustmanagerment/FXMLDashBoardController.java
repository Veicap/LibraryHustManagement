/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.libraryhustmanagerment;


import com.mycompany.entities.BookEntity;
import com.mycompany.entities.BorrowBookEntity;
import com.mycompany.entities.CatalogEntity;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;

import com.mycompany.entities.AccountEntity;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Account;
import models.Book;
import models.BorrowBook;

/**
 * FXML Controller class
 *
 * @author Legion
 */
public class FXMLDashBoardController {
    @FXML
    private TableView<Account> account_accountTableView;

    @FXML
    private TableColumn<Account, Integer> account_accountIdColumn;

    @FXML
    private TableColumn<Account, String> account_roleColumn;

    @FXML
    private TableColumn<Account, String> account_nameColumn;

    @FXML
    private TableColumn<Account, String> account_emailAddressColumn;

    @FXML
    private TableColumn<Account, String> account_phoneNumberColumn;

    @FXML
    private TableColumn<Account, String> account_passwordColumn;

    @FXML
    private AnchorPane account_form;

    @FXML
    private Button accountBtn;

    @FXML
    private Button account_addBtn;

    @FXML
    private Button account_deleteBtn;

    @FXML
    private Button account_updateButton;

    @FXML
    private Button account_ResetBtn;

    @FXML
    private TextField account_accountID;

    @FXML
    private TextField account_name;

    @FXML
    private TextField account_emailAddress;

    @FXML
    private TextField account_phoneNumber;

    @FXML
    private TextField account_password;

    @FXML
    private TextField account_searchByUserID;

    @FXML
    private Button account_selectAccount;

    @FXML
    private Button borrowedBook_returnBook;

    @FXML
    private Button borrowedBook_selectBook;

    @FXML
    private ComboBox<String> borrowedBooks_bookIDSearch;

    @FXML
    private TableColumn<BorrowBook, Integer> borrowedBooks_bookIDTable;

    @FXML
    private TableColumn<BorrowBook, String> borrowedBooks_bookTitleTable;

    @FXML
    private TableColumn<?, ?> borrowedBooks_borrowDateTable;

    @FXML
    private ComboBox<String> borrowedBooks_borrowIDSearch;

    @FXML
    private TableColumn<BorrowBook, Integer> borrowedBooks_borrowIDTable;

    @FXML
    private Button borrowedBooks_btn;

    @FXML
    private Button borrowedBook_searchBtn;

    @FXML
    private Button borrowedBook_showBorrowedBookBtn;
    
    @FXML
    private Button borrowedBook_studentDetailBtn;

    @FXML
    private TableColumn<?, ?> borrowedBooks_dueDateTable;

    @FXML
    private AnchorPane borrowedBooks_form;

    @FXML
    private TableColumn<?, ?> borrowedBooks_returnDateTable;

    @FXML
    private ComboBox<String> borrowedBooks_studentIDSearch;

    @FXML
    private TableColumn<BorrowBook, Integer> borrowedBooks_studentIDTable;

    @FXML
    private TableView<BorrowBook> borrowedBooks_tableView;

    @FXML
    private Button close_btn;

    @FXML
    private AnchorPane dasboard_form;

    @FXML
    private Label login_role;

    @FXML
    private Label login_accountId;

    @FXML
    private Label login_name;

    // @FXML
    // private AnchorPane main_form;

    @FXML
    private Button managerBook_SearchBtn;

    @FXML
    private Button managerBook_addBtn;

    @FXML
    private TextField managerBook_author;

    @FXML
    private TableColumn<Book, String> managerBook_authorTable;

    @FXML
    private ComboBox<String> managerBook_authorsCombobox;

    @FXML
    private TableColumn<Book, Integer> managerBook_availableBookTable;

    @FXML
    private AnchorPane managerBook_bookBorrowUI;

    @FXML
    private Label managerBook_bookBorrowedID;

    @FXML
    private TableColumn<Book, Integer> managerBook_bookID;

    @FXML
    private TextField managerBook_bookTitle;

    @FXML
    private ComboBox<String> managerBook_bookTitleSearch;

    @FXML
    private TextField managerBook_studentID;

    @FXML
    private TableColumn<Book, String> managerBook_bookTitleTable;

    @FXML
    private Button managerBook_borrowBookBtn;
    

    @FXML
    private Button managerBook_borrowBookBtnComplete;

    @FXML
    private Button managerBook_btn;

    @FXML
    private DatePicker managerBook_date;

    @FXML
    private Button managerBook_deleteBtn;

    @FXML
    private AnchorPane managerBook_form;

    @FXML
    private TextField managerBook_genre;

    @FXML
    private ComboBox<String> managerBook_genreCombobox;

    @FXML
    private TableColumn<?, ?> managerBook_genreTable;

    @FXML
    private TableColumn<?, ?> managerBook_publicationDate;

    @FXML
    private TextField managerBook_publisherField;

    @FXML
    private TableColumn<Book, String> managerBook_publisherTable;

    @FXML
    private Button managerBook_resetBtn;

    @FXML
    private Button managerBook_selectBookBtn;

    @FXML
    private Button managerBook_showAllBookBtn;

    @FXML
    private TextField managerBook_stock;

    @FXML
    private TableView<Book> managerBook_tableView;

    @FXML
    private TableColumn<Book, Integer> managerBook_totalBookTable;

    @FXML
    private Button managerBook_updateBtn;

    @FXML
    private Button minimize_btn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signout_btn;

    @FXML
    private TableView<Account> account_TableView;

    @FXML
    private void dasboard_form_close() {
        System.exit(0);
    }

    @FXML
    private void dasboard_form_minimize() {
        Stage stage = (Stage) dasboard_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void switchForm(ActionEvent event) throws IOException {
        if (event.getSource() == managerBook_btn) {
            managerBook_form.setVisible(true);
            borrowedBooks_form.setVisible(false);
            account_form.setVisible(false);

        } else if (event.getSource() == borrowedBooks_btn) {
            managerBook_form.setVisible(false);
            borrowedBooks_form.setVisible(true);
            account_form.setVisible(false);
        } else if (event.getSource() == accountBtn) {
            managerBook_form.setVisible(false);
            borrowedBooks_form.setVisible(false);
            account_form.setVisible(true);
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // accountLogin
    
    public void initializeDataByStudent() {
        try {
            SetValueMangagetBookAll();
            SetValueBorrowBookAll();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SetValueForBookTitlesCatalog();
        SetValueForBookGenreCatalog();
        SetValueForBookAuthorCatalog();
        account_accountIdColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().GetAccountId()).asObject());
        account_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetName()));
        account_emailAddressColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetEmailAddress()));
        account_phoneNumberColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetPhoneNumber()));
        account_passwordColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetPassword()));
        account_roleColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetRole()));
        ShowAllAccountsInTable();
        // UPdateCatalog borrowBook
        SetValueForBorrowIDSearch();
        SetValueForBookIDSearch();
        SetValueForAccountIDSearch();
    }
    private Boolean checkStringNotNULL(String nameOfObject, TextField textField) {
        try {
            if (!textField.getText().equals("")) {
                return true;
            } else {
                showAlert("Error!", "Add Book Failure!", " not NULL, please try again!");
                return false;
            }
        } catch (NumberFormatException ex) {
            showAlert("Error!", "Add Book Failure!", nameOfObject + " not NULL, please try again!");
            return false;
        }
    }
    //! Function AddBook
    @FXML
    private void AddBook() {
        String bookTitle = null;
        // Check string is null or not
        if (checkStringNotNULL("Book Title", managerBook_bookTitle)) {
            bookTitle = managerBook_bookTitle.getText(); // if not will setvalue
        }
        String bookAuthor = null;
        if (checkStringNotNULL("Book Author", managerBook_author)) {
            bookAuthor = managerBook_author.getText();
        }
        Integer stock = null;
        try {
            if (Book.IsValidStock(managerBook_stock.getText())) {
                stock = Integer.valueOf(managerBook_stock.getText());
            } else {
                showAlert("Error!", "Add Book Failure!",
                        "Stock must be a positive point number, please try again!");
                return;
            }
        } catch (NumberFormatException ex) {
            showAlert("Error!", "Add Book Failure!", "Stock must be a positive point number, please try again!");
            return;
        }
        String genre = null;
        if (checkStringNotNULL("Genre", managerBook_genre)) {
            genre = managerBook_genre.getText();
        }
        String publisher = null;
        if (checkStringNotNULL("Publisher", managerBook_publisherField)) {
            publisher = managerBook_publisherField.getText();
        }

        LocalDate selectedDate = managerBook_date.getValue();
        Date publicationDate = null;
        if (selectedDate != null) {

            publicationDate = Date.valueOf(selectedDate);

        } else {
            showAlert("Error", null, "Please choose a publication date.");
        }

        Book newBook = new Book(bookTitle, genre, bookAuthor, stock, stock, publisher, publicationDate);
        if (BookEntity.IsExisted(newBook)) {
            showAlert("Error", null, "Book is exited.");
            return;
        }
        BookEntity.AddBook(newBook);
        showAlert("Success!", "Book is added successfully!", "Book added is: " + newBook.getBookTitle());
        managerBook_bookTitle.clear();
        managerBook_author.clear();
        managerBook_stock.clear();
        managerBook_genre.clear();
        managerBook_publisherField.clear();
        managerBook_date.setValue(null);
        try {
            SetValueMangagetBookAll();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Update Catalog
        SetValueForBookTitlesCatalog();
        SetValueForBookGenreCatalog();
        SetValueForBookAuthorCatalog();
        // Update Catalog
        SetValueForBookTitlesCatalog();
        SetValueForBookGenreCatalog();
        SetValueForBookAuthorCatalog();
    }
    // Set value for comboBox
    private void SetValueForComboBox(ComboBox<String> comboBox, List<String> catalogList, String model) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("None");
        for (int i = 0; i < catalogList.size(); i++) {
            comboBox.getItems().addAll(catalogList.get(i));
        }
        comboBox.getSelectionModel().select(model);
    }

    private void SetValueForBookTitlesCatalog() {
        List<String> titleCatalogList = CatalogEntity.GetBookTitleList();
        SetValueForComboBox(managerBook_bookTitleSearch, titleCatalogList, "Book Titles");
    }

    private void SetValueForBookGenreCatalog() {
        List<String> genreCatalogList = CatalogEntity.GetGenreList();
        SetValueForComboBox(managerBook_genreCombobox, genreCatalogList, "Genres");
    }

    private void SetValueForBookAuthorCatalog() {
        List<String> authorCatalogList = CatalogEntity.GetAuhorList();
        SetValueForComboBox(managerBook_authorsCombobox, authorCatalogList, "Authors");
    }

    @FXML
    private void SetValueManagerBookTableViewByCatalog() throws SQLException {
        String titleCatalog = managerBook_bookTitleSearch.getValue();
        String genreCatalog = managerBook_genreCombobox.getValue();
        String authorCatalog = managerBook_authorsCombobox.getValue();
        if ((titleCatalog.equals("Book Titles") || titleCatalog.equals("None")) &&
                (genreCatalog.equals("Genres") || genreCatalog.equals("None")) &&
                (authorCatalog.equals("Authors") || authorCatalog.equals("None"))) {
            SetValueMangagetBookAll();
        } else if ((!titleCatalog.equals("Book Titles") && !titleCatalog.equals("None")) &&
                (genreCatalog.equals("Genres") || genreCatalog.equals("None")) &&
                (authorCatalog.equals("Authors") || authorCatalog.equals("None"))) {
            ObservableList<Book> dataBookByTitle = BookEntity.GetDataBookByTitle(titleCatalog);
            setValueForManagerBookTableView(dataBookByTitle);

        } else if ((!titleCatalog.equals("Book Titles") && !titleCatalog.equals("None")) &&
                (!genreCatalog.equals("Genres") && !genreCatalog.equals("None")) &&
                (authorCatalog.equals("Authors") || authorCatalog.equals("None"))) {
            ObservableList<Book> dataBookByTitleAndGenre = BookEntity.GetDataBookByTitleAndGenre(titleCatalog,
                    genreCatalog);
            setValueForManagerBookTableView(dataBookByTitleAndGenre);

        } else if ((!titleCatalog.equals("Book Titles") && !titleCatalog.equals("None")) &&
                (genreCatalog.equals("Genres") || genreCatalog.equals("None")) &&
                (!authorCatalog.equals("Authors") && !authorCatalog.equals("None"))) {
            ObservableList<Book> dataBookByTitleAndAuthor = BookEntity.GetDataBookByTitleAndAuthor(titleCatalog,
                    authorCatalog);
            setValueForManagerBookTableView(dataBookByTitleAndAuthor);

        } else if ((!titleCatalog.equals("Book Titles") && !titleCatalog.equals("None")) &&
                (!genreCatalog.equals("Genres") && !genreCatalog.equals("None")) &&
                (!authorCatalog.equals("Authors") && !authorCatalog.equals("None"))) {
            ObservableList<Book> dataBookByTitleAndGenreAndAuthor = BookEntity
                    .GetDataBookByTitleAndGenreAndAuthor(titleCatalog, genreCatalog, authorCatalog);
            setValueForManagerBookTableView(dataBookByTitleAndGenreAndAuthor);

        } else if ((titleCatalog.equals("Book Titles") || titleCatalog.equals("None")) &&
                (!genreCatalog.equals("Genres") && !genreCatalog.equals("None")) &&
                (!authorCatalog.equals("Authors") && !authorCatalog.equals("None"))) {
            ObservableList<Book> dataBookByGenreAndAuthor = BookEntity.GetDataBookByGenreAndAuthor(genreCatalog,
                    authorCatalog);
            setValueForManagerBookTableView(dataBookByGenreAndAuthor);

        } else if ((titleCatalog.equals("Book Titles") || titleCatalog.equals("None")) &&
                (!genreCatalog.equals("Genres") && !genreCatalog.equals("None")) &&
                (authorCatalog.equals("Authors") || authorCatalog.equals("None"))) {
            ObservableList<Book> dataBookByGenre = BookEntity.GetDataBookByGenre(genreCatalog);
            setValueForManagerBookTableView(dataBookByGenre);

        } else if ((titleCatalog.equals("Book Titles") || titleCatalog.equals("None")) &&
                (genreCatalog.equals("Genres") || genreCatalog.equals("None")) &&
                (!authorCatalog.equals("Authors") && !authorCatalog.equals("None"))) {
            ObservableList<Book> dataBookByAuthor = BookEntity.GetDataBookByAuthor(authorCatalog);
            setValueForManagerBookTableView(dataBookByAuthor);

        }
    }

    @FXML
    void SetValueMangagetBookAll() throws SQLException {
        ObservableList<Book> bookDataList = BookEntity.GetDataBooks();
        setValueForManagerBookTableView(bookDataList);
       // System.out.println(GetAccountLogin().GetRole());
    }

    private void setValueForManagerBookTableView(ObservableList<Book> bookDataList) {
        // setvalue for table
        managerBook_bookTitleTable.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        managerBook_genreTable.setCellValueFactory(new PropertyValueFactory<>("genre"));
        managerBook_bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        managerBook_authorTable.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        managerBook_publisherTable.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        managerBook_publicationDate.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        managerBook_totalBookTable.setCellValueFactory(new PropertyValueFactory<>("totalBook"));
        managerBook_availableBookTable.setCellValueFactory(new PropertyValueFactory<>("availBook"));
        managerBook_tableView.setItems(bookDataList);
    }
    private Book selectedBook = null;
    @FXML
    private void SelectBook() {
        selectedBook = managerBook_tableView.getSelectionModel().getSelectedItem();
        showAlert("Success!", "Selected Book successfully!", "Book is selected: " + selectedBook.getBookTitle());
        SetValueForUpdateForm();
        System.out.print(selectedBook.getBookID());

    }

    private void SetValueForUpdateForm() {
        if (selectedBook != null) {
            managerBook_bookTitle.setText(selectedBook.getBookTitle());
            managerBook_author.setText(selectedBook.getBookAuthor());
            managerBook_stock.setText(selectedBook.getTotalBook().toString());
            managerBook_genre.setText(selectedBook.getGenre());
            managerBook_publisherField.setText(selectedBook.getPublisher());
            Date sqlDate = (Date) selectedBook.getPublicationDate();
            LocalDate localDate = sqlDate.toLocalDate();
            managerBook_date.setValue(localDate);
        }
    }
    @FXML
    private void ResetForm() {
        managerBook_bookTitle.clear();
        managerBook_author.clear();
        managerBook_stock.clear();
        managerBook_genre.clear();
        managerBook_publisherField.clear();
        managerBook_date.setValue(null);
        selectedBook = null;
    }

    @FXML
    private void UpdateBook() {
        if (selectedBook != null) {
            if(BorrowBookEntity.IsExistedInBorrowBook(selectedBook.getBookID())) {
                showAlert("Error", "Update Error", "Book are being borrowed ");
                System.out.println("True");
                return;
            }
            System.out.println("False");
            try {
                String title = managerBook_bookTitle.getText();
                String author = managerBook_author.getText();
                Integer totalBook = Integer.valueOf(managerBook_stock.getText());
                
                String genre = managerBook_genre.getText();
                String publisher = managerBook_publisherField.getText();
                LocalDate pubLocalDate = managerBook_date.getValue();
                Date sqlDate = Date.valueOf(pubLocalDate);

                Book updateBook = new Book(title, genre, author, totalBook, publisher, sqlDate);

                BookEntity.UpdateBook(updateBook, selectedBook);
                System.out.println("Book ID: " + selectedBook.getBookID());

                SetValueMangagetBookAll();

                showAlert("Success!", "Update Book successfully!",
                        "Book is updated: " + selectedBook.getBookTitle());
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid Input", "Please enter valid numbers for stock.");
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, "Database update error",
                        ex);
                showAlert("Error", "Database Error", "Failed to update book in the database.");
            }
        } else {
            System.out.println("Selected book is null");
        }
        // Update Catalog
        SetValueForBookTitlesCatalog();
        SetValueForBookGenreCatalog();
        SetValueForBookAuthorCatalog();
    }

    @FXML
    private void DeleteBook() {
        if (selectedBook != null) {
            if(BorrowBookEntity.IsExistedInBorrowBook(selectedBook.getBookID())) {
                showAlert("Error", "Delete Error", "Book are being borrowed ");
                return;
            }
            int deletedBookID = selectedBook.getBookID();
            BookEntity.DeleteBook(deletedBookID);
            showAlert("Success!", "Delete Book successfully!", "Book is deleted: " + selectedBook.getBookTitle());
            try {
                SetValueMangagetBookAll();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SetValueForBookTitlesCatalog();
        SetValueForBookGenreCatalog();
        SetValueForBookAuthorCatalog();
    }

    /*
     *
     * //! Account Function
     *
     */

    private Account account;

    private boolean showConfirmationAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void DisplayAccountIdRoleAndName(Account account) {
        this.account = account;
        UpdateUI();
    }

    private void UpdateUI() {
        if (account != null) {
            login_accountId.setText(account.GetAccountId().toString());
            login_role.setText(account.GetRole());
            login_name.setText(account.GetName());
            if (account != null && "Student".equals(account.GetRole())) {
                DisableButtonsForStudentRole();
            }
        }
    }

    private void DisableButtonsForStudentRole() {
        DisableButtonWithLockIcon(managerBook_addBtn);
        DisableButtonWithLockIcon(managerBook_updateBtn);
        DisableButtonWithLockIcon(managerBook_resetBtn);
        DisableButtonWithLockIcon(managerBook_deleteBtn);
        DisableButtonWithLockIcon(managerBook_selectBookBtn);
        DisableButtonWithLockIcon(managerBook_borrowBookBtn);
        DisableButtonWithLockIcon(borrowedBook_selectBook);
        DisableButtonWithLockIcon(borrowedBook_returnBook);
        DisableButtonWithLockIcon(accountBtn);
        DisableButtonWithLockIcon(borrowedBook_studentDetailBtn);
    }

    private void DisableButtonWithLockIcon(Button button) {
        button.setDisable(true);
        button.setOpacity(0.8);
        ImageView lockIcon = new ImageView(
                new Image(getClass().getResourceAsStream("/com/mycompany/libraryhustmanagerment/Images/Lock.png")));
        lockIcon.setFitHeight(16);
        lockIcon.setFitWidth(16);
        button.setGraphic(lockIcon);
    }

    @FXML
    private void SignOut() throws IOException {
        boolean signOutConfirmed = showConfirmationAlert("Confirm Sign Out", "Are you sure you want to sign out?",
                "Please confirm if you want to sign out.");

        if (signOutConfirmed) {
            Account account = new Account();
            rootPane.getScene().getWindow().hide();
            account.SetAccountId(null);
            account.SetPassword(null);
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void ShowAllAccountsInTable() {
        List<Account> accounts = AccountEntity.ShowAllAccounts();
        account_accountTableView.getItems().setAll(accounts);
    }

    @FXML
    private void SelectAccount() {
        Account selectedAccount = account_accountTableView.getSelectionModel().getSelectedItem();

        if (selectedAccount != null) {
            account_accountID.setText(selectedAccount.GetAccountId().toString());
            account_name.setText(selectedAccount.GetName());
            account_emailAddress.setText(selectedAccount.GetEmailAddress());
            account_phoneNumber.setText(selectedAccount.GetPhoneNumber());
            account_password.setText(selectedAccount.GetPassword());
            LockTextFieldWithTooltip(account_accountID);
        } else {
            showAlert("Selection Error", "No Account Selected", "Please select an account from the list.");
        }
    }

    @FXML
    private void HandleAddAccount() {
        String accountIdText = account_accountID.getText();
        String name = account_name.getText();
        String emailAddress = account_emailAddress.getText();
        String phoneNumber = account_phoneNumber.getText();
        String password = account_password.getText();

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
            showAlert("Error!", "Cannot Add!", "Account ID already exists, please choose another one!");
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
            ;
            showAlert("Successfully!", "Registration Successful!",
                    "Account " + account.GetAccountId() + " has been added!");
            ClearFields();
        } catch (DuplicateEntryException e) {
            showAlert("Error!", "Cannot Register!", e.getMessage());
        }
    }

    @FXML
    private void HandleUpdateAccount() {
        if (!ValidateAccountFields()) {
            showAlert("Error", "Invalid Input", "Please ensure all fields are filled in correctly.");
            return;
        }

        Account account = new Account(
                Integer.valueOf(account_accountID.getText()),
                account_name.getText(),
                account_emailAddress.getText(),
                account_phoneNumber.getText(),
                account_password.getText());
        if(BorrowBookEntity.IsStudentBorrowingBook(account.GetAccountId())) {
            showAlert("Error", "Update Failed", "Account is being borrowed book.");
            return;
        }
        try {
            AccountEntity.UpdateAccount(account);
            showAlert("Success!", "Account Updated", "The account has been successfully updated.");
            RefreshAccountTableView();
        } catch (SQLException e) {
            showAlert("Error", "Update Failed", "An error occurred while updating the account.");
            e.printStackTrace();
        }
        ClearFields();
    }

    @FXML
    private void HandleDeleteAccount() {
        int accountId = Integer.parseInt(account_accountID.getText());
        if(BorrowBookEntity.IsStudentBorrowingBook(accountId)) {
            showAlert("Error", "Delete Failed", "Account is being borrowed book.");
            return;
        }
        try {
            Account account = AccountEntity.GetDataAccountById(accountId);

            if (account != null && "Librarian".equals(account.GetRole())) {
                showAlert("Error", "Delete Failed", "Cannot delete an account with the role 'Librarian'.");
                return;
            }

            if (showConfirmationAlert("Delete Account", "Confirm Delete",
                    "Are you sure you want to delete this account?")) {
                AccountEntity.DeleteAccount(accountId);
                showAlert("Success!", "Account Deleted", "The account has been successfully deleted.");
                RefreshAccountTableView();
            }
        } catch (SQLException e) {
            showAlert("Error", "Delete Failed", "An error occurred while deleting the account.");
            e.printStackTrace();
        }
        ClearFields();
    }

    @FXML
    private void RefreshAccountTableView() {
        try {
            ObservableList<Account> accounts = AccountEntity.GetAllAccounts();
            account_accountTableView.setItems(accounts);
        } catch (SQLException e) {
            showAlert("Error", "Refresh Failed", "An error occurred while refreshing the account list.");
            e.printStackTrace();
        }
    }

    @FXML
    private void HandleSearchByAccountId() {
        String idText = account_searchByUserID.getText();

        if (idText.isEmpty()) {
            showAlert("Error", "Input Required", "Please enter an account ID.");
            return;
        }

        try {
            int accountId = Integer.parseInt(idText);

            TableView<Account> tableView = account_accountTableView;
            ObservableList<Account> items = tableView.getItems();

            boolean found = false;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).GetAccountId() == accountId) {
                    tableView.getSelectionModel().select(i);
                    tableView.scrollTo(i);

                    tableView.getFocusModel().focus(i);
                    tableView.requestFocus();

                    found = true;
                    break;
                }
            }

            if (found) {
                Account account = AccountEntity.GetDataAccountById(accountId);
                if (account != null) {
                    account_accountID.setText(String.valueOf(account.GetAccountId()));
                    account_name.setText(account.GetName());
                    account_emailAddress.setText(account.GetEmailAddress());
                    account_phoneNumber.setText(account.GetPhoneNumber());
                    account_password.setText(account.GetPassword());

                    showAlert("Found", "Account Found", "Account " + account.GetAccountId() + " has been found!");
                } else {
                    showAlert("Not Found", "Account Not Found", "No account found with the provided ID.");
                }
            } else {
                showAlert("Not Found", "Account Not Found", "No account found with the provided ID.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid ID", "Please enter a valid numeric account ID.");
            e.printStackTrace();
        }

        LockTextFieldWithTooltip(account_accountID);
    }

    private boolean ValidateAccountFields() {
        if (account_accountID.getText().isEmpty() ||
                account_name.getText().isEmpty() ||
                account_emailAddress.getText().isEmpty() ||
                account_phoneNumber.getText().isEmpty() ||
                account_password.getText().isEmpty()) {
            return false;
        }

        boolean isValidAccountId = Account.validateAccountId(account_accountID.getText());
        boolean isValidPhoneNumber = Account.validatePhoneNumber(account_phoneNumber.getText());
        boolean isValidEmailAddress = Account.validateEmailAddress(account_emailAddress.getText());
        boolean isValidPassword = Account.validatePasswordHash(account_password.getText());

        return isValidAccountId && isValidPhoneNumber && isValidEmailAddress && isValidPassword;
    }

    private boolean isTextFieldLocked = false;

    private void LockTextFieldWithTooltip(TextField textField) {
        isTextFieldLocked = true;

        textField.setEditable(false);

        Tooltip tooltip = new Tooltip("Cannot edit Account ID for security reasons.");

        tooltip.setStyle(
                "-fx-font-size: 14px; -fx-text-fill: #fff; -fx-background-color: #000; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-width: 1px;");

        textField.setOnMouseEntered(event -> {
            if (isTextFieldLocked) {
                Bounds boundsInScreen = textField.localToScreen(textField.getBoundsInLocal());
                tooltip.show(textField, boundsInScreen.getMinX(), boundsInScreen.getMinY() - 50);
            }
        });

        textField.setOnMouseExited(event -> tooltip.hide());
    }

    @FXML
    private void ClearFields() {
        account_accountID.clear();
        account_name.clear();
        account_emailAddress.clear();
        account_phoneNumber.clear();
        account_password.clear();
        if (isTextFieldLocked) {
            account_accountID.setEditable(true);
            account_accountID.setOnMouseEntered(null);
            account_accountID.setOnMouseExited(null);
            isTextFieldLocked = false;
        }
    }
    // Initialize variable of borrow and return
    // SelectBorrowBook
    private BorrowBook selectedBorrowBook = null;
    private Account accountStudentLogin = null;
    private Boolean AccountIsLogin() {
        return FXMLDocumentController.GetAccountLogin() != null;
    }
    private Account GetAccountLogin() {
        if(AccountIsLogin()) {
            return FXMLDocumentController.GetAccountLogin();
        } 
        return null;
    }
    private Boolean IsStudent() {
        accountStudentLogin = FXMLDocumentController.GetAccountLogin();
        return AccountIsLogin() && GetAccountLogin().GetRole().equals("Student");
    }
    @FXML
    private void SelectBorrowBook() {
        selectedBorrowBook = borrowedBooks_tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!!!");
        alert.setHeaderText("Selected Book successfully!!!");
        alert.setContentText("Book is selected: " + selectedBorrowBook.getTitle());
        alert.showAndWait();
        SetValueForUpdateForm();
        System.out.println(selectedBorrowBook.getBookID());
        
    }
   
    
    // Setvalue for tableview
    @FXML
    void SetValueBorrowBookAll() throws SQLException {
        if(IsStudent()) {
            ObservableList<BorrowBook> borrowBookDataList = BorrowBookEntity.GetDataBorrowBooksByStudentID(accountStudentLogin.GetAccountId());
            setValueForBorrowBookTableView(borrowBookDataList);
            SetValueForAccountIDSearch();
            SetValueForBookIDSearch();
            SetValueForBorrowIDSearch();
        }
        else {
            ObservableList<BorrowBook> borrowBookDataList = BorrowBookEntity.GetDataBorrowBooks();
            setValueForBorrowBookTableView(borrowBookDataList);
        }
        
    }

    private void setValueForBorrowBookTableView(ObservableList<BorrowBook> borrowBookDataList) {
        // setvalue for table
        borrowedBooks_bookTitleTable.setCellValueFactory(new PropertyValueFactory<>("title"));
        borrowedBooks_bookIDTable.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        borrowedBooks_borrowIDTable.setCellValueFactory(new PropertyValueFactory<>("borrowID"));
        borrowedBooks_studentIDTable.setCellValueFactory(new PropertyValueFactory<>("accountID"));
        borrowedBooks_borrowDateTable.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        borrowedBooks_dueDateTable.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        borrowedBooks_returnDateTable.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        borrowedBooks_tableView.setItems(borrowBookDataList);
    }
    //Setvalue for borrowBook Combobox
    private void SetValueForBorrowIDSearch() {
        if(IsStudent()) {
            List<String> borrowIDList = BorrowBookEntity.getBorrowIDListByStudentID(accountStudentLogin.GetAccountId());
            SetValueForComboBox(borrowedBooks_borrowIDSearch, borrowIDList, "Borrow ID");
        }
        else {
            List<String> borrowIDList = BorrowBookEntity.getBorrowIDList();
            SetValueForComboBox(borrowedBooks_borrowIDSearch, borrowIDList, "Borrow ID");
        }
        
    }

    private void SetValueForBookIDSearch() {
        if(IsStudent()) {
            List<String> borrowIDList = BorrowBookEntity.getBookIDListByStudentID(accountStudentLogin.GetAccountId());
            SetValueForComboBox(borrowedBooks_bookIDSearch, borrowIDList, "Book ID");
        }
        else {
            List<String> bookIDList = BorrowBookEntity.getBookIDList();
            SetValueForComboBox(borrowedBooks_bookIDSearch, bookIDList, "Book ID");
        }
        
    }

    private void SetValueForAccountIDSearch() {
        if(IsStudent()) {
            List<String> borrowIDList = BorrowBookEntity.getAccountIDListByStudentID(accountStudentLogin.GetAccountId());
            SetValueForComboBox(borrowedBooks_studentIDSearch, borrowIDList, "Account ID");
        }
        else {
            List<String> accountIDList = BorrowBookEntity.getAccountIDList();
            SetValueForComboBox(borrowedBooks_studentIDSearch, accountIDList, "Account ID");
        }
        
    }

    @FXML
    private void SetValueBorrowBookSearch() throws SQLException {
        String borrowStringID = borrowedBooks_borrowIDSearch.getValue();
        String bookStringID = borrowedBooks_bookIDSearch.getValue();
        String accountStringID = borrowedBooks_studentIDSearch.getValue();
        if(IsStudent()) {
            if(("Account ID".equals(accountStringID) ||"None".equals(accountStringID)) &&
                    ("Borrow ID".equals(borrowStringID) ||"None".equals(borrowStringID))  &&
                    ("Book ID".equals(bookStringID) ||"None".equals(bookStringID))) {
                ObservableList<BorrowBook> borrowBookDataList = BorrowBookEntity.GetDataBorrowBooksByStudentID(accountStudentLogin.GetAccountId());
                setValueForBorrowBookTableView(borrowBookDataList);
                
                System.out.println("True");
                return;
            }
        }
//        System.out.println(borrowStringID);
//        System.out.println(bookStringID);
//        System.out.println(accountStringID);
        Integer borrowID = 0;
        Integer bookID = 0;
        Integer accountID = 0;

        try {
            borrowID = Integer.valueOf(borrowStringID);
            System.out.println(borrowID);
        } catch (NumberFormatException ex) {
            System.out.println("Search for all borrowID.");
           // return;
        }
        try {
            bookID = Integer.valueOf(bookStringID);
        } catch (NumberFormatException ex) {
            System.out.println("Search for all bookID.");
            //return;
        }
        try {
            accountID = Integer.valueOf(accountStringID);
        } catch (NumberFormatException ex) {
            System.out.println("Search for all accountID");
           // return;
        }
        setValueForBorrowBookTableView(BorrowBookEntity.GetDataBorrowSearch(borrowID, bookID, accountID));
        
    }
    @FXML
    private void ShowStudentInforDetail() throws SQLException {
        if (selectedBorrowBook != null) {
            Account studentAccount = AccountEntity.GetDataAccountById(selectedBorrowBook.getAccountID());
            if (studentAccount == null) {
                showAlert("Error!", "Show Infor Failure!", "Student is deleted or not exist");
                return;
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Student Details");
            alert.setHeaderText(null); // Không có tiêu đề

            // Tạo VBox để chứa thông tin
            VBox content = new VBox();
            content.getChildren().addAll(
                    new Label("ID: " + studentAccount.GetAccountId().toString()),
                    new Label("Name: " + studentAccount.GetName()),
                    new Label("Emai: " + studentAccount.GetEmailAddress()),
                    new Label("Phone Number: " + studentAccount.GetPhoneNumber()));

            alert.getDialogPane().setContent(content);
            alert.showAndWait();
        }
    } 
    @FXML
    private void BorrowBook() {
        if (selectedBook != null) {
            System.out.println(selectedBook.getBookID());
            // If no book available
            if (selectedBook.getAvailBook() <= 0) {
                showAlert("Error", "Borrow Book Failure", "This book is out of stock!!!");
                return;
            } else {
                Integer bookID = null;
                bookID = selectedBook.getBookID();
                String title = "";
                title = selectedBook.getBookTitle();
                Integer studentID = null;
                try {                    
                    studentID = Integer.valueOf(managerBook_studentID.getText());
                    Account studentAccount = AccountEntity.GetDataAccountById(studentID);
                    if(studentAccount == null) {
                        showAlert("Error!!!","Borrow Book Failure!!!","Incorrect student ID, please try again!!!");
                        
                        return;
                    }
                    if (account != null && "Librarian".equals(studentAccount.GetRole())) {
                        showAlert("Error", "Borrow Book Failure", "Borrower must be a 'student'.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setHeaderText("Borrow Book Failure!!!");
                    alert.setContentText("Incorrect student ID, please try again!!!");
                    alert.showAndWait();
                    return;
                }
                // - 1 book from number of available books
                BookEntity.UpdateBorrowAvailBook(selectedBook);
                // Add to Borrow Book
                BorrowBook newBorrow = new BorrowBook(bookID, studentID, title);
                BorrowBookEntity.BorrowBook(newBorrow);
                showAlert("Success!!!","Book is borrowed successfully!!!","Book borrowed is: " + selectedBook.getBookTitle());
 
                managerBook_studentID.clear();
                ResetForm();
                SetValueForBorrowIDSearch();
                SetValueForBookIDSearch();
                SetValueForAccountIDSearch();
                
                try {
                    SetValueMangagetBookAll();
                    SetValueBorrowBookAll();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    @FXML
    private void ReturnBook() {
        if (selectedBorrowBook != null) {
            if (selectedBorrowBook.getReturnDate() != null) {
                showAlert("Error!!!","Return Book Failure!!!","Book returned is: " + selectedBorrowBook.getTitle());
                return;
            } else {
                Integer bookID = null;
                bookID = selectedBorrowBook.getBookID();
                // - 1 book from number of available books
                BorrowBookEntity.returnBook(selectedBorrowBook);
                BookEntity.UpdateReturnAvailBook(bookID, BookEntity.getAvailLeftFromID(bookID));
                showAlert("Success!!!","Book is Returned successfully!!!","Book returned is: " + selectedBorrowBook.getTitle());
                try {
                    SetValueMangagetBookAll();
                    SetValueBorrowBookAll();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // unselect selectedBorrowedBook;
        selectedBorrowBook = null;
    }
}
