package com.api.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.api_rest.models.Productos;

@Repository
public interface IProductosRepository extends JpaRepository<Productos,Long>{

}
