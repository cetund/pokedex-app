package com.ctun.pokeapi.utils;

public class GetIdFromUrl {

    public static int getId(String path){
        String[] segments = path.split("/");

        return Integer.parseInt(segments[segments.length - 1]);
    }
}
