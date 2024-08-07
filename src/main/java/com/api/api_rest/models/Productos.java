package com.api.api_rest.models;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Productos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	@NotBlank(message = "Tiene que agregar una descripción")
	@Size(max = 254, message = "La descripción debe ser máximo 254 caracteres")
	private String descripcion;
	private float precio;
	private int cantidad;
	@ManyToOne
    @JoinColumn(name = "proveedor_id")
//	@JsonBackReference
    private Proveedores proveedor;
	
	@Override
    public String toString() {
        return "Productos{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", precio=" + precio +
               ", cantidad=" + cantidad +
               ", proveedor=" + (proveedor != null ? proveedor.getId() : "null") +
               '}';
    }
}
