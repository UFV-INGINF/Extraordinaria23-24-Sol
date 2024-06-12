package com.ufv.dis.api.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.ufv.dis.api.models.Character;

@Service
public class LocalDragonBallAPIService {

    public ArrayList<Character> readLocalDB() {
        // Read the local database
        Gson gson = new Gson();
        ArrayList<Character> characters = new ArrayList<>();
        try {
            Type type = new TypeToken<ArrayList<Character>>() {
            }.getType();
            Reader reader = new FileReader("db.json");
            characters = gson.fromJson(reader, type);
            return characters;
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo db.json");
            // File does not exist. Returning empty list.
            return new ArrayList<Character>();
        }
    }

    public Character readCharacter(int id) {
        // Read the local database
        ArrayList<Character> characters = this.readLocalDB();
        for (Character character : characters) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }


    public Boolean deleteLocalDB() {
        // Delete the local database
        File file = new File("db.json");
        if (file.delete()) {
            System.out.println("El archivo db.json ha sido borrado");
            return true;
        } else {
            System.out.println("El archivo db.json no se ha podido borrar");
            return false;
        }
    }

    public Character createCharacter(Character character) {
        // Create a character in the local database
        ArrayList<Character> characters = this.readLocalDB();
        character.setId(this.getLastId() + 1);
        characters.add(character);
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter("db.json");
            gson.toJson(characters, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return character;
    }

    public Character updateCharacter(int id, Character character) {
        // Update a character in the local database
        ArrayList<Character> characters = this.readLocalDB();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getId() == id) {
                characters.set(i, character);
            }
        }
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter("db.json");
            gson.toJson(characters, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return character;
    }

    private int getLastId() {
        ArrayList<Character> characters = this.readLocalDB();
        int lastId = characters.get(characters.size() -1 ).getId();
        return lastId;
    }

}
