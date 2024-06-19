package com.ufv.dis.front.services;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ufv.dis.front.exceptions.DataException;
import com.ufv.dis.front.models.Character;

import org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataService implements Serializable {

    private final static String API_URL = "http://backend:8081/api";

    public ArrayList<Character> getCharacters() {
        String url = String.format("%s/db", API_URL);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET() // GET is default
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Character>>() {}.getType();
            ArrayList<Character> responseObj = gson.fromJson(response.body(), listType);

            return responseObj;
        } catch (
                URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e ) {
            throw new RuntimeException(e);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public Character getCharacter(int id) throws DataException {
        if (id == -1) {
            throw new DataException("Character not found");
        } else {
            String url = String.format("%s/db/%d", API_URL, id);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = null;
            try {
                request = HttpRequest.newBuilder()
                        .uri(new URI(url))
                        .GET() // GET is default
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                Gson gson = new Gson();
                Character responseObj = gson.fromJson(response.body(), Character.class);

                return responseObj;
            } catch (
                    URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteDB() {
        String url = String.format("%s/db", API_URL);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .DELETE() // GET is default
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (
                URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Boolean> resetDB() {
        String url = String.format("%s/reset", API_URL);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .DELETE() // GET is default
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.ok(response.statusCode() == 200);
        } catch (
                URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Character createCharacter(Character character) {
        String url = String.format("%s/db", API_URL);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(character);
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Character responseObj = gson.fromJson(response.body(), Character.class);

            return responseObj;
        } catch (
                URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Character editCharacter(int id, Character character) {
        String url = String.format("%s/db/%d", API_URL, id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(character);
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Character responseObj = gson.fromJson(response.body(), Character.class);

            return responseObj;
        } catch (
                URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
