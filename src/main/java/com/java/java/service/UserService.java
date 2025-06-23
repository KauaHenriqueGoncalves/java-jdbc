package com.java.java.service;

import com.java.java.components.PopUp;
import com.java.java.model.User;
import com.java.java.repositories.UserDAO;
import javafx.scene.layout.AnchorPane;
import java.sql.Date;

/**
 * Service class responsible for handling business logic related to User operations,
 * including validation and interaction with the UserDAO.
 */
public class UserService {

    private UserDAO userDAO;
    private AnchorPane rootPage = null;

    /**
     * Default constructor for UserService.
     */
    public UserService() {}

    /**
     * Constructs a UserService with a reference to the root UI page.
     *
     * @param rootPage The root AnchorPane of the user interface for displaying popups.
     */
    public UserService(AnchorPane rootPage) {
        this.rootPage = rootPage;
    }

    /**
     * Gets the root UI page.
     *
     * @return The AnchorPane used for UI popups.
     */
    public AnchorPane getRootPage() {
        return rootPage;
    }

    /**
     * Sets the root UI page.
     *
     * @param rootPage The AnchorPane to be used for UI popups.
     */
    public void setRootPage(AnchorPane rootPage) {
        this.rootPage = rootPage;
    }

    /**
     * Creates a new user after performing validation checks.
     *
     * @param user The User object to be created.
     * @return true if the user is successfully created; false otherwise.
     */
    public boolean createUser(User user) {

        Date dateSQl = (Date) user.getDateOfBirth();
        Date dateNow = new Date(System.currentTimeMillis());

        this.userDAO = new UserDAO();

        if (!user.isCPFValid()) {
            PopUp.createPopUp(this.rootPage, "CPF Invalid!", false);
            return false;
        }
        else if (user.getName().isEmpty()) {
            PopUp.createPopUp(this.rootPage, "Invalid Name!", false);
            return false;
        }
        else if (dateSQl.after(dateNow)) {
            PopUp.createPopUp(this.rootPage, "Invalid Date of Birth!", false);
            return false;
        }
        else if (this.userDAO.cpfExists(user.getCpf())) {
            PopUp.createPopUp(this.rootPage, "CPF already registered!", false);
            return false;
        }

        boolean status = userDAO.insert(user);

        if (!status) PopUp.createPopUp(this.rootPage, "Some Error in DataBase!", false);

        return status;
    }

    /**
     * Updates an existing user after performing validation checks.
     *
     * @param user The User object with updated information.
     * @return true if the update is successful; false otherwise.
     */
    public boolean updateUser(User user) {

        Date dateSQl = (Date) user.getDateOfBirth();
        Date dateNow = new Date(System.currentTimeMillis());

        this.userDAO = new UserDAO();

        if (user.getName().isEmpty()) {
            PopUp.createPopUp(this.rootPage, "Invalid Name!", false);
            return false;
        }
        else if (dateSQl.after(dateNow)) {
            PopUp.createPopUp(this.rootPage, "Invalid Date of Birth!", false);
            return false;
        }

        boolean status = userDAO.update(user);

        if (!status) PopUp.createPopUp(this.rootPage, "Some Error in DataBase!", false);

        return status;
    }

    /**
     * Deletes a user from the system.
     *
     * @param user The User object to be deleted.
     * @return true if the deletion is successful; false otherwise.
     */
    public boolean deleteUser(User user) {

        this.userDAO = new UserDAO();
        boolean status = userDAO.delete(user.getId());

        if (!status) PopUp.createPopUp(this.rootPage, "Some Error in DataBase!", false);

        return status;
    }

}
