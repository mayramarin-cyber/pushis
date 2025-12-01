package com.example.libreria_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libreria_back.entity.DetalleVentaEntity;


@Repository
public interface DetalleVentaDao extends JpaRepository<DetalleVentaEntity, Long>{

}
