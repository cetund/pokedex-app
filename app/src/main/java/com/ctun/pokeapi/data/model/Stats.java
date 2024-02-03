package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

public class Stats {
    /*{
        "base_stat": 60,
            "effort": 0,
            "stat": {
        "name": "hp",
                "url": "https://pokeapi.co/api/v2/stat/1/"
    }
    }*/
    @SerializedName("base_stat")
    private int baseStat;

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }
}
