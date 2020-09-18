package org.model.dao;

import org.model.objects.dto.ReservationDetail;
import org.model.objects.dto.UserDTO;
import org.model.objects.entities.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationDAO extends AbstractDAO {

    public static ReservationDAO reservationDAO = null;

    private ReservationDAO() {

    }

    public static ReservationDAO getInstance() {
        if (reservationDAO == null) {
            reservationDAO = new ReservationDAO();
        }
        return reservationDAO;
    }

    public boolean addReservation(Reservation reservation) {
        String sql = "insert into car.reservation values (default,?,?);";
        PreparedStatement preparedStatement = this.getPreparedStatement(sql);

        try {
            preparedStatement.setString(1, reservation.getUser().getLogin());
            preparedStatement.setInt(2, reservation.getAuto().getId());

            preparedStatement.executeUpdate();

            setReservationID(reservation);

            return true;

        } catch (SQLException sqlException) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            return false;
        }

    }

    public List<ReservationDetail> getAllBookingsForUser(UserDTO user) {
        Statement statement = this.getStatement();

        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(
                    "SELECT car.auto.marke, car.auto.modell, car.reservation.id " +
                            "FROM car.reservation JOIN car.auto " +
                            "ON ( car.reservation.autoid = car.auto.id) " +
                            "WHERE car.reservation.userid = \'" + user.getLogin() + "\' "
            );
        } catch (SQLException sqlException) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }

        if (resultSet == null) return null;

        List<ReservationDetail> reservationDetailList = new ArrayList<>();
        ReservationDetail reservationDetail = null;

        try {
            while (resultSet.next()) {
                reservationDetail = new ReservationDetail();
                reservationDetail.setMarke(resultSet.getString(1));
                reservationDetail.setModell(resultSet.getString(2));
                reservationDetail.setId(resultSet.getInt(3));

                reservationDetailList.add(reservationDetail);

            }
        } catch (SQLException sqlException) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }

        return reservationDetailList;

    }

    private void setReservationID(Reservation reservation) {
        Statement statement = this.getStatement();

        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery("SELECT max(car.reservation.id) " +
                    "FROM car.reservation");
        } catch (SQLException sqlException) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }

        int currentValue = 0;
        try {
            resultSet.next();
            currentValue = resultSet.getInt(1);
        } catch (SQLException sqlException) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }

        reservation.setId(currentValue);

    }

    public void deleteReservationByID(int id) {
        Statement statement = this.getStatement();

        try {
            statement.executeQuery("DELETE FROM car.reservation WHERE car.reservation.id = \'" + id + "\';");
        } catch (SQLException sqlException) {
            //TODO
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }

    }

}
