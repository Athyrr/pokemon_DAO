package org.example.pojo;

import java.util.*;

public class Pokemon {

    //Attribut
    private String prenom;
    private List<Capacite> capacites;
    private Integer niveau;
    private Boolean isShiny;
    private String nature;


    //Constructeur
    public Pokemon(String prenom, List<Capacite> capacites, Integer niveau, Boolean isShiny, String nature) {
        this.prenom = prenom;
        this.capacites = capacites;
        this.niveau = niveau;
        this.isShiny = isShiny;
        this.nature = nature;
    }

    public Pokemon() {
        this.prenom = "unPrenom";
        this.nature = "Gentil";
        this.niveau = 15;
        this.isShiny = false;
        this.capacites = new ArrayList<>();
    }

    public void addCapacity(Capacite capacite) {
        if (this.capacites != null && this.capacites.size() < 4) {
            this.capacites.add(capacite);
        } else if (this.capacites != null) {
            this.menuForbiddenCapacitie(capacite);
        }
    }

    private void menuForbiddenCapacitie(Capacite capacite) {
        System.out.println("Deja 4 capacités");
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1 : Oublier une capacité");
        System.out.println("2 : Ne rien faire");
        System.out.print("Entrez votre choix : ");
        int saisie = 0;

        try(Scanner sc = new Scanner(System.in)){
            saisie = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("MisInput");
        }catch (Exception e){
            System.out.println("Erreur exception");
        }

        switch (saisie) {
            case 1:
                Random random = new Random();
                int randomInt = random.nextInt(capacites.size()-1);
                this.capacites.remove(randomInt); //remove(index)
                System.out.println("La capacité "+(randomInt+1)+" a été oubliée");
                break;
            case 2:
                System.out.println("Rien n'a été oublié");
                break;
            default:
                System.err.println("Choix incorrect");
        }
    }

        //Accesseur et mutateur : getter and setter
        public String getPrenom () {
            return prenom;
        }

        public void setPrenom (String prenom){
            this.prenom = prenom;
        }


        public Integer getNiveau () {
            return niveau;
        }

        public void setNiveau (Integer niveau){
            this.niveau = niveau;
        }

        public Boolean getShiny () {
            return isShiny;
        }

        public void setShiny (Boolean shiny){
            isShiny = shiny;
        }

        public String getNature () {
            return nature;
        }

        public void setNature (String nature){
            this.nature = nature;
        }


        @Override
        public String toString () {
            return prenom.toUpperCase() + " | " + capacites + " | "
                    + nature + " | " + niveau+ "\n";
        }
}

