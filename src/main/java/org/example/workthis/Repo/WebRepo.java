package org.example.workthis.Repo;

import org.example.workthis.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WebRepo extends JpaRepository<Client,Integer> {

    @Query("SELECT c FROM Client c WHERE c.email = ?1")
    Client findByEmail(String name);

}
