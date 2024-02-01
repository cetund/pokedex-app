package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonDetail {

    @SerializedName("types")
    List<PokemonTypes> types;

}
