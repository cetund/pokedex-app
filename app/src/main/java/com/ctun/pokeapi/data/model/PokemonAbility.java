package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PokemonAbility implements Serializable {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
