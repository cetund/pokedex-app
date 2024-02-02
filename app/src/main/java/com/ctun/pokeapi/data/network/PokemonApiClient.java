package com.ctun.pokeapi.data.network;

import com.ctun.pokeapi.data.model.DamageRelations;
import com.ctun.pokeapi.data.model.FlavorTextEntries;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.data.model.TypeDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApiClient {
    @GET("pokemon?limit=10")
    Call<PokemonList> getPokemonList(@Query("offset") int offset);

    @GET("pokemon/{id}/")
    Call<PokemonDetail> getPokemonDetail(@Path("id") int id);

    @GET("pokemon/{name}/")
    Call<Results> searchPokemon(@Path("name") String name);
    @GET("pokemon-species/{id}")
    Call<FlavorTextEntries> getPokemonSpecie(@Path("id") int id);
    @GET("type/{id}")
    Call<TypeDetail> getDamageRelations(@Path("id") int id);
}
