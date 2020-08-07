package org.model.dao;

import org.model.objects.dto.Auto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO extends AbstractDAO {

    public static AutoDAO autoDAO = null;

    private AutoDAO() {

    }

    public static AutoDAO getInstance() {
        if (autoDAO == null) {
            autoDAO = new AutoDAO();
        }
        return autoDAO;
    }

    public List<Auto> getAuto(String autoSQL) {
        Statement statement = this.getStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(
                    "SELECT *"
                    + "FROM carSearch.auto "
                    + "WHERE carSearch.auto.mark = \'" + autoSQL + "\' "
                    + "OR WHERE carSearch.auto.modell = \'" + autoSQL + "\' "
                    + "OR WHERE carSearch.auto.baujahr = \'" + autoSQL + "\'"
                    + "OR WHERE carSearch.auto.beschreibung = \'" + autoSQL + "\'");
        } catch (SQLException sqlException) {

        }

        if (resultSet == null) {
            return null;
        }

        List<Auto> autoList = new ArrayList<>();
        Auto auto = null;

        try {
            while (resultSet.next()) {
                auto = new Auto();
                auto.setId(resultSet.getInt(1));
                auto.setMarke(resultSet.getString(2));
                auto.setModell(resultSet.getString(3));
                auto.setBaujahr(resultSet.getInt(4));
                auto.setBeschreibung(resultSet.getString(5));
            }
        } catch (SQLException sqlException) {

        }

        return autoList;

    }

}
