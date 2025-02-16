package com.josehumberto.testeDev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josehumberto.testeDev.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}