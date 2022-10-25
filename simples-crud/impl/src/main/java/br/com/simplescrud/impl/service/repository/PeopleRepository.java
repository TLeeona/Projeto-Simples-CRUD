package br.com.simplescrud.impl.service.repository;


import br.com.simplescrud.impl.service.model.PeopleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleModel, UUID> {
    boolean existsByEmail(String email);
}
