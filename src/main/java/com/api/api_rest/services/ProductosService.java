package com.api.api_rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_rest.exceptions.ResourceNotFoundException;
import com.api.api_rest.models.Productos;
import com.api.api_rest.models.Proveedores;
import com.api.api_rest.repositories.IProductosRepository;
import com.api.api_rest.repositories.IProveedoresRepository;

@Service
public class ProductosService {
	private IProductosRepository productoRepo;
	@Autowired
	
	public ProductosService(IProductosRepository productRepo) {
		this.productoRepo = productRepo;
	}
	@Autowired
	private IProveedoresRepository proveedorRepo;
	public List<Productos> index(){
		return productoRepo.findAll();
	}
	
	public Optional<Productos> show(Long id){
		return productoRepo.findById(id);
	}
	
	public void store(Productos producto) {
		Long id  = producto.getProveedor().getId();
		proveedorRepo.findById(id).ifPresentOrElse(
	        proveedor -> {
	            producto.setProveedor(proveedor);
	            productoRepo.save(producto);
	        },
	        () -> {
	            throw new ResourceNotFoundException("Proveedor con ID " + id + " no encontrado");
	        }
	    );	
	}
	
	public void update(Productos producto) {
		Long id = producto.getId();
		Productos productoExistente = productoRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Producto con ID " + id + " no encontrado"));
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
	    
	    if (producto.getProveedor() != null && producto.getProveedor().getId() != null) {
	        Proveedores proveedor = proveedorRepo.findById(producto.getProveedor().getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Proveedor con ID " + producto.getProveedor().getId() + " no encontrado"));
	        productoExistente.setProveedor(proveedor);
	    }
	    productoRepo.save(productoExistente);
	}
	
	public void destroy(Long id) {
		productoRepo.deleteById(id);
	}
}
