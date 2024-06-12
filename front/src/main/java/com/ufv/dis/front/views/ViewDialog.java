package com.ufv.dis.front.views;

import com.ufv.dis.front.exceptions.DataException;
import com.ufv.dis.front.services.DataService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.ufv.dis.front.models.Character;
import org.springframework.beans.factory.annotation.Autowired;

public class ViewDialog extends Div {

    DataService service;

    public ViewDialog(@Autowired DataService service) {
        this.service = service;
    }


    public Dialog generateDialog(Character character) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        int id = character.getId();

        dialog.setHeaderTitle("Character Detail");

        dialog.add(createDialogLayout(id));

        Button cancelButton = new Button("Close", e -> dialog.close());
        dialog.getFooter().add(cancelButton);

        return dialog;
    }

    private VerticalLayout createDialogLayout(int id) {

        Character character = null;
        try {
            character = service.getCharacter(id);
            Span name = new Span("Name: " + character.getName());
            Span ki = new Span("Ki: " + character.getKi());
            Span maxKi = new Span("Max Ki: " + character.getMaxKi());
            Span race = new Span("Race: " + character.getRace());
            Span description = new Span("Description: " + character.getDescription());
            Image image = new Image(character.getImage(), character.getName());
            image.addClassName("dialog-image");


            VerticalLayout editDialogLayout = new VerticalLayout(image, name, ki, maxKi, race, description);
            editDialogLayout.setPadding(false);
            editDialogLayout.setSpacing(false);
            editDialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            editDialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return editDialogLayout;
        } catch (DataException e) {
            throw new RuntimeException(e);
        }


    }
}
