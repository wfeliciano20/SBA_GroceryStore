package com.williamfeliciano.sba_grocerystore.repositories;

import com.williamfeliciano.sba_grocerystore.entities.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@EnableJpaRepositories(basePackages = "com.williamfeliciano.sba_grocerystore.repositories")
@ActiveProfiles("test")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void givenListOfItems_whenFindAll_thenReturnsListOfITems() {
        List<Item> items = itemRepository.findAll();
        Assertions.assertThat(items).isNotNull();
        Assertions.assertThat(items.size()).isGreaterThan(0);
    }
}
