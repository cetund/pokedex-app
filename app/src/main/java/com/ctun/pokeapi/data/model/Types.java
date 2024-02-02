package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Types implements Serializable {
    @SerializedName("type")
    PokemonType type;

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
}
