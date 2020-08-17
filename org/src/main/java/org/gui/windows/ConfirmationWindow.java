package org.gui.windows;

import com.vaadin.ui.*;

public class ConfirmationWindow extends Window {

    public ConfirmationWindow(String text) {
        super("Confirmation");
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label(text));
        verticalLayout.setMargin(true);
        setContent(verticalLayout);

        Button button = new Button("OK");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });

        verticalLayout.addComponent(button);
        verticalLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

    }

}
