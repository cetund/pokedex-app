package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonList {

    @SerializedName("next")
    String next;
    @SerializedName("results")
    List<Results> resultsList;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Results> getResultsList() {
        return resultsList;
    }

    public void setResultsList(List<Results> resultsList) {
        this.resultsList = resultsList;
    }
}
