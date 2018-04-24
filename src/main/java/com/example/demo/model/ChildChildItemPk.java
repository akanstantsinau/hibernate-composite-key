package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChildChildItemPk implements Serializable{
    private String lang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "childItemId")
    @JsonBackReference
    private ChildItem parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildChildItemPk localePk = (ChildChildItemPk) o;
        if (getLang() == null || getParent() == null || getParent().getId() == null || localePk.getParent() == null) {
            return false;
        }
        return getLang().equals(localePk.getLang())
                && getParent().getId().equals(localePk.getParent().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLang(), getParent() != null ? getParent().getId() : null);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ChildItem getParent() {
        return parent;
    }

    public void setParent(ChildItem parent) {
        this.parent = parent;
    }
}
