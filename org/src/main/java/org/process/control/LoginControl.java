package org.process.control;

import com.vaadin.ui.UI;
import org.gui.ui.MyUI;
import org.model.objects.dto.UserDTO;
import org.process.control.exceptions.DatabaseException;
import org.process.control.exceptions.NoSuchUserOrPassword;
import org.services.db.JDBCConnection;
import org.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {


    public static void checkAuthenification(String login, String passwort) throws NoSuchUserOrPassword, DatabaseException {

        ResultSet resultSet = null;

        try {

            Statement statement = JDBCConnection.getInstance().getStatement();

            resultSet = statement.executeQuery("SELECT * " +
                    "FROM car.user " +
                    "WHERE car.user.login = '" + login + "\'" +
                    "AND car.user.passwort = \'" + passwort + "\'");

        } catch (SQLException sqlException) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, sqlException);
            throw new DatabaseException("Datenbankfehler!");
        }

        UserDTO userDTO = null;

        try {
            if ( resultSet.next()) {

                userDTO = new UserDTO();
                userDTO.setLogin(resultSet.getString(2));
                userDTO.setVorname(resultSet.getString(4));
                userDTO.setNachname(resultSet.getString(5));

            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException sqlException) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, sqlException);
        }
        finally {
            JDBCConnection.getInstance().closeConnection();
        }

        ((MyUI) UI.getCurrent()).setUserDTO(userDTO);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);



    }

    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/CarSearch");
    }
}
