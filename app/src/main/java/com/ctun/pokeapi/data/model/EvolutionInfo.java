package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

public class EvolutionInfo {
    @SerializedName("min_level")
    private int minLevel;

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }
}
