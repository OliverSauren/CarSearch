package org.process.control;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.gui.ui.MyUI;
import org.gui.windows.ConfirmationWindow;
import org.model.dao.ReservationDAO;
import org.model.factories.ReservationFactory;
import org.model.objects.dto.ReservationRequest;
import org.model.objects.dto.UserDTO;
import org.model.objects.entities.Reservation;

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

            //TODO Fehlerhandling

        }

    }



}
