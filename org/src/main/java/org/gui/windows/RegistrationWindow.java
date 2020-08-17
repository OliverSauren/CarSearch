package org.gui.windows;

import com.vaadin.ui.*;
import org.model.objects.dto.Auto;
import org.model.objects.dto.RegistrationRequest;
import org.process.control.RegistrationProcess;

public class RegistrationWindow extends Window {

    public RegistrationWindow() {
        super("Registrierung");
        center();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label("Neue Registrierung"));
        verticalLayout.setMargin(true);
        setContent(verticalLayout);

        final TextField vornameFeld = new TextField();
        vornameFeld.setCaption("Vorname: ");
        verticalLayout.addComponent(vornameFeld);

        final TextField nachnameFeld = new TextField();
        nachnameFeld.setCaption("Nachname: ");
        verticalLayout.addComponent(nachnameFeld);

        final TextField loginFeld = new TextField();
        loginFeld.setCaption("E-Mail: ");
        //loginFeld.setValue("example@example.com");
        verticalLayout.addComponent(loginFeld);


        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");
        verticalLayout.addComponent(passwordField);

        setClosable(true);

        Button registrationButton = new Button("Registration");
        registrationButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                RegistrationRequest registrationRequest = new RegistrationRequest();

                registrationRequest.setLogin(loginFeld.getValue());
                registrationRequest.setPasswort(passwordField.getValue());
                registrationRequest.setVorname(vornameFeld.getValue());
                registrationRequest.setNachname(nachnameFeld.getValue());


                RegistrationProcess.getInstance().createUser(registrationRequest , RegistrationWindow.this);

            }
        });

        verticalLayout.addComponent(registrationButton);
        verticalLayout.setComponentAlignment(registrationButton , Alignment.MIDDLE_CENTER);

    }

}
