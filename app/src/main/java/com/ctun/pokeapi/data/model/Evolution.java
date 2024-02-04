package com.ctun.pokeapi.data.model;

public class Evolution {
    private int idPokemon;
    private String name;
    private int level;

    public Evolution(int idPokemon, String name, int level){
        this.idPokemon = idPokemon;
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }
}
