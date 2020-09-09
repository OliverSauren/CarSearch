package org.gui.views;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.gui.ui.MyUI;
import org.gui.components.TopPanel;
import org.gui.windows.ReservationWindow;
import org.model.objects.dto.Auto;
import org.model.objects.dto.UserDTO;
import org.process.control.AutoSearch;
import org.services.util.Views;

import java.util.List;

public class MainView extends VerticalLayout implements View {

    private Auto autoSelektiert = null;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        UserDTO userDTO = ((MyUI) UI.getCurrent()).getUserDTO();

        if ( userDTO == null) {
           UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        } else {
           this.setUp();
        }

    }

    public void setUp() {

        addComponent(new TopPanel());

        String vorname = null;

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField suchFeld = new TextField();

        UserDTO userDTO = ((MyUI) UI.getCurrent()).getUserDTO();

        if ( userDTO != null) {
            vorname = userDTO.getVorname();
        }

        Label informationLabel = new Label("Sie können ein Auto über die Marke oder das Modell suchen.");
        Label labelText = new Label(vorname + ", gebe das gesuchte Auto ein: ");

        horizontalLayout.addComponent(labelText);
        horizontalLayout.addComponent(suchFeld);
        horizontalLayout.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);

        addComponent(informationLabel);
        setComponentAlignment(informationLabel , Alignment.MIDDLE_CENTER);
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        Grid<Auto> grid = new Grid<>(Auto.class);
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        Button reservierungsButton = new Button("Reservieren");


        reservierungsButton.addClickListener(e -> {

            if (autoSelektiert == null) {
                return;
            } else {
                System.out.println("Auto selektiert: " + this.autoSelektiert.getMarke() + " " + this.autoSelektiert.getModell());

                ReservationWindow reservationWindow = new ReservationWindow(MainView.this.autoSelektiert);
                UI.getCurrent().addWindow(reservationWindow);

            }


        });
        grid.addItemClickListener(event ->
                autoSelektiert = event.getItem());

        suchFeld.setValueChangeMode(ValueChangeMode.EAGER);
        suchFeld.addValueChangeListener(event -> {

            String autoSQL = "%" + suchFeld.getValue() + "%";
            System.out.println(autoSQL);


            addComponent(grid);
            grid.setCaption("Suchergebnisse");


            List<Auto> autoList = AutoSearch.getInstance().getAuto(autoSQL);

            grid.setItems(autoList);
            grid.removeAllColumns();

            if (autoList.size() == 0) {

            } else {

                grid.setHeightByRows(autoList.size());

                grid.addColumn(Auto::getId).setCaption("Auto-ID");
                grid.addColumn(Auto::getMarke).setCaption("Marke");
                grid.addColumn(Auto::getModell).setCaption("Modell");
                grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
                grid.addColumn(Auto::getBeschreibung).setCaption("Beschreibung");

                addComponent(reservierungsButton);
                setComponentAlignment(reservierungsButton, Alignment.MIDDLE_CENTER);

            }

        });


    }


}
