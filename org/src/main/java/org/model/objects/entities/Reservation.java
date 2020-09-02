package org.model.objects.entities;

import org.model.objects.dto.Auto;
import org.model.objects.dto.UserDTO;

public class Reservation {

    private int id;
    private Auto auto;
    private UserDTO user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
