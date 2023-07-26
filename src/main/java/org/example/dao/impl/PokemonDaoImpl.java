package org.example.dao.impl;

import org.example.dao.PokemonDao;
import org.example.database.DatabaseConnection;
import org.example.pojo.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDaoImpl implements PokemonDao {

    Connection connection;
    public PokemonDaoImpl(){

        connection = DatabaseConnection.getConnection(); // Connexion à la base de données
        try{
            createTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException{
            String query = "CREATE TABLE IF NOT EXISTS pokemon(prenom VARCHAR(250)," +
                    "niveau INT(50)," +
                    "isShiny BOOLEAN," +
                    "nature VARCHAR(250)" +
                    ");";

            Statement statement = connection.createStatement();
            statement.execute(query);
    }

    @Override
    public void addPokemon(Pokemon pokemon) throws SQLException {
        //Définition d'un query(requête SQL)
        String query = "insert into pokemon (prenom,niveau,isShiny,nature) VALUES"+
                "(?,?,?,?)";

        // Création d'un PreparedStatement pour une requête paramétrée
        PreparedStatement ps = connection.prepareStatement(query);

        //Spécifier la valeur du paramètre '?' avant d'exécuter la requête
        //1 étant l'index du paramètre
        ps.setString(1, pokemon.getPrenom());
        ps.setInt(2,pokemon.getNiveau());
        ps.setBoolean(3,pokemon.getShiny());
        ps.setString(4,pokemon.getNature());

        //Exécution d'une requête paramétrée avec PreparedStatement
        //executeUpdate pour les query INSERT, UPDATE or DELETE
        ps.executeUpdate();
    }


    @Override
    public List<Pokemon> getAllPokemon() throws SQLException {

        //Créer et exécuter une requête SELECT pour récupérer les pokemons
        String query = "select * from pokemon";
        PreparedStatement ps = connection.prepareStatement(query);

        //ResultSet car executeQuery retourne un ResultSet
        ResultSet rs = ps.executeQuery();

        //Parcourir les résultats avec une boucle while :
        //Ajouter les pokemons dans une nouvelle liste
        List<Pokemon>pokemonList = new ArrayList<>();

        // rs.next() permet de vérifier s'il y a une ligne supplémentaire
        // dans le résultat. Si c'est le cas,
        // rs.next() déplace le curseur sur la prochaine ligne
        while (rs.next()){
            Pokemon pokemon = new Pokemon();
            //extraire les valeurs des colonnes pour cette ligne
            // à l'aide des méthodes appropriées (getInt(), getString(), etc.)
            pokemon.setPrenom(rs.getString("prenom"));
            pokemon.setNiveau(rs.getInt("niveau"));
            pokemon.setShiny(rs.getBoolean("isShiny"));
            pokemon.setNature(rs.getString("nature"));

            pokemonList.add(pokemon);
        }
        return pokemonList;
    }

    @Override
    public Pokemon  getPokemonByPrenom(String prenom) throws SQLException {
        String query = "select * from pokemon where prenom = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,prenom);
        ResultSet rs = ps.executeQuery();

        Pokemon pokemon = new Pokemon();

        boolean check = false;
        while (rs.next()){

            check = true;
            pokemon.setPrenom(rs.getString("prenom"));
            pokemon.setNature(rs.getString("nature"));
            pokemon.setShiny(rs.getBoolean("isShiny"));
            pokemon.setNiveau(rs.getInt("niveau"));

        }
        if(check){ //check == true
            return pokemon;
        }else {
            return null;
        }
    }

    @Override
    public void deletePokemon(String prenom) throws SQLException{
        String query = "delete from pokemon where prenom = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,prenom);
        ps.executeUpdate();


    }

    @Override
    public void update(Pokemon pokemon, String prenom) throws SQLException{
        String query = "update pokemon set prenom = ?, niveau = ?, " +
                "isShiny = ?, nature = ? where prenom = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, pokemon.getPrenom());
        ps.setInt(2,pokemon.getNiveau());
        ps.setBoolean(3,pokemon.getShiny());
        ps.setString(4,pokemon.getNature());
        ps.setString(5,prenom);

        ps.executeUpdate();
    }



}
