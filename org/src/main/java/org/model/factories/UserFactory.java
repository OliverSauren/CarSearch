package org.model.factories;

import org.model.objects.dto.RegistrationRequest;
import org.objects.entities.Registration;

public class UserFactory {

    public static Registration createUser(RegistrationRequest registrationRequest) {

        Registration registration = new Registration();

        registration.setLogin(registrationRequest.getLogin());
        registration.setPasswort(registrationRequest.getPasswort());
        registration.setVorname(registrationRequest.getVorname());
        registration.setNachname(registrationRequest.getNachname());

        return registration;

    }

}
