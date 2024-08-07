package com.api.api_rest.repositories;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.api_rest.models.Proveedores;

@Repository
public interface IProveedoresRepository extends JpaRepository<Proveedores, Long>{
	
}
