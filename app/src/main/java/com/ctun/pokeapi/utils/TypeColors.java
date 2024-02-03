package com.ctun.pokeapi.utils;

import java.util.Locale;

public enum TypeColors {
    BUG("BUG", "#94BC4A"),
    DARK("DARK","#736C75"),
    DRAGON("DRAGON","#6A7BAF"),
    ELECTRIC("ELECTRIC","#E5C531"),
    FAIRY("FAIRY","#E397D1"),
    FIGHTING("FIGHTING","#CB5F48"),
    FIRE("FIRE","#EA7A3C"),
    FLYING("FLYING","#7DA6DE"),
    GHOST("GHOST","#846AB6"),
    GRASS("GRASS","#71C558"),
    GROUND("GROUND","#CC9F4F"),
    ICE("ICE","#70CBD4"),
    NORMAL("NORMAL","#AAB09F"),
    POISON("POISON","#B468B7"),
    PSYCHIC("PSYCHIC","#E5709B"),
    ROCK("ROCK","#B2A061"),
    STEEL("STEEL","#89A1B0"),
    WATER("WATER","#539AE2");

    private final String hexColor;
    private final String name;

    TypeColors(String name, String hexColor) {
        this.name = name;
        this.hexColor = hexColor;
    }

    public String getHexColor() {
        return hexColor;
    }

    public String getName() {
        return name;
    }

    public static TypeColors getColorType(String name){
        return TypeColors.valueOf(name.toUpperCase(Locale.getDefault()));
    }
}
