package com.ufv.dis.front.views;

import com.ufv.dis.front.services.DataService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.ufv.dis.front.models.Character;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CharactersGrid extends VerticalLayout {


    Grid<Character> grid = new Grid<>(Character.class, false);
    Button newCharacter;

    public CharactersGrid(ArrayList<Character> listCharacters, DataService dataService) {

        grid.addColumn(Character::getName).setHeader("Name");
        grid.addColumn(Character::getKi).setHeader("Ki");
        grid.addColumn(Character::getMaxKi).setHeader("Max Ki");
        grid.addColumn(Character::getRace).setHeader("Race");

        if (listCharacters == null) {
            listCharacters = new ArrayList<>();
        }
        grid.setItems(listCharacters);
        grid.addClassName("my-grid");
        grid.addItemDoubleClickListener(e -> {
            Character character = e.getItem();
            ViewDialog viewDialog = new ViewDialog(dataService);
            viewDialog.generateDialog(character).open();
        });

        grid.addColumn(new NativeButtonRenderer<>("Edit", clickedItem -> {
            EditDialog editDialog = new EditDialog(dataService);
            Dialog dialog = editDialog.generateEditDialog(clickedItem);
            dialog.addOpenedChangeListener(event -> {
                if (!event.isOpened()) {
                    this.refreshGrid(dataService.getCharacters());
                }
            });
            dialog.open();
        })).setHeader("Edit");

        newCharacter = new Button("New Character", e -> {
            Dialog dialog = new NewDialog(dataService).generatenewDialog();
            dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);
            dialog.addOpenedChangeListener(event -> {
                if (!event.isOpened()) {
                    this.refreshGrid(dataService.getCharacters());
                }
            });
            dialog.open();
        });
        newCharacter.setEnabled(false);
        add(grid, newCharacter);


    }

    public void refreshGrid(ArrayList<Character> listCharacters) {
        grid.setItems(listCharacters);
        grid.getDataProvider().refreshAll();
        if(listCharacters.size() > 0) {
            newCharacter.setEnabled(true);
        } else {
            newCharacter.setEnabled(false);
        }
    }


}
