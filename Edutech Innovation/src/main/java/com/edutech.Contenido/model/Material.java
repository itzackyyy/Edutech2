package com.edutech.Contenido.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Contenido_Didactico")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_material;
    private String asignatura_material;
    private String titulo_material;
    private String descripcion_material;
    private String valor_material;
}
