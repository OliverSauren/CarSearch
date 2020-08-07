package org.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.model.objects.dto.Auto;

public class MainView extends VerticalLayout implements View {

    //TODO Prüfen ob User bereits eingeloggt oder nicht

    //TODO Top-Panel hinzufügen

    //TODO Grid erstellen
    Grid<Auto> grid = new Grid<>(Auto.class);

}
