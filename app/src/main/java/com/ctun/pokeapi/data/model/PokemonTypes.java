package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

public class PokemonTypes {
    @SerializedName("type")
    PokemonType type;

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
}
