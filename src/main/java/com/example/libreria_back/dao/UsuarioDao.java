package com.example.libreria_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libreria_back.entity.UsuarioEntity;

public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long>{

}
