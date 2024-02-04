package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chain {
    @SerializedName("evolution_details")
    private List<EvolutionDetails> evolutionDetails;

    @SerializedName("evolves_to")
    @Expose
    private List<EvolvesTo> evolvesTo;
    @SerializedName("is_baby")
    @Expose
    private boolean isBaby;
    @SerializedName("species")
    @Expose
    private Results species;

    public List<EvolvesTo> getEvolvesTo() {
        return evolvesTo;
    }

    public void setEvolvesTo(List<EvolvesTo> evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

    public boolean isBaby() {
        return isBaby;
    }

    public void setIsBaby(boolean isBaby) {
        this.isBaby = isBaby;
    }

    public Results getSpecies() {
        return species;
    }

    public void setSpecies(Results species) {
        this.species = species;
    }

    public List<EvolutionDetails> getEvolutionDetails() {
        return evolutionDetails;
    }

    public void setEvolutionDetails(List<EvolutionDetails> evolutionDetails) {
        this.evolutionDetails = evolutionDetails;
    }
}
