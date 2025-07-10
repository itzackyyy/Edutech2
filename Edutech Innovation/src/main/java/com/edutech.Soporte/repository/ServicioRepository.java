package com.edutech.Soporte.repository;
import com.edutech.Soporte.model.SoporteCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<SoporteCliente, Integer> {

}
