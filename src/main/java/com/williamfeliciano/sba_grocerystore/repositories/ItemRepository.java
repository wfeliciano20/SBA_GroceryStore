package com.williamfeliciano.sba_grocerystore.repositories;

import com.williamfeliciano.sba_grocerystore.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
