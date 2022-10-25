package br.com.simplescrud.impl.repository;


import br.com.simplescrud.impl.model.PeopleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleModel, UUID> {
    boolean existsByEmail(String email);
}
