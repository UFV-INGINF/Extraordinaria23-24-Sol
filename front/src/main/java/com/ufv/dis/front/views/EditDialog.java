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

public class EditDialog {

    TextField name = new TextField("Name: ");
    TextField ki = new TextField("Ki: ");
    TextField maxKi = new TextField("Max Ki: ");
    TextField race = new TextField("Race: ");
    TextArea description = new TextArea();
    DataService service;

    public EditDialog(@Autowired DataService service) {
        this.service = service;
    }

    public Dialog generateEditDialog(Character character) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        int id = character.getId();

        dialog.setHeaderTitle("Character Detail");

        dialog.add(createEditDialogLayout(id));

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        Button saveButton = new Button("Save", e -> {
            Character editingCharacter = new Character(id,name.getValue(), ki.getValue(), maxKi.getValue(), race.getValue(), character.getImage(), description.getValue());
            this.service.editCharacter(id, editingCharacter);
            dialog.close();
        });
        dialog.getFooter().add(saveButton);

        return dialog;
    }



    private VerticalLayout createEditDialogLayout(int id) {

        Character character = null;
        try {
            character = this.service.getCharacter(id);

            name.setValue(character.getName());
            ki.setValue(character.getKi());
            maxKi.setValue(character.getMaxKi());
            race.setValue(character.getRace());
            description.setLabel("Description");
            description.setMaxLength(2000);
            description.setValueChangeMode(ValueChangeMode.EAGER);
            description.setValue(character.getDescription());

            Image image = new Image(character.getImage(), character.getName());
            image.addClassName("dialog-image");

            VerticalLayout dialogLayout = new VerticalLayout(image, name, ki, maxKi, race, description);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
        } catch (DataException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }
}
