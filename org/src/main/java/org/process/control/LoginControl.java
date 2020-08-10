package org.process.control;

import com.vaadin.ui.UI;
import org.example.MyUI;
import org.model.objects.dto.User;
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

        User user = null;

        try {
            if ( resultSet.next()) {

                user = new User();
                user.setLogin(resultSet.getString(1));
                user.setVorname(resultSet.getString(3));
                user.setNachname(resultSet.getString(4));

            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException sqlException) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, sqlException);
        }
        finally {
            JDBCConnection.getInstance().closeConnection();
        }

        ((MyUI) UI.getCurrent()).setUser(user);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);



    }

    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/CarSearch");
    }
}
