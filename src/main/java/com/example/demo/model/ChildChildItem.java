package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class ChildChildItem {


    @EmbeddedId
    @JsonIgnore
    private final ChildChildItemPk id = new ChildChildItemPk();

    private String name;

    @JsonProperty
    public String getLang() {
        return getId().getLang();
    }

    @JsonProperty
    public void setLang(String lang) {
        getId().setLang(lang);
    }

    public ChildChildItemPk getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
