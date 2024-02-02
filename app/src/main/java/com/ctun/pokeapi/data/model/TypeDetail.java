package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

public class TypeDetail {
    @SerializedName("damage_relations")
    private DamageRelations damageRelations;

    public DamageRelations getDamageRelations() {
        return damageRelations;
    }

    public void setDamageRelations(DamageRelations damageRelations) {
        this.damageRelations = damageRelations;
    }
}
