package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PokemonDetail implements Serializable {

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("types")
    List<Types> types;

    @SerializedName("abilities")
    List<Abilities> abilities;

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
        return "PokemonDetail{" +
                "height=" + height +
                ", weight=" + weight +
                ", types=" + types +
                ", abilities=" + abilities +
                '}';
    }
}
