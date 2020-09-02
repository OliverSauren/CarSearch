package org.model.dao;


import org.model.objects.entities.Registration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationsDAO extends AbstractDAO {

    public static RegistrationsDAO registrationsDAO = null;

    private RegistrationsDAO() {

    }

    public static RegistrationsDAO getInstance() {
        if (registrationsDAO == null) {
            registrationsDAO = new RegistrationsDAO();
        }
        return registrationsDAO;
    }

    public boolean addUser(Registration registration) {
        String sql = "insert into car.user values (default,?,?,?,?);";
        PreparedStatement preparedStatement = this.getPreparedStatement(sql);

        try {
            preparedStatement.setString(1 , registration.getLogin());
            preparedStatement.setString(2 , registration.getPasswort());
            preparedStatement.setString(3 , registration.getVorname());
            preparedStatement.setString(4 , registration.getNachname());

            preparedStatement.executeUpdate();

            setUserID(registration);

            return true;

        } catch (SQLException sqlException) {
            Logger.getLogger(RegistrationsDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            return false;
        }

    }

    private void setUserID(Registration registration) {
        Statement statement = this.getStatement();

        ResultSet resultSet = null;

        try {

            resultSet = statement.executeQuery("SELECT max(car.user.id)" +
                    "FROM car.user");

        } catch (SQLException sqlException) {
            Logger.getLogger(RegistrationsDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }

        int currentValue = 0;

        try {

            resultSet.next();
            currentValue = resultSet.getInt(1);

        } catch (SQLException sqlException) {
            Logger.getLogger(RegistrationsDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }

        registration.setId(currentValue);

    }

    //TODO Prüfen, ist Nutzer / bzw. Email bereits vorhanden?! --> Fehlerhandling
    //TODO Prüfen, ob die Email tatsächlich valide ist

}
