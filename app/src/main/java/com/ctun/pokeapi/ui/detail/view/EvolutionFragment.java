package com.ctun.pokeapi.ui.detail.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctun.pokeapi.data.model.Evolution;
import com.ctun.pokeapi.data.model.EvolvesTo;
import com.ctun.pokeapi.databinding.FragmentEvolutionBinding;
import com.ctun.pokeapi.ui.detail.adapter.PokemonEvolutionListAdapter;
import com.ctun.pokeapi.ui.detail.viewmodel.PokemonDetailViewModel;
import com.ctun.pokeapi.utils.GetIdFromUrl;

import java.util.ArrayList;
import java.util.List;


public class EvolutionFragment extends Fragment {
    private FragmentEvolutionBinding binding;
    private PokemonDetailViewModel viewModel;

    public EvolutionFragment() {
        // Required empty public constructor
    }

    public static EvolutionFragment newInstance() {

        return  new EvolutionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PokemonDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEvolutionBinding.inflate(inflater, container, false);

        setObservers();

        return binding.getRoot();
    }

    private void setObservers(){

        viewModel.getPokemonSpecies().observe(getActivity(), species -> {
            String urlEvolutionChain = species.getEvolutionChain().getUrl();

            String segments[] = urlEvolutionChain.split("/");

            // Grab the last segment
            int document = Integer.parseInt(segments[segments.length - 1]);
            viewModel.getPokemonEvolution(document);
        });

        viewModel.getEvolutionChain().observe(getActivity(), evolutionChain -> {
            List<EvolvesTo> chain1 = evolutionChain.getChain().getEvolvesTo();
            String base = evolutionChain.getChain().getSpecies().getName();
            String pathEvolutionBase = evolutionChain.getChain().getSpecies().getUrl();
            String evolution1;
            String pathEvolution1;
            String evolution2;
            String pathEvolution2;
            int level1 = 0;
            int level2 = 0;

            int idEvolutionBase = GetIdFromUrl.getId(pathEvolutionBase);
            int idEvolution1 = 0;
            int idEvolution2 = 0;

            List<Evolution> evolutionList = new ArrayList<>();
            evolutionList.add(new Evolution(idEvolutionBase, base, 1));

            if(!chain1.isEmpty()){
                evolution1 = chain1.get(0).getSpecies().getName();
                pathEvolution1 = chain1.get(0).getSpecies().getUrl();
                idEvolution1 = GetIdFromUrl.getId(pathEvolution1);
                level1 = chain1.get(0).getEvolutionDetails().get(0).getMinLevel();

                evolutionList.add(new Evolution(idEvolution1, evolution1, level1));

                if(!chain1.get(0).getEvolvesTo().isEmpty()){
                    evolution2 = chain1.get(0).getEvolvesTo().get(0).getSpecies().getName();
                    pathEvolution2 = chain1.get(0).getEvolvesTo().get(0).getSpecies().getUrl();
                    idEvolution2 = GetIdFromUrl.getId(pathEvolution2);
                    level2 = chain1.get(0).getEvolvesTo().get(0).getEvolutionDetails().get(0).getMinLevel();
                    evolutionList.add(new Evolution(idEvolution2, evolution2, level2));
                }
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            PokemonEvolutionListAdapter adapter = new PokemonEvolutionListAdapter(getActivity(), evolutionList);
            binding.recyclerEvolution.setLayoutManager(linearLayoutManager);
            binding.recyclerEvolution.setAdapter(adapter);
        });
    }
}