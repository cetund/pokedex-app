package com.ctun.pokeapi.data.network;

import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.PokemonGetAll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApiClient {
    @GET("pokemon?limit=10")
    Call<PokemonGetAll> getPokemonList(@Query("offset") int offset);

    @GET("pokemon/{id}/")
    Call<PokemonDetail> getPokemonDetail(@Path("id") int id);
}
