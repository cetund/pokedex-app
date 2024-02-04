package com.ctun.pokeapi.ui.detail.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ctun.pokeapi.R;
import com.ctun.pokeapi.databinding.FragmentStatsBinding;
import com.ctun.pokeapi.ui.detail.viewmodel.PokemonDetailViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;

    private PokemonDetailViewModel viewModel;

    String[] labels = {"HP", "ATTACK", "DEFENSE", "SPECIAL ATTACK", "SPECIAL DEFENSE", "SPEED"};

    public StatsFragment() {
        // Required empty public constructor
    }

    public static StatsFragment newInstance() {
        StatsFragment fragment = new StatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PokemonDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStatsBinding.inflate(getLayoutInflater(), container, false );
        setObservers();
        setListeners();
        return binding.getRoot();
    }

    private void setListeners(){
        binding.switchRadar.setOnCheckedChangeListener((compoundButton, b) -> {
            binding.lytBars.setVisibility(b ? View.GONE: View.VISIBLE);
            binding.radarChart.setVisibility(b ? View.VISIBLE:View.GONE);
        });
    }

    private void setObservers(){
        viewModel.getDetail().observe(requireActivity(), pokemonDetail -> {
            int statHp =  pokemonDetail.getStats().get(0).getBaseStat();
            int statAttack = pokemonDetail.getStats().get(1).getBaseStat();
            int statDefense = pokemonDetail.getStats().get(2).getBaseStat();
            int statSpecialAttack = pokemonDetail.getStats().get(3).getBaseStat();
            int statSpecialDefense = pokemonDetail.getStats().get(4).getBaseStat();
            int statSpeed = pokemonDetail.getStats().get(5).getBaseStat();

            binding.tvHP.setText("HP");
            binding.progressHP.setProgress(statHp);
            binding.progressHP.setProgressTintList(ColorStateList.valueOf(getColorProgress(statHp)));
            binding.tvProgressHp.setText(String.valueOf(statHp));

            binding.tvAttack.setText("ATTACK");
            binding.progressAttack.setProgress(statAttack);
            binding.progressAttack.setProgressTintList(ColorStateList.valueOf(getColorProgress(statAttack)));
            binding.tvProgressAttack.setText(String.valueOf(statAttack));

            binding.tvDefense.setText("DEFENSE");
            binding.progressDefense.setProgress(statDefense);
            binding.progressDefense.setProgressTintList(ColorStateList.valueOf(getColorProgress(statDefense)));
            binding.tvProgressDefense.setText(String.valueOf(statDefense));

            binding.tvSpecialAttack.setText("SPECIAL ATTACK");
            binding.progressSpecialAttack.setProgress(statSpecialAttack);
            binding.progressSpecialAttack.setProgressTintList(ColorStateList.valueOf(getColorProgress(statSpecialAttack)));
            binding.tvProgressSpecialAttack.setText(String.valueOf(statSpecialAttack));

            binding.tvSpecialDefense.setText("SPECIAL DEFENSE");
            binding.progressSpecialDefense.setProgress(statSpecialDefense);
            binding.progressSpecialDefense.setProgressTintList(ColorStateList.valueOf(getColorProgress(statSpecialDefense)));
            binding.tvProgressSpecialDefense.setText(String.valueOf(statSpecialDefense));

            binding.tvSpeed.setText("SPEED");
            binding.progressSpeed.setProgress(statSpeed);
            binding.progressSpeed.setProgressTintList(ColorStateList.valueOf(getColorProgress(statSpeed)));
            binding.tvProgressSpeed.setText(String.valueOf(statSpeed));

            ArrayList<RadarEntry> dataValues = new ArrayList<>();
            dataValues.add(new RadarEntry(statHp));
            dataValues.add(new RadarEntry(statAttack));
            dataValues.add(new RadarEntry(statDefense));
            dataValues.add(new RadarEntry(statSpecialAttack));
            dataValues.add(new RadarEntry(statSpecialDefense));
            dataValues.add(new RadarEntry(statSpeed));
            initChart(dataValues);
        });
    }

    private void initChart(List<RadarEntry> dataValues){
        RadarDataSet dataSet = new RadarDataSet(dataValues, null);
        dataSet.setDrawFilled(true);
        dataSet.setColor(Color.BLUE);
        dataSet.setFillColor(Color.BLUE);
        dataSet.setFillAlpha(20);
        dataSet.setLineWidth(2f);
        dataSet.setDrawHighlightCircleEnabled(true);
        dataSet.setDrawHighlightIndicators(true);
        dataSet.setValueTextSize(12f);


        RadarData data = new RadarData();
        data.addDataSet(dataSet);


        XAxis xAxis = binding.radarChart.getXAxis();
        xAxis.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        YAxis yAxis = binding.radarChart.getYAxis();
        yAxis.setAxisMinimum(0f);
        yAxis.setTextSize(16f);
        yAxis.setLabelCount(6, false);
        yAxis.setDrawLabels(false);


       binding.radarChart.getLegend().setEnabled(false);
        binding.radarChart.getDescription().setEnabled(false);
        binding.radarChart.animateXY(
                1400, 1400,
                Easing.EaseInOutQuad,
                Easing.EaseInOutQuad);
        binding.radarChart.setRotationEnabled(false);
        binding.radarChart.setData(data);
        binding.radarChart.invalidate();

    }

    private int getColorProgress(int stat){
        if(stat >= 50){
            return Color.parseColor("#4CAF50");
        }else{
            return Color.parseColor("#F44336");
        }
    }

}