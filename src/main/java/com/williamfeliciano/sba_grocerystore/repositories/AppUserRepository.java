package com.williamfeliciano.sba_grocerystore.repositories;


import com.williamfeliciano.sba_grocerystore.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

    Optional<AppUser> findByUsername(String username);
}
