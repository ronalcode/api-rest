package com.api.api_rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_rest.models.Proveedores;
import com.api.api_rest.repositories.IProveedoresRepository;

@Service
public class ProveedoresService  {
	private IProveedoresRepository proveedorRepo;
	@Autowired
	
	public ProveedoresService(IProveedoresRepository proveedorRepo) {
		this.proveedorRepo = proveedorRepo;
	}
	
	public List<Proveedores> index(){
		return proveedorRepo.findAll();
	}
	
	public Optional<Proveedores> show(Long id) {
		return proveedorRepo.findById(id);
	}
	
	public void store(Proveedores proveedores) {
		proveedorRepo.save(proveedores);
	}
	
	public void update(Proveedores proveedor) {
		proveedorRepo.save(proveedor);
	}
	
	public void destroy(Long id) {
		proveedorRepo.deleteById(id);
	}
}
