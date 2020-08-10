package org.gui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.example.MyUI;
import org.model.objects.dto.User;
import org.process.control.LoginControl;

public class TopPanel extends HorizontalLayout {

    public TopPanel() {

        this.setSizeFull();

        Label headLabel = new Label("Car Search");
        headLabel.setSizeUndefined();

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel , Alignment.TOP_LEFT);

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        User user = ((MyUI) UI.getCurrent()).getUser();

        String vorname = null;

        if ( user != null) {
            vorname = user.getVorname();
        }

        Label logLabl = new Label("Wilkommen " + vorname + "!");
        logLabl.setSizeUndefined();

        horizontalLayout.addComponent(logLabl);
        horizontalLayout.setComponentAlignment(logLabl , Alignment.MIDDLE_CENTER);

        MenuBar menuBar = new MenuBar();
        MenuBar.MenuItem menuItem = menuBar.addItem("Menu" , null);

        menuItem.addItem("Logout", VaadinIcons.CLOSE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                LoginControl.logoutUser();
            }
        });

        menuItem.addItem("Reservierungen", VaadinIcons.CAR, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                //TODO Liste aller Reservierung in einem Window darstellen
            }
        });

        horizontalLayout.addComponent(menuBar);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout , Alignment.TOP_RIGHT);

    }

}
