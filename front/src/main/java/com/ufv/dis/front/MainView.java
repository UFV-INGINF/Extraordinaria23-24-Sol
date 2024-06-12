package com.ufv.dis.front;

import com.ufv.dis.front.models.Character;
import com.ufv.dis.front.services.DataService;
import com.ufv.dis.front.views.CharactersGrid;
import com.ufv.dis.front.views.NewDialog;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed
     * bean.
     */
    ArrayList<Character> listCharacters = new ArrayList<>();

    public MainView(@Autowired DataService service) {
        CharactersGrid characters = new CharactersGrid(this.listCharacters, service);

        Button loadDB = new Button("Load Grid info", e -> {
            this.listCharacters = service.getCharacters();
            if (this.listCharacters == null) {
                this.listCharacters = new ArrayList<>();
            }
            characters.refreshGrid(this.listCharacters);
        });

        Button resetDB = new Button("Reset DB", e -> {
            service.resetDB();
            this.listCharacters = new ArrayList<>();
            characters.refreshGrid(this.listCharacters);
            //Tarda un poco, pero funciona
        });

        Button deleteDB = new Button("Delete DB", e -> {
            service.deleteDB();
            this.listCharacters = new ArrayList<>();
            characters.refreshGrid(this.listCharacters);
        });


        loadDB.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        resetDB.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteDB.addThemeVariants(ButtonVariant.LUMO_PRIMARY);




        HorizontalLayout buttons = new HorizontalLayout(loadDB, resetDB, deleteDB);

        addClassName("centered-content");

        add(buttons, characters);
    }

}
