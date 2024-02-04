package com.ctun.pokeapi.ui.detail.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    private MutableLiveData<PokemonDetail> detail;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Boolean> isRequestFailure;

    private MutableLiveData<Species> pokemonSpecies;

    private MutableLiveData<TypeDetail> damageRelations;

    private MutableLiveData<EvolutionChain> evolutionChain;

    @Inject
    public PokemonDetailViewModel() {

        detail = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        isRequestFailure = new MutableLiveData<>();
        pokemonSpecies = new MutableLiveData<>();
        damageRelations = new MutableLiveData<>();
        evolutionChain = new MutableLiveData<>();
    }

    public void getPokemonDetail(int id) {
        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        getPokemonDetailUseCase.getPokemonDetail(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(PokemonDetail pokemonDetail) {
                isLoading.postValue(false);

                if (pokemonDetail != null) {
                    detail.postValue(pokemonDetail);
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(String error) {
                Log.d("ViewModelFailure", "" + error);
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        }, id);
    }

    public void getPokemonSpecies(int id) {
        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        getPokemonDetailUseCase.getPokemonSpecies(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(Species species) {
                isLoading.postValue(false);

                if (species != null) {
                    pokemonSpecies.postValue(species);
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(String error) {
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        }, id);
    }
    public void getPokemonDamageRelation(int id) {
        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        getPokemonDetailUseCase.getPokemonDamageRelation(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(TypeDetail response) {
                isLoading.postValue(false);

                if (response != null) {
                    damageRelations.postValue(response);
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(String error) {
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        }, id);
    }

    public void getPokemonEvolution(int id) {
        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        getPokemonDetailUseCase.getPokemonEvolutionChain(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(EvolutionChain response) {
                isLoading.postValue(false);

                if (response != null) {
                    evolutionChain.postValue(response);
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(String error) {
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        }, id);
    }


    public MutableLiveData<PokemonDetail> getDetail(){
        return detail;
    }

    public MutableLiveData<Boolean> getIsLoading(){
        return isLoading;
    }

    public MutableLiveData<Species> getPokemonSpecies (){
        return pokemonSpecies;
    }

    public MutableLiveData<Boolean> getIsRequestFailure() {
        return isRequestFailure;
    }
    public MutableLiveData<TypeDetail> getDamageRelations(){
        return damageRelations;
    }

    public MutableLiveData<EvolutionChain> getEvolutionChain(){
        return evolutionChain;
    }
}
