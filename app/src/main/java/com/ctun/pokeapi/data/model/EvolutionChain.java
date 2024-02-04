package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

public class EvolutionChain {
    @SerializedName("chain")
    private Chain chain;

    public Chain getChain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }
}