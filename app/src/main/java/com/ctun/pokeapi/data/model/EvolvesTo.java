package com.ctun.pokeapi.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvolvesTo {

    @SerializedName("evolution_details")
    private List<EvolutionInfo> evolutionDetails;

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

    public boolean isIsBaby() {
        return isBaby;
    }

    public void setIsBaby(boolean isBaby) {
        this.isBaby = isBaby;
    }

    public boolean isBaby() {
        return isBaby;
    }

    public Results getSpecies() {
        return species;
    }

    public void setSpecies(Results species) {
        this.species = species;
    }

    public List<EvolutionInfo> getEvolutionDetails() {
        return evolutionDetails;
    }

    public void setEvolutionDetails(List<EvolutionInfo> evolutionDetails) {
        this.evolutionDetails = evolutionDetails;
    }
}