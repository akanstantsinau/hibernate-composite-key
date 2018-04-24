package com.example.demo.model;

import org.hibernate.annotations.Where;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "parentItem")
    @OrderColumn
    private Set<ChildItem> childItems = new HashSet<>();

    public void setChildItems(Set<ChildItem> childItems) {
        this.childItems.clear();
        if (!CollectionUtils.isEmpty(childItems)) {
            childItems.forEach(this::addChildContent);
        }
    }

    public void addChildContent(ChildItem childContent) {
        childContent.setParentItem(this);
        this.childItems.add(childContent);
    }

    public Set<ChildItem> getChildItems() {
        return childItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
