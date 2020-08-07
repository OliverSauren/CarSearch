package org.gui.windows;

import com.vaadin.ui.*;
import org.model.objects.dto.Auto;

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

        final TextField emailFeld = new TextField();
        emailFeld.setCaption("E-Mail: ");
        //emailFeld.setValue("example@example.com");
        verticalLayout.addComponent(emailFeld);


        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");
        verticalLayout.addComponent(passwordField);

        setClosable(true);



    }

}
