package ds.training.mitocode.ventas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.training.mitocode.ventas.model.Producto;
import ds.training.mitocode.ventas.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController extends AbstractController {
	@Autowired
	private IProductoService service;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtener(@PathVariable("id") Integer id) {
		Producto prod = getImpl(id);
		return ResponseEntity.ok(prod);
	}
	
	@PostMapping
	public ResponseEntity<?> registrar(@Valid @RequestBody Producto prod) {
		verificarNoExiste(prod);
		prod = service.actualizar(prod);
		URI location = obtenerRuta("/{id}", prod.getIdProducto()).toUri();
		return ResponseEntity.created(location).body(prod);
	}
	
	@PutMapping
	public ResponseEntity<Producto> actualizar(@Valid @RequestBody Producto prod) {
		getImpl(prod.getIdProducto());
		service.actualizar(prod);
		return ResponseEntity.ok(prod);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
		getImpl(id);
		service.eliminar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private boolean verificarNoExiste(Producto prod) {
		return verificarNoExiste(service, prod, Producto.class, "un producto");
	}
	
	private Producto getImpl(Integer id) {
		return getImpl(service, id, Producto.class, "un producto");
	}
}
