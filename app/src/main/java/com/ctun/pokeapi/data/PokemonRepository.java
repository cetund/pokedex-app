package com.ctun.pokeapi.data;

import com.ctun.pokeapi.data.model.PokemonGetAll;
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

    public void getAll(ApiServiceCallback<PokemonGetAll> callback, int offset) {
        Call<PokemonGetAll> response = apiClient.getPokemonList(offset);

        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PokemonGetAll> call, Response<PokemonGetAll> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PokemonGetAll> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
