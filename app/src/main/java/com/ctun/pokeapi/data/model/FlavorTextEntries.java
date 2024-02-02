package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlavorTextEntries {
    @SerializedName("flavor_text_entries")
    List<FlavorText> flavorTextEntries;

    public List<FlavorText> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    public void setFlavorTextEntries(List<FlavorText> flavorTextEntries) {
        this.flavorTextEntries = flavorTextEntries;
    }
}
