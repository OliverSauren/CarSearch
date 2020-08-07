package org.gui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.example.MyUI;
import org.model.objects.dto.Auto;
import org.model.objects.dto.User;
import org.services.util.Views;

import java.util.List;

public class MainView extends VerticalLayout implements View {

    private List<Auto> autoList;
    private Integer anzahlSuchanfragen;

    //TODO Prüfen ob User bereits eingeloggt oder nicht

    //TODO Top-Panel hinzufügen

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        User user = ((MyUI) UI.getCurrent()).getUser();

        if ( user == null) {
           // UI.getCurrent().getNavigator().navigateTo(Views.LOGIN); //Erstmal zu Testzwecken nicht ausführen
            this.setUp();
        } else {
           // this.setUp();
        }

    }

    public void setUp() {

        String vorname = null;

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button sucheButton = new Button("Suche" , VaadinIcons.SEARCH);
        TextField textField = new TextField();

        User user = ((MyUI) UI.getCurrent()).getUser();

        if ( user != null) {
            vorname = user.getVorname();
        }

        Label labelText = new Label(vorname + ", gebe das gesuchte Auto ein: ");

        horizontalLayout.addComponent(labelText);
        horizontalLayout.addComponent(textField);
        horizontalLayout.addComponent(sucheButton);
        horizontalLayout.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);

        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        Grid<Auto> grid = new Grid<>(Auto.class);
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);

        SingleSelect<Auto> autoSingleSelect = grid.asSingleSelect();

        sucheButton.addClickListener(e -> {
            String auto = textField.getValue(); //TODO Spätere On-the-fly implementierung!

            if (auto.equals("")) {
                Notification.show("Bitte etwas Eingeben!", Notification.Type.WARNING_MESSAGE);
            } else {

                addComponent(grid);
                anzahlSuchanfragen++;
                grid.removeAllColumns();
                grid.setCaption("Suchergebnisse");
                grid.setItems(); //TODO

                grid.addColumn(Auto::getId).setCaption("Auto-ID");
                grid.addColumn(Auto::getMarke).setCaption("Marke");
                grid.addColumn(Auto::getModell).setCaption("Modell");
                grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
                grid.addColumn(Auto::getBeschreibung).setCaption("Beschreibung");

            }

        });



    }


}
