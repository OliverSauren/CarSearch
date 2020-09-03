package org.gui.windows;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.model.objects.dto.ReservationDetail;
import org.process.control.ReservationProcess;

import java.util.List;

public class ListReservationWindow extends Window {

    private int currentID;
    private List<ReservationDetail> reservationDetailList;
    private ReservationDetail reservationSelektiert;

    Button deleteButton = new Button("Storniere Reservierung");

    public ListReservationWindow() {
        super("Liste aller Reservierungen:");
        center();
        VerticalLayout verticalLayout = new VerticalLayout();

        Grid<ReservationDetail> reservationDetailGrid = new Grid<>(ReservationDetail.class);
        reservationDetailGrid.setSizeFull();
        reservationDetailGrid.setHeightMode(HeightMode.UNDEFINED);

        SingleSelect<ReservationDetail> select = reservationDetailGrid.asSingleSelect();

        reservationDetailList = ReservationProcess.getInstance().getAllReservationsForUser();

        reservationDetailGrid.addSelectionListener(selectionEvent -> {
            this.reservationSelektiert = select.getValue();
        });

        reservationDetailGrid.removeAllColumns();
        reservationDetailGrid.setCaption("Alle Reservierungen");

        reservationDetailGrid.setItems(reservationDetailList);

        //TODO addColumn Die Frage ist, was genau sonst unter Auto angezeigt wird, da noch genauer drauf achten!

        reservationDetailGrid.addColumn(ReservationDetail::getMarke).setCaption("Marke");
        reservationDetailGrid.addColumn(ReservationDetail::getModell).setCaption("Modell");
        reservationDetailGrid.addColumn(ReservationDetail::getId).setCaption("Reservierungs-ID");

        deleteButton.addClickListener(clickEvent -> {
            ReservationProcess.getInstance().deleteReservationByID(currentID);
            reservationDetailList = ReservationProcess.getInstance().getAllReservationsForUser();

            reservationDetailGrid.removeAllColumns();
            reservationDetailGrid.setItems(reservationDetailList);

            //TODO selber Fall wie oben!

            reservationDetailGrid.addColumn(ReservationDetail::getMarke).setCaption("Marke");
            reservationDetailGrid.addColumn(ReservationDetail::getModell).setCaption("Modell");
            reservationDetailGrid.addColumn(ReservationDetail::getId).setCaption("Reservierungs-ID");

            reservationDetailGrid.setSizeFull();
            reservationDetailGrid.setHeightMode(HeightMode.UNDEFINED);

        });

        verticalLayout.addComponent(reservationDetailGrid);
        verticalLayout.addComponent(deleteButton);
        verticalLayout.setComponentAlignment(deleteButton, Alignment.MIDDLE_CENTER);

        reservationDetailGrid.addItemClickListener(itemClick -> {
            currentID = itemClick.getItem().getId();
        });

        this.setContent(verticalLayout);

    }

}
