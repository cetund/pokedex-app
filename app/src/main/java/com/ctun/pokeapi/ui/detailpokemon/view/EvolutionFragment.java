package com.ctun.pokeapi.ui.detailpokemon.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctun.pokeapi.databinding.FragmentEvolutionBinding;


public class EvolutionFragment extends Fragment {
    private FragmentEvolutionBinding binding;

    public EvolutionFragment() {
        // Required empty public constructor
    }

    public static EvolutionFragment newInstance() {

        return  new EvolutionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEvolutionBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}