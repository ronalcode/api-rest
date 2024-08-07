package com.api.api_rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_rest.models.Productos;
import com.api.api_rest.services.ProductosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class ProductoController {
	private ProductosService productoService;
	
	@Autowired
	public ProductoController(ProductosService productoService) {
		this.productoService = productoService;
	}
	
	@GetMapping(value = "productos", headers = "Accept=application/json")
	public List<Productos> index(){
		return productoService.index();
	}
	
	@GetMapping(value = "productos/{id}", headers = "Accept=application/json")
	public Optional<Productos> show(@PathVariable Long id){
		return productoService.show(id);
	}
	
	@PostMapping(value = "productos", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> store(@Valid @RequestBody Productos producto, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> errorResponse = new HashMap<>();
            Map<String, String> errors = new HashMap<>();
            
            result.getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            
            errorResponse.put("errors", errors);
            
            return ResponseEntity.badRequest().body(errorResponse);
        }
		productoService.store(producto);
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("msg", "Nuevo producto guardado con éxito");
        
        return ResponseEntity.ok(successResponse);
	}
	
	@PutMapping(value = "productos", headers = "Accept=application/json")
	public void update(@RequestBody Productos producto){
		productoService.update(producto);
	}
	
	@DeleteMapping(value = "productos/{id}", headers = "Accept=application/json")
	public void destroy(@PathVariable Long id){
		productoService.destroy(id);
	}
}
