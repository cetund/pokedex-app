package com.ctun.pokeapi.utils;

public interface ApiServiceCallback<T> {
    void onSuccess(T t);
    void onFailure(String error);
}

