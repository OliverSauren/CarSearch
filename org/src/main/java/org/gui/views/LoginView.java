package org.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.gui.ui.MyUI;
import org.gui.windows.RegistrationWindow;
import org.model.objects.dto.UserDTO;
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

        Label labelText = new Label("Noch nicht registriert?");
        verticalLayout.addComponent(labelText);
        verticalLayout.setComponentAlignment(labelText , Alignment.MIDDLE_CENTER);

        Button registrationButton = new Button("Zur Registrierung");
        verticalLayout.addComponent(registrationButton);
        verticalLayout.setComponentAlignment(registrationButton , Alignment.MIDDLE_CENTER);



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

                Notification.show("Fehler" , "Login oder Passwort falsch! Bitte erneut versuchen." , Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");

            } catch (DatabaseException databaseException) {

                Notification.show("Datenbank-Fehler; Bitte sichere Verbindung überprüfen." , Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");

            }



        });


        registrationButton.addClickListener(e -> {

            RegistrationWindow registrationWindow = new RegistrationWindow();
            registrationWindow.setSizeUndefined();
            registrationWindow.center();
            UI.getCurrent().addWindow(registrationWindow);


        });


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        UserDTO userDTO = ((MyUI) UI.getCurrent()).getUserDTO();

        if ( userDTO != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
            this.setUp();
        }

    }

}
