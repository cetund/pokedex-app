package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DamageRelations {
    @SerializedName("double_damage_from")
    private List<Results> doubleDamageFrom;

    public List<Results> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public void setDoubleDamageFrom(List<Results> doubleDamageFrom) {
        this.doubleDamageFrom = doubleDamageFrom;
    }
}
