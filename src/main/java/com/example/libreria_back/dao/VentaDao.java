package com.example.libreria_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libreria_back.entity.VentaEntity;

public interface VentaDao extends JpaRepository<VentaEntity, Long>{

}
