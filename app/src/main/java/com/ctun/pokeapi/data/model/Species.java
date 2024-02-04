package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Species {
    @SerializedName("flavor_text_entries")
    List<FlavorText> flavorTextEntries;

    @SerializedName("evolution_chain")
    Results evolutionChain;

    public List<FlavorText> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    public void setFlavorTextEntries(List<FlavorText> flavorTextEntries) {
        this.flavorTextEntries = flavorTextEntries;
    }

    public Results getEvolutionChain() {
        return evolutionChain;
    }

    public void setEvolutionChain(Results evolutionChain) {
        this.evolutionChain = evolutionChain;
    }
}
