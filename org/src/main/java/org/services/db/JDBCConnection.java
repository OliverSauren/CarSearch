
package org.services.db;

import org.process.control.exceptions.DatabaseException;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {

    private static JDBCConnection connection = null;

    private String url = "jdbx:postgresql://dumbo.inf.h-brs.de/osaure2s";

    private Connection conn;

    private String login = "osaure2s";
    private String password  = "osaure2s";

    public static JDBCConnection getInstance() throws DatabaseException {

        if( connection == null) {
            connection = new JDBCConnection();
        }
        return connection;

    }

    private JDBCConnection() throws DatabaseException {
        this.initConnection();
    }

    public void initConnection() throws DatabaseException {

        try {
            DriverManager.registerDriver(new org.postgresql.Driver() );
        } catch (SQLException exception) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, exception);
        }
        this.openConnection();
    }

    public void openConnection() throws DatabaseException {

        try {

            Properties properties = new Properties();
            properties.setProperty("user" , login);
            properties.setProperty("password" , password);

            this.conn = DriverManager.getConnection(this.url, properties);

        } catch (SQLException exception) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, exception);
            throw new DatabaseException( "Fehler bei Zugriff auf die Datenbank! Sichere Verbindung überprüfen!");
        }
    }

    public Statement getStatement() throws DatabaseException {

        try {
            if ( this.conn.isClosed() ) {
                this.openConnection();
            }
            return this.conn.createStatement();

        } catch (SQLException exception) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, exception);
            return null;
        }

    }

    public PreparedStatement getPreparedStatement( String sql) throws DatabaseException {

        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }

            return this.conn.prepareStatement(sql);

        } catch (SQLException exception) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, exception);
            return null;
        }

    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException exception) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

}

