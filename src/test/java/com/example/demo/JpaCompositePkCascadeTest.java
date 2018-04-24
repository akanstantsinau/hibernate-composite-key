package com.example.demo;

import com.example.demo.model.ChildChildItem;
import com.example.demo.model.ChildItem;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaCompositePkCascadeTest {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void test(){
        Item item = new Item();
        item.setName("test");
        itemRepository.save(item);

        entityManager.flush();
        entityManager.clear();

        ChildItem childItem = new ChildItem();
        childItem.setName("test1");
        childItem.setParentItem(item);

        ChildChildItem childChild = new ChildChildItem();
        childChild.setLang("some");
        childChild.setName("test3");
        childChild.getId().setParent(childItem);

        childItem.setChildItems(Collections.singleton(childChild));

        item.setChildItems(Collections.singleton(childItem));

        itemRepository.save(item);
    }
}
