package com.edutech.Contenido.repository;

import com.edutech.Contenido.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
