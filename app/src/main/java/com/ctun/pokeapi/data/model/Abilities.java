package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class Abilities implements Serializable {
    @SerializedName("ability")
    PokemonAbility ability;

    public PokemonAbility getAbility() {
        return ability;
    }

    public void setAbility(PokemonAbility ability) {
        this.ability = ability;
    }
}
