package com.williamfeliciano.sba_grocerystore.repositories;

import com.williamfeliciano.sba_grocerystore.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<List<Item>> findByCategoryId (Long categoryId);
}
