package com.edutech.Gerente.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Gerente")
public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_gerente;
    private String nombre_gerente;
    private String apellido_gerente;
}
