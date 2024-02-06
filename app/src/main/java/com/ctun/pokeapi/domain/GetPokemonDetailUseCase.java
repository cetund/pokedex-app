package com.ctun.pokeapi.domain;

import androidx.lifecycle.LiveData;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.EvolutionChain;
import com.ctun.pokeapi.data.model.Species;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.TypeDetail;

import javax.inject.Inject;

public class GetPokemonDetailUseCase {
    @Inject
    PokemonRepository repository;

    @Inject
    public GetPokemonDetailUseCase(){}

    public LiveData<PokemonDetail> getPokemonDetail(int id){
        return repository.getPokemonDetail(id);
    }

    public LiveData<Species> getPokemonSpecies(int id){
        return repository.getPokemonSpecies(id);
    }

    public LiveData<TypeDetail> getPokemonDamageRelation(int id){
        return repository.getPokemonDamageRelationship(id);
    }

    public LiveData<EvolutionChain> getPokemonEvolutionChain(int id){
       return repository.getPokemonEvolutionChain(id);
    }

}
