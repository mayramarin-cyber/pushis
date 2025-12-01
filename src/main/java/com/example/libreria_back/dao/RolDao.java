package com.example.libreria_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libreria_back.entity.RolEntity;

@Repository
public interface RolDao extends JpaRepository<RolEntity, Long> {

}
