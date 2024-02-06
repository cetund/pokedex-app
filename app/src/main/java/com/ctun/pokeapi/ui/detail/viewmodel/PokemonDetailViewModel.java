package com.ctun.pokeapi.ui.detail.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.EvolutionChain;
import com.ctun.pokeapi.data.model.Species;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.TypeDetail;
import com.ctun.pokeapi.domain.GetPokemonDetailUseCase;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PokemonDetailViewModel extends ViewModel {
    @Inject
    GetPokemonDetailUseCase getPokemonDetailUseCase;

    private LiveData<PokemonDetail> detail;
    private LiveData<Boolean> isLoading;
    private LiveData<Boolean> isRequestFailure;

    private LiveData<Species> pokemonSpecies;

    private LiveData<TypeDetail> damageRelations;

    private LiveData<EvolutionChain> evolutionChain;

    @Inject
    public PokemonDetailViewModel(PokemonRepository repository) {

        detail = repository.getPokemonDetail();
        isLoading = repository.getIsLoading();
        isRequestFailure = repository.getIsRequestFailure();
        pokemonSpecies = repository.getSpecies();
        damageRelations = repository.getTypeDetail();
        evolutionChain = repository.getEvolutionChain();
    }

    public void getPokemonDetail(int id) {
       detail = getPokemonDetailUseCase.getPokemonDetail(id);
    }

    public void getPokemonSpecies(int id) {
        pokemonSpecies = getPokemonDetailUseCase.getPokemonSpecies(id);
    }
    public void getPokemonDamageRelation(int id) {
        damageRelations = getPokemonDetailUseCase.getPokemonDamageRelation(id);
    }

    public void getPokemonEvolution(int id) {
       evolutionChain = getPokemonDetailUseCase.getPokemonEvolutionChain(id);
    }


    public LiveData<PokemonDetail> getDetail(){
        return detail;
    }

    public LiveData<Boolean> getIsLoading(){
        return isLoading;
    }

    public LiveData<Species> getPokemonSpecies (){
        return pokemonSpecies;
    }

    public LiveData<Boolean> getIsRequestFailure() {
        return isRequestFailure;
    }
    public LiveData<TypeDetail> getDamageRelations(){
        return damageRelations;
    }

    public LiveData<EvolutionChain> getEvolutionChain(){
        return evolutionChain;
    }
}
