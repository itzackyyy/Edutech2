package com.edutech.Soporte.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Soporte")
public class SoporteCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer soporte_id;
    private String nombre_soporte;
    private String apellido_soporte;
    private String correo_soporte;
    private String numero_soporte;
}
