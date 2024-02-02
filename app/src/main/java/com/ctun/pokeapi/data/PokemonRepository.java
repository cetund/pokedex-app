package com.ctun.pokeapi.data;

import android.util.Log;

import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.data.network.PokemonApiClient;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository {
    @Inject
    PokemonApiClient apiClient;

    @Inject
    public PokemonRepository(){
    }

    public void getAll(ApiServiceCallback<PokemonList> callback, int offset) {
        Call<PokemonList> response = apiClient.getPokemonList(offset);

        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void searchPokemon(ApiServiceCallback<Results> callback, String name){
        Call<Results> response = apiClient.searchPokemon(name);
        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getPokemonDetail(ApiServiceCallback<PokemonDetail> callback, int id){
        Call<PokemonDetail> response = apiClient.getPokemonDetail(id);
        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }
}
