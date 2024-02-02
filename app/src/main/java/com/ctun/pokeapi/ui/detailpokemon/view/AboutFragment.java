package com.ctun.pokeapi.ui.detailpokemon.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctun.pokeapi.data.model.Abilities;
import com.ctun.pokeapi.data.model.FlavorText;
import com.ctun.pokeapi.data.model.FlavorTextEntries;
import com.ctun.pokeapi.data.model.PokemonAbility;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.databinding.FragmentAboutBinding;
import com.ctun.pokeapi.ui.detailpokemon.viewmodel.PokemonDetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;
    private PokemonDetailViewModel viewModel;
    private static final String ID_POKEMON = "ID_POKEMON";
    private static final String POKEMON_DETAIL = "POKEMON_DETAIL";
    private int idPokemon = 0;
    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(int idPokemon) {
        AboutFragment fragment = new AboutFragment();

        Bundle args = new Bundle();
        args.putInt(ID_POKEMON, idPokemon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPokemon = getArguments().getInt(ID_POKEMON);
        }

        viewModel = new ViewModelProvider(requireActivity()).get(PokemonDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment


        viewModel.getDetail().observe(getActivity(), pokemonDetail -> {
            binding.tvWeight.setText("" + (pokemonDetail.getWeight()/10f) + " kilogramos");
            binding.tvHeight.setText("" + (pokemonDetail.getHeight()/10f) + " metros");

            String habilidades = "";

            for (int i = 0; i <pokemonDetail.getAbilities().size(); i++) {
                PokemonAbility ability = pokemonDetail.getAbilities().get(i).getAbility();
                habilidades = habilidades + ability.getName() + ",\n";
            }
            habilidades = habilidades.trim();

            if (habilidades != null && habilidades.length() > 0 && habilidades.charAt(habilidades.length() - 1) == ',') {
                habilidades = habilidades.substring(0, habilidades.length() - 1);
            }

            binding.tvAbilities.setText(habilidades);
        });

        viewModel.getPokemonSpecies().observe(getActivity(), flavorTextEntries -> {
            Log.d("onResponse", "" + flavorTextEntries.getFlavorTextEntries().get(0).getDescription());
            FlavorText descripcion = new FlavorText();

            for(FlavorText entries : flavorTextEntries.getFlavorTextEntries()){
                if(entries.getLanguage().getName().equals("es")){
                    descripcion = entries;
                }
            }
            binding.tvDescription.setText(descripcion.getDescription());
        });

        viewModel.getDamageRelations().observe(getActivity(), damageRelations -> {
            String damageText = "";
            Log.d("damageResults", "" + damageRelations.getDamageRelations().getDoubleDamageFrom().size());

            for(Results results : damageRelations.getDamageRelations().getDoubleDamageFrom()){
                damageText = damageText + results.getName() + ", ";
            }

            damageText = damageText.trim();

            if (damageText != null && damageText.length() > 0 && damageText.charAt(damageText.length() - 1) == ',') {
                damageText = damageText.substring(0, damageText.length() - 1);
            }

            binding.tvWeakness.setText(damageText);
        });

        viewModel.getIsRequestFailure().observe(getActivity(), aBoolean -> {
        });
        return binding.getRoot();
    }



}