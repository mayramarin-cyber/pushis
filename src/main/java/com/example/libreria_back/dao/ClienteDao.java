package com.example.libreria_back.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.libreria_back.entity.ClienteEntity;


@Repository
public interface ClienteDao extends JpaRepository<ClienteEntity, Long>{
	
	@Query("SELECT c FROM ClienteEntity c JOIN FETCH c.persona WHERE c.idCliente = :id")
    Optional<ClienteEntity> findByIdWithPersona(@Param("id") Long id);
	
	 List<ClienteEntity> findByPersonaDni(Integer dni);
}
