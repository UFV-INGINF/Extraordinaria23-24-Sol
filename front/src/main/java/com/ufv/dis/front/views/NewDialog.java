package com.ufv.dis.front.views;

import com.ufv.dis.front.exceptions.DataException;
import com.ufv.dis.front.models.Character;
import com.ufv.dis.front.services.DataService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.beans.factory.annotation.Autowired;

public class NewDialog {
    TextField name = new TextField("Name: ");
    TextField ki = new TextField("Ki: ");
    TextField maxKi = new TextField("Max Ki: ");
    TextField race = new TextField("Race: ");
    TextField image = new TextField("Image URL: ");
    TextArea description = new TextArea();

    DataService service;

    public NewDialog(@Autowired DataService service) {
        this.service = service;

    }

    public Dialog generatenewDialog() {

        this.clearFields();
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);

        dialog.setHeaderTitle("Character Detail");

        dialog.add(createNewDialogLayout());

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        Button saveButton = new Button("Save", e -> {
            Character newCharacter = new Character(0, name.getValue(), ki.getValue(), maxKi.getValue(), race.getValue(), image.getValue(), description.getValue());
            this.service.createCharacter(newCharacter);
            dialog.close();

        });
        dialog.getFooter().add(saveButton);

        return dialog;
    }


    private VerticalLayout createNewDialogLayout() {
        description.setLabel("Description");
        description.setMaxLength(2000);
        description.setValueChangeMode(ValueChangeMode.EAGER);
        VerticalLayout dialogLayout = new VerticalLayout(name, ki, maxKi, race, image, description);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        return dialogLayout;

    }

    private void clearFields() {
        name.setValue("");
        ki.setValue("");
        maxKi.setValue("");
        race.setValue("");
        image.setValue("");
        description.setValue("");

    }
}
