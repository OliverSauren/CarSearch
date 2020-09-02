package org.model.factories;

import org.model.objects.dto.ReservationRequest;
import org.model.objects.dto.UserDTO;
import org.model.objects.entities.Reservation;

public class ReservationFactory {

    public static Reservation createReservation(ReservationRequest reservationRequest , UserDTO user) {

        Reservation reservation = new Reservation();
        reservation.setAuto(reservationRequest.getAuto());
        reservation.setUser(user);
        return reservation;

    }

}
