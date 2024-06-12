package com.ufv.dis.api.controllers;

import com.google.gson.Gson;
import com.ufv.dis.api.models.APIResponse;
import com.ufv.dis.api.services.DragonBallAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController()
@RequestMapping("/api/remote")
public class RemoteCharactersController {

    @GetMapping(value = "/characters", produces = "application/json")
    // Controller Method to get all the characters from the Dragon Ball API

    // Añadir un botón de reset database en la página principal.
    public ResponseEntity<APIResponse> getCharacters(@Autowired DragonBallAPIService dbService) {
        APIResponse respuesta = dbService.getDBAPIInfo("characters");

        //Now, if the local file does not exist, we will create one
        //We will use the Gson library to convert the object to a JSON string
        Gson gson = new Gson();

        File file = new File("db.json");

        // Comprobar si el archivo existe
//        if (!file.exists()) {
        try {
            // Si no existe, crear un nuevo archivo
            file.createNewFile();

            // Escribir la cadena JSON en el archivo
            BufferedWriter writer = new BufferedWriter(new FileWriter("db.json"));
            gson.toJson(respuesta.getItems(), writer);
            writer.flush();

            // Cerrar el escritor
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        } else {
//        System.out.println("El archivo ya existe");
//        }


        return ResponseEntity.ok(respuesta);
    }


    //Añadir botón que permita borrar la base de datos, además del reset, que la deja como estaba trayendo de nuevo los datos desde la API original.
    //Ellos tendrían que implementar el método de la API que llama al método para eliminar el fichero que contiene la base de datos

}
