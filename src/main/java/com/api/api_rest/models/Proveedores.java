package com.api.api_rest.models;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Proveedores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nombre;
	private String direccion;
	private String telefono;
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El campo debe ser de tipo email")
	private String email;
	@OneToMany(mappedBy = "proveedor")
//	@JsonManagedReference 
	@JsonIgnore
    private Set<Productos> productos;

}
