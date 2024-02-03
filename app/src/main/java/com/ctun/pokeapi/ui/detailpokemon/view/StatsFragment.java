package com.ctun.pokeapi.ui.detailpokemon.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctun.pokeapi.databinding.FragmentStatsBinding;
import com.ctun.pokeapi.ui.detailpokemon.viewmodel.PokemonDetailViewModel;

public class StatsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private FragmentStatsBinding binding;

    private PokemonDetailViewModel viewModel;

    public StatsFragment() {
        // Required empty public constructor
    }

    public static StatsFragment newInstance() {
        StatsFragment fragment = new StatsFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        viewModel = new ViewModelProvider(requireActivity()).get(PokemonDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStatsBinding.inflate(getLayoutInflater(), container, false );
        // Inflate the layout for this fragment

        viewModel.getDetail().observe(this, pokemonDetail -> {
           int statHp =  pokemonDetail.getStats().get(0).getBaseStat();
           int statAttack = pokemonDetail.getStats().get(1).getBaseStat();
           int statDefense = pokemonDetail.getStats().get(2).getBaseStat();
           int statSpecialAttack = pokemonDetail.getStats().get(3).getBaseStat();
           int statSpecialDefense = pokemonDetail.getStats().get(4).getBaseStat();
           int statSpeed = pokemonDetail.getStats().get(5).getBaseStat();

           binding.tvHP.setText("HP(" +statHp +")");
           binding.progressHP.setProgress(statHp);

           binding.tvAttack.setText("ATTACK(" + statAttack + ")");
           binding.progressAttack.setProgress(statAttack);

            binding.tvDefense.setText("DEFENSE(" + statDefense + ")");
            binding.progressDefense.setProgress(statDefense);

            binding.tvSpecialAttack.setText("SPECIAL ATTACK(" + statSpecialAttack + ")");
            binding.progressSpecialAttack.setProgress(statSpecialAttack);

            binding.tvSpecialDefense.setText("SPECIAL DEFENSE(" + statSpecialDefense + ")");
            binding.progressSpecialDefense.setProgress(statSpecialDefense);

            binding.tvSpeed.setText("SPEED(" + statSpeed + ")");
            binding.progressSpeed.setProgress(statSpeed);
        });
        return binding.getRoot();
    }
}