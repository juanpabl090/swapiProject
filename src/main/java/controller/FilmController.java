package controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Film;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FilmController {
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    public Film getFilm(int numberFile) throws IOException {
        String encodeBusqueda = String.format("https://swapi.dev/api/films/%s", numberFile);
        URI direccion = URI.create(encodeBusqueda);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("No encontre la pelicula: " + e.getMessage());
        }
        assert response != null;
        return new Gson().fromJson(response.body(), Film.class);
    }

    public void postFile(Film response) throws IOException {
        FileWriter escritura = new FileWriter("Films.txt");
        escritura.write(gson.toJson(response));
        escritura.close();
    }
}