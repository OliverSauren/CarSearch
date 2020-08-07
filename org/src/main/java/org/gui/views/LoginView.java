package org.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

    public void setUp() {

        this.setSizeFull();

        final TextField userLogin = new TextField();
        userLogin.setCaption("Login");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort");

    }

}
