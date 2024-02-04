package com.ctun.pokeapi.ui.detail.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class PokemonTabsDetailAdapter extends FragmentStateAdapter
{
    private static final int STATS = 0;
    private static final int CALLS = 1;
    private static final int CHATS = 2;

    private static final int[] TABS = new int[]{STATS, CALLS, CHATS};

    private Context mContext;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public PokemonTabsDetailAdapter(Context context, FragmentManager fragmentManager,  Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        mContext = context;
    }
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);

    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
