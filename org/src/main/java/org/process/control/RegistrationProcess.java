package org.process.control;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.gui.windows.ConfirmationWindow;
import org.model.dao.RegistrationDAO;
import org.model.factories.UserFactory;
import org.model.objects.dto.RegistrationRequest;
import org.model.objects.entities.Registration;

public class RegistrationProcess {

    public static RegistrationProcess registrationProcess = null;

    private RegistrationProcess() {

    }

    public static RegistrationProcess getInstance() {
        if (registrationProcess == null) {
            registrationProcess = new RegistrationProcess();
        }
        return registrationProcess;
    }

    public void createUser(RegistrationRequest registrationRequest, Window window) {

        Registration registration = UserFactory.createUser(registrationRequest);

        boolean result = RegistrationDAO.getInstance().addUser(registration);

        if (result == true) {

            window.close();
            UI.getCurrent().addWindow(new ConfirmationWindow("Registration erfolgreich! UserID: " + registration.getId()));

        } else {

            Notification.show("Something went wrong..." , Notification.Type.ERROR_MESSAGE);

            //TODO Fehlerhandling

        }

    }

}
