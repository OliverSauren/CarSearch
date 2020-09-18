package org.gui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.gui.ui.MyUI;
import org.gui.windows.ListReservationWindow;
import org.model.objects.dto.UserDTO;
import org.process.control.LoginControl;

public class TopPanel extends HorizontalLayout {

    public TopPanel() {

        this.setSizeFull();

        Label headLabel = new Label("CarLook Ltd. Autosuche");
        headLabel.setSizeUndefined();

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel , Alignment.TOP_LEFT);

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        UserDTO userDTO = ((MyUI) UI.getCurrent()).getUserDTO();

        String vorname = null;

        if ( userDTO != null) {
            vorname = userDTO.getVorname();
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
                ListReservationWindow listReservationWindow = new ListReservationWindow();
                listReservationWindow.setHeight("50%");
                listReservationWindow.setWidth("50%");
                listReservationWindow.center();
                UI.getCurrent().addWindow(listReservationWindow);
            }
        });

        horizontalLayout.addComponent(menuBar);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout , Alignment.TOP_RIGHT);

    }

}
