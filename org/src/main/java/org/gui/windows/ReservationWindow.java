package org.gui.windows;

import com.vaadin.ui.*;
import org.model.objects.dto.Auto;
import org.model.objects.dto.ReservationRequest;
import org.process.control.ReservationProcess;

public class ReservationWindow extends Window {

    public ReservationWindow(final Auto auto) {
        super("Reservierung");
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label("Reservierung für: " + auto.getMarke() + " " + auto.getModell()) );
        verticalLayout.setMargin(true);
        setContent(verticalLayout);

        setClosable(true);

        Button reservierungsButton = new Button("Reservierung");
        reservierungsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                ReservationRequest reservationRequest = new ReservationRequest();

                reservationRequest.setAuto(auto);

                ReservationProcess.getInstance().createReservation(reservationRequest , ReservationWindow.this);

            }
        });

        verticalLayout.addComponent(reservierungsButton);
        verticalLayout.setComponentAlignment(reservierungsButton, Alignment.MIDDLE_CENTER);

    }

}
