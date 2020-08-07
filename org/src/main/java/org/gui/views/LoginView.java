package org.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.example.MyUI;
import org.model.objects.dto.User;
import org.process.control.LoginControl;
import org.process.control.exceptions.DatabaseException;
import org.process.control.exceptions.NoSuchUserOrPassword;
import org.services.util.Views;

public class LoginView extends VerticalLayout implements View {

    public void setUp() {

        this.setSizeFull();

        final TextField userLogin = new TextField();
        userLogin.setCaption("Login");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(userLogin);
        verticalLayout.addComponent(passwordField);

        Button loginButton = new Button("Login");
        verticalLayout.addComponent(loginButton);
        verticalLayout.setComponentAlignment(loginButton , Alignment.MIDDLE_CENTER);

        Panel panel = new Panel("Login Daten eingeben");

        this.addComponent(panel);
        this.setComponentAlignment(panel , Alignment.MIDDLE_CENTER);

        panel.setContent(verticalLayout);
        panel.setSizeUndefined();

        loginButton.addClickListener(e -> {
            String login = userLogin.getValue();
            String passwort = passwordField.getValue();

            try {

                LoginControl.checkAuthenification(login, passwort);

            } catch (NoSuchUserOrPassword noSuchUserOrPassword) {

                Notification.show("Fehler" , "Login oder Passwort falsch!" , Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");

            } catch (DatabaseException databaseException) {

                Notification.show("Datenbank-Fehler" , Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");

            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        User user = ((MyUI) UI.getCurrent()).getUser();

        if ( user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
            this.setUp();
        }

    }

}
