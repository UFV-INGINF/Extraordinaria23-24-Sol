package com.ufv.dis.api.controllers;

import com.ufv.dis.api.services.LocalDragonBallAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import com.ufv.dis.api.models.Character;


@RestController()
@RequestMapping("/api/")
public class LocalCharactersController {

    @Autowired
    private LocalDragonBallAPIService localService;


    @GetMapping(value = "/db", produces = "application/json")
    public ResponseEntity<ArrayList<Character>> readLocalDB() {
        // Controller Method to read the local database
        return ResponseEntity.ok(localService.readLocalDB());
    }

    @GetMapping(value = "/db/{id}", produces = "application/json")
    public ResponseEntity<Character> readCharacter(@PathVariable int  id) {
        // Controller Method to read a character from the local database
        return ResponseEntity.ok(localService.readCharacter(id));
    }

    @DeleteMapping(value = "/db")
    public ResponseEntity<Boolean> deleteLocalDB() {
        // Controller Method to delete the local database
        return ResponseEntity.ok(localService.deleteLocalDB());
    }

    @PostMapping(value = "/db")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        // Controller Method to create a character in the local database
        return ResponseEntity.ok(localService.createCharacter(character));
    }

    @PutMapping(value = "/db/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable int id, @RequestBody Character character) {
        // Controller Method to update a character in the local database
        return ResponseEntity.ok(localService.updateCharacter(id, character));
    }

    //Add POST and PUT methods here to create and update characters in the local database


}
