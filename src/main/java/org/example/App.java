package org.example;


import org.example.dao.impl.PokemonDaoImpl;
import org.example.pojo.Dresseur;
import org.example.pojo.Pokemon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws SQLException {
        System.out.println("");

        Dresseur monDresseur = new Dresseur("Sacha", 10, new ArrayList<>());


        int saisie = 0;
        while (saisie != 4){
            saisie = 0;
            System.out.println("Que souhaitez vous faire ? : ");

            System.out.println("1 : Capturer un pokemon");
            System.out.println("2 : Attendre");
            System.out.println("3 : Afficher pokemons");
            System.out.println("4 : Quitter");

            Scanner sc = new Scanner(System.in);
            try{
                saisie = sc.nextInt();
            }catch (InputMismatchException e){
                System.err.println("Rentrez un entier");
            }catch (Exception e){
                System.err.println("ERREUR");
            }

            PokemonDaoImpl pokemonDao = new PokemonDaoImpl();
            Pokemon monPokemon = null;

            switch (saisie){
                case 1 :
                    monPokemon = new Pokemon();
                    monDresseur.capturerPokemon(monPokemon);
                    pokemonDao.addPokemon(monPokemon);

                    System.out.println("Pokemon capturé !");
                    break;
                case 2 :
                    System.out.println("Vous attendez");
                    continue;
                case 3 :
                    System.out.println(monDresseur.getPokemonList());
                    break;
                case 4 :
                    System.out.println("Vous quittez");
                    break;
                default :
                    System.out.println("Choix invalide !");
                    System.out.println("Choississez une option");
            }


        }

    }
}