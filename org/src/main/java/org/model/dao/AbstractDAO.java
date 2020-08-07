package org.model.dao;

import org.process.control.exceptions.DatabaseException;
import org.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO {

    protected Statement getStatement() {

        Statement statement = null;
        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException databaseException) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, databaseException);
        }

        return statement;

    }

    protected PreparedStatement getPreparedStatement(String sql) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException databaseException) {
            Logger.getLogger(AutoDAO.class.getName()).log(Level.SEVERE, null, databaseException);
        }

        return preparedStatement;

    }

}
