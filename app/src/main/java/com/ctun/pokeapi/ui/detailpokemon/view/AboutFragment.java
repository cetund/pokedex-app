package com.ctun.pokeapi.ui.detailpokemon.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctun.pokeapi.data.model.Abilities;
import com.ctun.pokeapi.data.model.PokemonAbility;
import com.ctun.pokeapi.databinding.FragmentAboutBinding;
import com.ctun.pokeapi.ui.detailpokemon.viewmodel.PokemonDetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;
    private PokemonDetailViewModel viewModel;
    private static final String ID_POKEMON = "ID_POKEMON";

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

        viewModel.getPokemonDetail(idPokemon);
        viewModel.getDetail().observe(getActivity(), pokemonDetail -> {
            binding.tvWeight.setText("" + pokemonDetail.getWeight());
            binding.tvHeight.setText("" + pokemonDetail.getHeight());
            String habilidades = "";

            for (int i = 0; i <pokemonDetail.getAbilities().size(); i++) {
                PokemonAbility ability = pokemonDetail.getAbilities().get(i).getAbility();
                habilidades = habilidades + ability.getName() + ", ";
            }
            habilidades = habilidades.trim();

            if (habilidades != null && habilidades.length() > 0 && habilidades.charAt(habilidades.length() - 1) == ',') {
                habilidades = habilidades.substring(0, habilidades.length() - 1);
            }

            binding.tvAbilities.setText(habilidades);
        });

        return binding.getRoot();
    }


}