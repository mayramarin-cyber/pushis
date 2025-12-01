package com.example.libreria_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libreria_back.entity.EditorialEntity;

@Repository
public interface EditorialDao extends JpaRepository<EditorialEntity, Long>{
	
	

}
