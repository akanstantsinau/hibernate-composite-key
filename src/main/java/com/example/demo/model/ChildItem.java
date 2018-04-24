package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ChildItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="parent_item")
    @JsonBackReference
    private Item parentItem;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "id.parent")
    private Set<ChildChildItem> childItems = new HashSet<>();

    public void setChildItems(Set<ChildChildItem> locales) {
        locales = locales != null ? locales : Collections.emptySet();
        updateParent(locales);

        //required for cascade="all-delete-orphan"
        this.childItems.retainAll(locales);
        this.childItems.addAll(locales);
    }

    @SuppressWarnings("unchecked")
    private void updateParent(Set<ChildChildItem> locales) {
        for (ChildChildItem locale : locales) {
            locale.getId().setParent(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getParentItem() {
        return parentItem;
    }

    public void setParentItem(Item parentItem) {
        this.parentItem = parentItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
