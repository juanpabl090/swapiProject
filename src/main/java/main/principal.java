package main;

import controller.FilmController;
import models.Film;

import java.io.IOException;
import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        FilmController film = new FilmController();
        Scanner scaner = new Scanner(System.in);
        int numberFilm;

        try {
            System.out.println("Ingresa el numero de la pelicula que quieres solicitar");
            numberFilm = scaner.nextInt();
            Film response = film.getFilm(numberFilm);
            System.out.println(response);
            film.postFile(response);
            System.out.println("El archivo fue creado con exito");
        } catch (NumberFormatException e) {
            System.out.println("Numero no encontrado: " + e.getMessage());
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicacion");
        }
    }
}