package org.gui.windows;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.*;
import org.model.objects.dto.Auto;
import org.model.objects.dto.RegistrationRequest;
import org.process.control.RegistrationProcess;
import org.services.util.Email;

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

                //TODO Email validierung iwie einbauen

                Binder<Email> binder = new Binder<>();
                binder.forField(loginFeld)
                        .withValidator(new EmailValidator("This doesn't look like a valid mail!"))
                        .bind(Email::getMail, Email::setMail);


                if (binder.isValid()) {
                    if (vornameFeld.getValue().equals("") ||
                            nachnameFeld.getValue().equals("") ||
                            loginFeld.getValue().equals("") ||
                            passwordField.getValue().equals("")) {
                        Notification.show("Eingaben unvollständig!" , Notification.Type.ERROR_MESSAGE);
                    }
                    else {
                        RegistrationRequest registrationRequest = new RegistrationRequest();

                        registrationRequest.setLogin(loginFeld.getValue());
                        registrationRequest.setPasswort(passwordField.getValue());
                        registrationRequest.setVorname(vornameFeld.getValue());
                        registrationRequest.setNachname(nachnameFeld.getValue());


                        RegistrationProcess.getInstance().createUser(registrationRequest, RegistrationWindow.this);
                    }
                } else {
                    Notification.show("Bitte eine valide E-Mail angeben!" , Notification.Type.ERROR_MESSAGE);
                }

            }
        });

        verticalLayout.addComponent(registrationButton);
        verticalLayout.setComponentAlignment(registrationButton , Alignment.MIDDLE_CENTER);

    }


}
