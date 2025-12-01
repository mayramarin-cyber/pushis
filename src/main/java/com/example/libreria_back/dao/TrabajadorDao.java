package com.example.libreria_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libreria_back.entity.TrabajadorEntity;

public interface TrabajadorDao extends JpaRepository<TrabajadorEntity, Long>{

}
