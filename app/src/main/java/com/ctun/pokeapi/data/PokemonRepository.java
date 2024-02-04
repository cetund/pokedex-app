package com.ctun.pokeapi.data;

import android.util.Log;

import com.ctun.pokeapi.data.model.EvolutionChain;
import com.ctun.pokeapi.data.model.Species;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.data.model.TypeDetail;
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

    public void getPokemonSpecies(ApiServiceCallback<Species> callback, int id){
        Call<Species> flavorTextEntriesCall = apiClient.getPokemonSpecie(id);

        flavorTextEntriesCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                callback.onFailure(t.getMessage());
            }
        });

    }

    public void getPokemonDamageRelationship(ApiServiceCallback<TypeDetail> callback, int id){
        Call<TypeDetail> damageRelationsCall = apiClient.getDamageRelations(id);

        damageRelationsCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<TypeDetail> call, Response<TypeDetail> response) {
                Log.d("doubledamage","" + response.body().getDamageRelations().getDoubleDamageFrom().size());
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TypeDetail> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                callback.onFailure(t.getMessage());
            }
        });

    }

    public void getPokemonEvolutionChain(ApiServiceCallback<EvolutionChain> callback, int id){
        Call<EvolutionChain> evolutionChainCall = apiClient.getEvolutionChain(id);
        evolutionChainCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<EvolutionChain> call, Response<EvolutionChain> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EvolutionChain> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                callback.onFailure(t.getMessage());
            }
        });
    }
}
