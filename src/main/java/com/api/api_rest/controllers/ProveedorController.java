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

import com.api.api_rest.models.Proveedores;
import com.api.api_rest.services.ProveedoresService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class ProveedorController {
	private ProveedoresService proveedorService;
	
	@Autowired
	public ProveedorController(ProveedoresService proveedorService) {
		this.proveedorService = proveedorService;
	}
	
	@GetMapping(value = "proveedores", headers = "Accept=application/json")
	public List<Proveedores> index(){
		return proveedorService.index();
	}
	
	@GetMapping(value = "proveedores/{id}", headers = "Accept=application/json")
	public Optional<Proveedores> show(@PathVariable Long id){
		return proveedorService.show(id);
	}
	
	@PostMapping(value = "proveedores", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> store(@Valid @RequestBody Proveedores proveedor, BindingResult result) {
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
		proveedorService.store(proveedor);
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("msg", "Nuevo proveedor guardado con éxito");
        
        return ResponseEntity.ok(successResponse);
	}
	
	@PutMapping(value = "proveedores", headers = "Accept=application/json")
	public void update(@RequestBody Proveedores proveedor){
		proveedorService.update(proveedor);
	}
	
	@DeleteMapping(value = "proveedores/{id}", headers = "Accept=application/json")
	public void destroy(@PathVariable Long id){
		proveedorService.destroy(id);
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(
//	  MethodArgumentNotValidException ex) {
//	    Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//	    return errors;
//	}
}
