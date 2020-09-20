package org.process.control;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.gui.ui.MyUI;
import org.gui.windows.ConfirmationWindow;
import org.model.dao.ReservationDAO;
import org.model.factories.ReservationFactory;
import org.model.objects.dto.ReservationDetail;
import org.model.objects.dto.ReservationRequest;
import org.model.objects.dto.UserDTO;
import org.model.objects.entities.Reservation;

import java.util.List;

public class ReservationProcess {

    public static ReservationProcess reservationProcess = null;

    private ReservationProcess() {

    }

    public static ReservationProcess getInstance() {
        if (reservationProcess == null) {
            reservationProcess = new ReservationProcess();
        }
        return reservationProcess;
    }

    public void createReservation(ReservationRequest reservationRequest , Window window) {

        UserDTO user = (UserDTO) ((MyUI) UI.getCurrent()).getUserDTO();

        Reservation reservation = ReservationFactory.createReservation(reservationRequest , user);

        boolean result = ReservationDAO.getInstance().addReservation(reservation);

        if (result == true) {

            window.close();
            UI.getCurrent().addWindow(new ConfirmationWindow("Reservierung erfolgreich! ID: " + reservation.getId()));

        } else {

            Notification.show("Bei der Reservierung ist ein unerwarteter Fehler aufgetreten." , Notification.Type.ERROR_MESSAGE);

        }

    }

    public void deleteReservationByID(int id) {
        ReservationDAO.getInstance().deleteReservationByID(id);
        UI.getCurrent().addWindow(new ConfirmationWindow("Reservierung wurde storniert!"));
    }

    public List<ReservationDetail> getAllReservationsForUser() {

        UserDTO user = ((MyUI) UI.getCurrent()).getUserDTO();

        return ReservationDAO.getInstance().getAllBookingsForUser(user);

    }


}
