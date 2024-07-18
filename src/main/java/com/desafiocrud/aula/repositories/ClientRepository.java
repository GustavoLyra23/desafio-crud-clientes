package com.desafiocrud.aula.repositories;

import com.desafiocrud.aula.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
