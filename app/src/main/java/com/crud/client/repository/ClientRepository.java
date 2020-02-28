package com.crud.client.repository;


import com.crud.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.cpf = :cpf ")
    public Client findCpf(@Param("cpf") String cpf);

    @Query("SELECT c FROM Client c WHERE c.cpf = :cpf AND c.id <> :id")
    public Client findCpf(@Param("cpf") String cpf,@Param("id") long id);

    @Query("SELECT c FROM Client c WHERE c.name = :name ")
    public Client findName(@Param("name") String name);
}
