package com.ctun.pokeapi.data.model;

import com.google.gson.annotations.SerializedName;

public class FlavorText {

    @SerializedName("flavor_text")
    private String description;

    @SerializedName("language")
    private Language language;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public class Language {
     @SerializedName("name")
     private String name;
     @SerializedName("url")
     private String url;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getUrl() {
         return url;
     }

     public void setUrl(String url) {
         this.url = url;
     }
 }
}
