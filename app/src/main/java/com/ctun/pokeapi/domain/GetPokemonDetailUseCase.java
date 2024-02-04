package com.ctun.pokeapi.domain;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.EvolutionChain;
import com.ctun.pokeapi.data.model.Species;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.TypeDetail;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

public class GetPokemonDetailUseCase {
    @Inject
    PokemonRepository repository;

    @Inject
    public GetPokemonDetailUseCase(){}

    public void getPokemonDetail(ApiServiceCallback<PokemonDetail> callback, int id){
        repository.getPokemonDetail(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(PokemonDetail pokemonDetail) {
                callback.onSuccess(pokemonDetail);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        }, id);
    }

    public void getPokemonSpecies(ApiServiceCallback<Species> callback, int id){
        repository.getPokemonSpecies(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(Species species) {

                callback.onSuccess(species);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        }, id);
    }

    public void getPokemonDamageRelation(ApiServiceCallback<TypeDetail> callback, int id){
        repository.getPokemonDamageRelationship(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(TypeDetail damageRelations) {
                callback.onSuccess(damageRelations);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        }, id);
    }

    public void getPokemonEvolutionChain(ApiServiceCallback<EvolutionChain> callback, int id){
        repository.getPokemonEvolutionChain(new ApiServiceCallback<EvolutionChain>() {
            @Override
            public void onSuccess(EvolutionChain evolutionChain) {
                callback.onSuccess(evolutionChain);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        }, id);
    }

}
