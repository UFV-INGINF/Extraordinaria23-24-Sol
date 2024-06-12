package com.ufv.dis.api.services;

import com.ufv.dis.api.models.APIResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

@Service
public class DragonBallAPIService {

    private final static String API_URL = "https://dragonball-api.com/api/";

    public APIResponse getDBAPIInfo(String resource) {
        // With this method, I can get all the characters from the Dragon Ball API
        String url = String.format("%scharacters?limit=60", API_URL);
        String next = null;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET() // GET is default
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            APIResponse responseObj = gson.fromJson(response.body(), APIResponse.class);

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
